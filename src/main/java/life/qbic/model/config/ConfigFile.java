package life.qbic.model.config;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jmueller
 * Represents a config file. Its values are set by the presenter.
 * The toString()-method returns the entire config file as a string.
 * If a value is missing, a warning message is logged
 */
public class ConfigFile {
    private String projectName;
    private int numberOfDatasets;
    private int numberOfReplicates;
    private boolean isModeConditions;
    private String alignmentFile;
    private ArrayList<Dataset> datasetList;
    //If we choose to compare conditions, there's only one fasta and one gff file.
    private String conditionFasta, conditionGFF;

    private boolean writeGraphs = true; //Write normalized graphs by default
    private double stepHeight;
    private double stepHeightReduction;
    private double stepFactor;
    private double stepFactorReduction;
    private double enrichmentFactor;
    private double processingSiteFactor;
    private int stepLength;
    private double baseHeight;

    private double normalizationPercentile;
    private double enrichmentNormalizationPercentile;
    private String clusterMethod;
    private int tssClusteringDistance;
    private int allowedCrossDatasetShift;
    private int allowedCrossReplicateShift;
    private int matchingReplicates;
    private int utrLength;
    private int antisenseUtrLength;

    private final static Logger configFileLogger = Logger.getLogger(ConfigFile.class.getName());

    public ConfigFile() {
    }

