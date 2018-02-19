package life.qbic.model;

import life.qbic.model.beans.*;
import life.qbic.model.config.ConfigFile;
import life.qbic.model.config.Dataset;
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
        configFile.setDatasetList(new ArrayList<>());
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
            Dataset currentDataset = new Dataset();
            //IDs of conditions are set automatically
            if (configFile.isModeConditions()) {
                System.out.println("Current alignment id: " + (configFile.getNumberOfDatasets() - datasetsToAdd + i + 1));
                currentDataset.setAlignmentID("" + (configFile.getNumberOfDatasets() - datasetsToAdd + i + 1));
            }
            //Add as many replicates as the other Genomes have
            for (int j = 0; j < configFile.getNumberOfReplicates(); j++) {
                currentDataset.getReplicateList().add(new Replicate());
            }
            configFile.getDatasetList().add(currentDataset);
        }
    }

    public void removeDatasets(int datasetsToRemove) {
        int oldGenomeListSize = configFile.getDatasetList().size();
        int startIndex = oldGenomeListSize - datasetsToRemove;
        for (int i = startIndex; i < oldGenomeListSize; i++)
            //Remove tail until desired size is reached
            configFile.getDatasetList().remove(configFile.getDatasetList().size() - 1);
    }

    public void addReplicates(int replicatesToAdd) {
        for (int i = 0; i < replicatesToAdd; i++) {
            for (Dataset dataset : configFile.getDatasetList()) {
                dataset.getReplicateList().add(new Replicate());
            }
        }

    }

    public void removeReplicates(int replicatesToRemove) {
        int oldReplicateListSize = configFile.getDatasetList().get(0).getReplicateList().size();
        int startIndex = oldReplicateListSize - replicatesToRemove;
        for (Dataset dataset : configFile.getDatasetList()) {
            for (int i = startIndex; i < oldReplicateListSize; i++) {
                //Remove tail until desired size is reached
                dataset.getReplicateList().remove(dataset.getReplicateList().size() - 1);
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
