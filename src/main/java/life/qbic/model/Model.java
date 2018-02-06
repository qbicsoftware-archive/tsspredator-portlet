package life.qbic.model;

import life.qbic.model.beans.*;
import life.qbic.model.config.ConfigFile;
import life.qbic.model.config.Genome;
import life.qbic.model.config.Replicate;
import life.qbic.testing.TestData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    /**
     * The model holds:
     * - A config file
     * - A list of ProjectBeans
     * - A list of AlignmentFileBeans
     * - A list of FastaFileBeans
     * - A list of AnnotationFileBeans
     * - A list of GraphFileBeans
     */

    private ConfigFile configFile;
    private List<ProjectBean> projectBeans;
    private List<AlignmentFileBean> alignmentFileBeans;
    private List<FastaFileBean> fastaFileBeans;
    private List<AnnotationFileBean> annotationFileBeans;
    private List<GraphFileBean> graphFileBeans;

    public Model() {
        configFile = new ConfigFile();
        configFile.setGenomeList(new ArrayList<>());
        projectBeans = new LinkedList<>();
        alignmentFileBeans = new LinkedList<>();
        fastaFileBeans = new LinkedList<>();
        annotationFileBeans = new LinkedList<>();
        graphFileBeans = new LinkedList<>();
        fetchData();
    }

    /**
     * Currently, this method invokes the tests in TestData
     * Later, it should access the actual data from the server
     */
    private void fetchData() {
        projectBeans.addAll(TestData.createProjectBeanList());
        alignmentFileBeans.addAll(TestData.createAlignmentFileBeanList());
        fastaFileBeans.addAll(TestData.createFastaFileBeanList());
        annotationFileBeans.addAll(TestData.createAnnotationFileBeanList());
        graphFileBeans.addAll(TestData.createGraphFileBeanList());
    }

    public void addDatasets(int datasetsToAdd) {
        for (int i = 0; i < datasetsToAdd; i++) {
            Genome currentGenome = new Genome();
            //IDs of conditions are set automatically
            if (configFile.isModeConditions()) {
                currentGenome.setAlignmentID("" + (configFile.getNumberOfDatasets() - datasetsToAdd + i));
            }
            //Add as many replicates as the other Genomes have
            for (int j = 0; j < configFile.getNumberOfReplicates(); j++) {
                currentGenome.getReplicateList().add(new Replicate());
            }
            configFile.getGenomeList().add(currentGenome);
        }
    }

    public void removeDatasets(int datasetsToRemove) {
        int oldGenomeListSize = configFile.getGenomeList().size();
        int startIndex = oldGenomeListSize - datasetsToRemove;
        for (int i = startIndex; i < oldGenomeListSize; i++)
            //Remove tail until desired size is reached
            configFile.getGenomeList().remove(configFile.getGenomeList().size() - 1);
    }

    public void addReplicates(int replicatesToAdd) {
        for (int i = 0; i < replicatesToAdd; i++) {
            for (Genome genome : configFile.getGenomeList()) {
                genome.getReplicateList().add(new Replicate());
            }
        }

    }

    public void removeReplicates(int replicatesToRemove) {
        int oldReplicateListSize = configFile.getGenomeList().get(0).getReplicateList().size();
        int startIndex = oldReplicateListSize - replicatesToRemove;
        for (Genome genome : configFile.getGenomeList()) {
            for (int i = startIndex; i < oldReplicateListSize; i++) {
                //Remove tail until desired size is reached
                genome.getReplicateList().remove(genome.getReplicateList().size() - 1);
            }
        }

    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public List<ProjectBean> getProjectBeans() {
        return projectBeans;
    }

    public List<AlignmentFileBean> getAlignmentFileBeans() {
        return alignmentFileBeans;
    }

    public List<FastaFileBean> getFastaFileBeans() {
        return fastaFileBeans;
    }

    public List<AnnotationFileBean> getAnnotationFileBeans() {
        return annotationFileBeans;
    }

    public List<GraphFileBean> getGraphFileBeans() {
        return graphFileBeans;
    }
}