    @Override
    public String toString() {
        /*I (jmueller) changed some lines because they contained redundant/useless information, namely
            - 'xmfa' is only written if the user is comparing genomes
            - 'genome_...' and 'annotation_...", which contain the paths to the fasta and gff files for each genome,
                 are only written if the user is comparing genomes
            - When comparing conditions, every condition has the same genome (and annotation, respectively),
                 so only one "fasta" and one "annotation" parameter is written - these are two new parameters that
                 don't appear in the old config files!
          Whoever is going to connect the WebUI to the backend needs to keep this in mind and either has to adjust
          the config file parser in the backend or revert my changes right here.

        */
        StringBuilder builder = new StringBuilder();
        buildLine(builder, "projectName", projectName);
        buildLine(builder, "numberOfDatasets", Integer.toString(numberOfDatasets));
        buildLine(builder, "numReplicates", Integer.toString(numberOfReplicates));
        if (!isModeConditions) {
            buildLine(builder, "xmfa", alignmentFile);
        }
        buildLine(builder, "writeGraphs", writeGraphs ? "1" : "0");

        if (isModeConditions) {
            buildLine(builder, "fasta", conditionFasta);
            buildLine(builder, "annotation", conditionGFF);
            for (Dataset dataset : datasetList) {
                buildLine(builder, "outputPrefix_" + dataset.getAlignmentID(), dataset.getName());
                for (Replicate replicate : dataset.getReplicateList()) {
                    buildLine(builder, "fivePrimePlus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getTreatedCodingStrand());
                    buildLine(builder, "fivePrimeMinus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getTreatedTemplateStrand());
                    buildLine(builder, "normalPlus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getUntreatedCodingStrand());
                    buildLine(builder, "normalMinus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getUntreatedTemplateStrand());
                }
            }

        } else {
            for (Dataset dataset : datasetList) {
                buildLine(builder, "genome_" + dataset.getAlignmentID(), dataset.getFasta());
                buildLine(builder, "annotation_" + dataset.getAlignmentID(), dataset.getGff());
                buildLine(builder, "outputPrefix_" + dataset.getAlignmentID(), dataset.getName());
                for (Replicate replicate : dataset.getReplicateList()) {
                    buildLine(builder, "fivePrimePlus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getTreatedCodingStrand());
                    buildLine(builder, "fivePrimeMinus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getTreatedTemplateStrand());
                    buildLine(builder, "normalPlus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getUntreatedCodingStrand());
                    buildLine(builder, "normalMinus_" + dataset.getAlignmentID() + replicate.getReplicateID(), replicate.getUntreatedTemplateStrand());
                }
            }

        }
        StringBuilder idList = new StringBuilder();
        for (Dataset dataset : datasetList) {
            idList.append(dataset.getAlignmentID()).append(",");
        }
        buildLine(builder, "idList", idList.toString().substring(0, idList.length() - 1));
        buildLine(builder, "allowedCompareShift", Integer.toString(allowedCrossDatasetShift));
        buildLine(builder, "allowedRepCompareShift", Integer.toString(allowedCrossReplicateShift));
        buildLine(builder, "maxUTRlength", Integer.toString(utrLength));
        buildLine(builder, "maxASutrLength", Integer.toString(antisenseUtrLength));
        buildLine(builder, "maxNormalTo5primeFactor", Double.toString(processingSiteFactor));
        buildLine(builder, "maxTSSinClusterDistance", Integer.toString(tssClusteringDistance));
        buildLine(builder, "min5primeToNormalFactor", Double.toString(enrichmentFactor));
        buildLine(builder, "minCliffFactor", Double.toString(stepFactor));
        buildLine(builder, "minCliffFactorDiscount", Double.toString(stepFactorReduction));
        buildLine(builder, "minCliffHeight", Double.toString(stepHeight));
        buildLine(builder, "minCliffHeightDiscount", Double.toString(stepHeightReduction));
        buildLine(builder, "minNormalHeight", Double.toString(baseHeight));
        buildLine(builder, "minNumRepMatches", Integer.toString(matchingReplicates));
        buildLine(builder, "minPlateauLength", Integer.toString(stepLength));
        buildLine(builder, "mode", isModeConditions ? "cond" : "align");
        buildLine(builder, "normPercentile", Double.toString(normalizationPercentile));
        buildLine(builder, "texNormPercentile", Double.toString(enrichmentNormalizationPercentile));
        buildLine(builder, "TSSinClusterSelectionMethod", clusterMethod);


        //TODO: These values appear in the config file, but aren't customisable yet
        buildLine(builder, "maxGapLengthInGene", "500");
        buildLine(builder, "superGraphCompatibility", "igb");
        buildLine(builder, "writeNocornacFiles", "0");

        return builder.toString();
    }

    private void buildLine(StringBuilder builder, String key, String value) {
        if (value == null) {
            configFileLogger.log(Level.WARNING, "Couldn't create config file! Parameter \'" + key + "\' isn't set.");
        }
        else
            builder.append(key).append(" = ").append(value).append("\n");
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumberOfDatasets() {
        return numberOfDatasets;
    }

    public void setNumberOfDatasets(int numberOfDatasets) {
        this.numberOfDatasets = numberOfDatasets;
    }

    public int getNumberOfReplicates() {
        return numberOfReplicates;
    }

    public void setNumberOfReplicates(int numberOfReplicates) {
        this.numberOfReplicates = numberOfReplicates;
    }

    public boolean isModeConditions() {
        return isModeConditions;
    }

    public void setModeConditions(boolean modeConditions) {
        isModeConditions = modeConditions;
    }

    public void setAlignmentFile(String alignmentFile) {
        this.alignmentFile = alignmentFile;
    }

    public ArrayList<Dataset> getDatasetList() {
        return datasetList;
    }

    public void setDatasetList(ArrayList<Dataset> datasetList) {
        this.datasetList = datasetList;
    }

    public void setConditionFasta(String conditionFasta) {
        this.conditionFasta = conditionFasta;
    }

    public void setConditionGFF(String conditionGFF) {
        this.conditionGFF = conditionGFF;
    }

    public boolean isWriteGraphs() {
        return writeGraphs;
    }

    public void setWriteGraphs(boolean writeGraphs) {
        this.writeGraphs = writeGraphs;
    }

    public double getStepHeight() {
        return stepHeight;
    }

    public void setStepHeight(double stepHeight) {
        this.stepHeight = stepHeight;
    }

    public double getStepHeightReduction() {
        return stepHeightReduction;
    }

    public void setStepHeightReduction(double stepHeightReduction) {
        this.stepHeightReduction = stepHeightReduction;
    }

    public double getStepFactor() {
        return stepFactor;
    }

    public void setStepFactor(double stepFactor) {
        this.stepFactor = stepFactor;
    }

    public double getStepFactorReduction() {
        return stepFactorReduction;
    }

    public void setStepFactorReduction(double stepFactorReduction) {
        this.stepFactorReduction = stepFactorReduction;
    }

    public double getEnrichmentFactor() {
        return enrichmentFactor;
    }

    public void setEnrichmentFactor(double enrichmentFactor) {
        this.enrichmentFactor = enrichmentFactor;
    }

    public double getProcessingSiteFactor() {
        return processingSiteFactor;
    }

    public void setProcessingSiteFactor(double processingSiteFactor) {
        this.processingSiteFactor = processingSiteFactor;
    }

    public int getStepLength() {
        return stepLength;
    }

    public void setStepLength(int stepLength) {
        this.stepLength = stepLength;
    }

    public double getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(double baseHeight) {
        this.baseHeight = baseHeight;
    }

    public double getNormalizationPercentile() {
        return normalizationPercentile;
    }

    public void setNormalizationPercentile(double normalizationPercentile) {
        this.normalizationPercentile = normalizationPercentile;
    }

    public double getEnrichmentNormalizationPercentile() {
        return enrichmentNormalizationPercentile;
    }

    public void setEnrichmentNormalizationPercentile(double enrichmentNormalizationPercentile) {
        this.enrichmentNormalizationPercentile = enrichmentNormalizationPercentile;
    }

    public String getClusterMethod() {
        return clusterMethod;
    }

    public void setClusterMethod(String clusterMethod) {
        this.clusterMethod = clusterMethod;
    }

    public int getTssClusteringDistance() {
        return tssClusteringDistance;
    }

    public void setTssClusteringDistance(int tssClusteringDistance) {
        this.tssClusteringDistance = tssClusteringDistance;
    }

    public int getAllowedCrossDatasetShift() {
        return allowedCrossDatasetShift;
    }

    public void setAllowedCrossDatasetShift(int allowedCrossSubjectShift) {
        this.allowedCrossDatasetShift = allowedCrossSubjectShift;
    }

    public int getAllowedCrossReplicateShift() {
        return allowedCrossReplicateShift;
    }

    public void setAllowedCrossReplicateShift(int allowedCrossReplicateShift) {
        this.allowedCrossReplicateShift = allowedCrossReplicateShift;
    }

    public int getMatchingReplicates() {
        return matchingReplicates;
    }

    public void setMatchingReplicates(int matchingReplicates) {
        this.matchingReplicates = matchingReplicates;
    }

    public int getUtrLength() {
        return utrLength;
    }

    public void setUtrLength(int utrLength) {
        this.utrLength = utrLength;
    }

    public int getAntisenseUtrLength() {
        return antisenseUtrLength;
    }

    public void setAntisenseUtrLength(int antisenseUtrLength) {
        this.antisenseUtrLength = antisenseUtrLength;
    }


}
