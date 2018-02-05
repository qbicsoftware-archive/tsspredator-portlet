package life.qbic.model;

/**
 * All global variables/constants are stored in this class
 */
public class Globals {
    //Strings from the view that have logical dependencies (i.e. used more than once)
    public static final String GENERAL_PANEL_CAPTION = "General Configuration";
    public static final String DATA_PANEL_CAPTION = "Data Settings";
    public static final String PARAMETERS_PANEL_CAPTION = "Parameter Settings";
    public static final String COMPARE_CONDITIONS = "Compare Conditions";
    public static final String COMPARE_GENOMES = "Compare Strains/Species";
    public static final String CONFIG_FILE_TMP_PATH = "/tmp/tssconfiguration.conf";
    public static final String PARAMETERS_PRESET = "Preset";
    public static final String PARAMETERS_CUSTOM = "Custom";
    public static final String CLUSTER_METHOD_HIGHEST = "HIGHEST";
    public static final String CLUSTER_METHOD_FIRST = "FIRST";

    //Initial parameter values
    public static final boolean IS_CONDITIONS_INIT = false;
    public static final int NUMBER_OF_DATASETS_INIT = 1;
    public static final int NUMBER_OF_REPLICATES_INIT = 1;
    public static final double NORMALIZATION_PERCENTILE_INIT = 0.9;
    public static final double ENRICHMENT_NORMALIZATION_PERCENTILE_INIT = 0.5;
    public static final String CLUSTER_METHOD_INIT = CLUSTER_METHOD_HIGHEST;
    public static final int TSS_CLUSTERING_DISTANCE_INIT = 3;
    public static final int CROSS_DATASET_SHIFT_INIT = 1;
    public static final int CROSS_REPLICATE_SHIFT_INIT = 1;
    public static final int MATCHING_REPLICATES_INIT = 1;
    public static final int UTR_LENGTH_INIT = 300;
    public static final int ANTISENSE_UTR_LENGTH_INIT = 100;

    //Info texts
    public static final String GENERAL_CONFIG_INFO =
            "Welcome to <b>TSSpredator</b>!<br>" +
                    "Please select the type of your study below. Then select your project from the list by clicking on it. " +
                    "If you chose to compare different strains or species, please also select a multiple alignment file (<i>*.xmfa</i>).<br>" +
                    "When you're done, click on <b>\"Data Settings\"</b> to continue. You can always come back to this window by " +
                    "clicking on <b>\"General Configuration\"</b>.";
    public static final String GENOME_DATA_SETTINGS_INFO =
            "Please select how many <b>Genomes</b> you want to compare and how many <b>Replicates</b> you have for each genome.<br>" +
                    "Then enter genome and replicate data below.<br>" +
                    "When you're done, click on <b>\"Parameter Settings\"</b> to continue. " +
                    "You can always come back to this window by clicking on <b>\"Data Settings\"</b>.";
    public static final String CONDITION_DATA_SETTINGS_INFO =
            "Please select how many <b>Conditions</b> you want to compare and how many <b>Replicates</b> you have for each condition.<br>" +
                    "Next, choose the <b>nucleotide sequence</b> file (<i>*.fasta</i>) and the <b>annotation</b> file (<i>*.gff</i>) " +
                    "of your project by clicking on them.<br>" +
                    "Then scroll down to enter genome and replicate data.<br>" +
                    "When you're done, click on <b>\"Parameter Settings\"</b> to continue. " +
                    "You can always come back to this window by clicking on <b>\"Data Settings\"</b>.";
    public static final String GENOME_TAB_INFO =
            "Please enter the <b>Name</b> of this genome as well as its <b>Alignment ID</b> in the multiple alignment file.<br>" +
                    "Next, choose the <b>Nucleotide Sequence</b> file (<i>*.fasta</i>) and the <b>Annotation</b> file (<i>*.gff</i>) " +
                    "of this genome by clicking on them.<br>" +
                    "of this genome by clicking on them.<br>" +
                    "Then scroll down to select the <b>RNA-seq Graph Files</b> for this genome.";
    public static final String CONDITION_TAB_INFO =
            "Please enter the <b>Name</b> of this condition.<br>" +
                    "Then scroll down to select the <b>RNA-seq Graph Files</b> for this condition.";
    public static final String REPLICATE_TAB_INFO =
            "You need to select 4 <b>Wiggle Files</b> (<i>*.wig/*.gr</i>) per replicate. " +
                    "All the graph files that are contained in your project are listed below (<b>\"Available Graph Files\"</b>).<br>" +
                    "Please <i>drag'n'drop</i> them into the appropriate fields to select them.<br>" +
                    "If you've made a mistake, you can either drag the graph files from one field to another to swap them " +
                    "or you can drag them back into the list of available files.";
    public static final String PARAMETER_INFO =
            "In case you don't want to use default parameters, you can customize them here. " +
                    "There are four kinds of parameters in TSSpredator:" +
                    "<ul>" +
                    "<li><b>Normalization Parameters</b></li>" +
                    "<li><b>Prediction Parameters</b></li>" +
                    "<li><b>TSS Clustering and Comparative Parameters</b></li>" +
                    "<li><b>Classification Parameters</b></li>" +
                    "</ul>";

    //Parameter Tooltip Texts
    public static final String WRITE_GRAPHS_TEXT = "If you select this option, your output will include the expression " +
            "graph files based on the normalized dRNA-seq data.<br>Please note that this may increase the runtime of the workflow.";
    public static final String NORMALIZATION_PERCENTILE_TEXT = "Choose the percentile by which the dRNA-seq data is normalized by expression height." +
            "<br>If you select the slider to 0.0, no normalization will be performed.";
    public static final String ENRICHMENT_NORMALIZATION_PERCENTILE_TEXT = "Choose the percentile by which the dRNA-seq data is normalized by enrichment factor." +
            "<br>If you select the slider to 0.0, no normalization will be performed.";
    public static final String SENSITIVITY_SPECIFICITY_TEXT = "What were <i>Sensitivity</i> and <i>Specificity</i> again?";
    public static final String STEP_HEIGHT_TEXT = "Set the threshold step height for a TSS candidate here.<br>The step height is the absolute difference in expression height between the " +
            "position considered and the previous one.";
    public static final String STEP_HEIGHT_REDUCTION_TEXT = "If the threshold step height is reached in one of the datasets, " +
            "it is reduced for the other datasets by this value.<br>A higher value means higher sensitivity.";
    public static final String STEP_FACTOR_TEXT = "Set the threshold step factor for a TSS candidate here.<br>" +
            "The step factor is the relative difference in expression height between the position considered and the previous one.";
    public static final String STEP_FACTOR_REDUCTION_TEXT = "If the threshold step factor is reached in one of the datasets, " +
            "it is reduced for the other datasets by this value.<br>A higher value means higher sensitivity.";
    public static final String ENRICHMENT_FACTOR_TEXT = "Set the threshold enrichment factor for a TSS candidate here.<br>" +
            "The enrichment factor is the relative difference in expression height between the treated " +
            "and the untreated library at the position considered.<br>A lower value means higher sensitivity.";
    public static final String PROCESSING_SITE_FACTOR_TEXT = "The processing site factor is the maximal factor by which the untreated library " +
            "may be higher expressed than the treated library and above which the TSS candidate is considered a processing site " +
            "and not annotated as detected.<br>A higher value results in a higher sensitivity.";
    public static final String STEP_LENGTH_TEXT = "Set the minimal length of the TSS related expression region (in base " +
            "pairs).<br>This value depends on the length of the reads that are stacking at the TSS position. In most cases " +
            "this feature can be disabled by setting it to ’0’.<br>" +
            "However, it can be useful if RNA-seq reads have been trimmed extensively before mapping.";
    public static final String BASE_HEIGHT_TEXT = "This value relates to the minimal number of reads in the non-enriched " +
            "library that start at the TSS position.<br>This feature is disabled by default.";
    public static final String MATCHING_REPLICATES_TEXT = "Choose the minimal number of replicates in which a TSS candidate " +
            "has to be detected.<br>A lower value results in a higher sensitivity.";
    public static final String UTR_LENGTH_TEXT = "Set how far from the start codon a TSS may lie to be classified as primary/secondary.<br>" +
            "If the TSS lies further upstream, is is classified as orphan.";
    public static final String ANTISENSE_UTR_LENGTH_TEXT = "Set how far from the start or stop codon a TSS may lie in antisense orientation " +
            "to be classified as antisense.";
    public static final String CROSS_DATASET_SHIFT_TEXT = "Set the maximal distance (in base pairs) between TSS candidates <i>from different datasets</i>" +
            " to be assigned to each other.";
    public static final String CROSS_REPLICATE_SHIFT_TEXT = "Set the maximal distance (in base pairs) between TSS candidates <i>from different replicates</i>" +
            " to be assigned to each other.";
    public static final String CLUSTER_METHOD_INFO = "TSS candidates which are closer than a specific distance (see slider to the right) " +
            "are clustered together.<br>Choose whether the first or the most highly expressed candidate is taken as a representative.";
    public static final String CLUSTERING_DISTANCE_TEXT = "Set the maximal distance (in base pairs) between TSS candidates to be clustered together.";


    //Parameter Tooltip Images
    public static final String STEP_HEIGHT_IMAGE = "../VAADIN/images/Step_Height_2.svg";
    public static final String STEP_FACTOR_IMAGE = "../VAADIN/images/Step_Factor_2.svg";
    public static final String ENRICHMENT_FACTOR_IMAGE = "../VAADIN/images/Enrichment_Factor_2.svg";
    public static final String SENSITIVITY_SPECIFICITY_IMAGE = "../VAADIN/images/High_SE&SP_Bar.svg";


}
