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
    public static final String COMPARE_GENOMES = "Compare Strain/Species";
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
                    "Please select the type of your study below. Then select your project from the list by double-clicking on it. " +
                    "If you chose to compare different strains or species, please also select a multiple alignment file (<i>*.xmfa</i>).<br>" +
                    "When you're done, click on <b>\"Data Settings\"</b> to continue. You can always come back to this window by " +
                    "clicking on <b>\"General Configuration\"</b>.";
    public static final String GENOME_DATA_SETTINGS_INFO =
            "Please select how many <b>Genomes</b> you want to compare and how many <b>Replicates</b> you have for each genome.<br>" +
                    "Then enter genome and replicate data below.<br>" +
                    "When you're done, click on <b>\"Parameter Settings\"</b> to continue. " +
                    "You can always come back to this window by clicking on <b>\"Data Settings\"</b>";
    public static final String CONDITION_DATA_SETTINGS_INFO =
            "Please select how many <b>Conditions</b> you want to compare and how many <b>Replicates</b> you have for each condition.<br>" +
                    "Next, choose the <b>nucleotide sequence</b> file (<i>*.fasta</i>) and the <b>annotation</b> file (<i>*.gff</i>) " +
                    "of your project by double-clicking on them.<br>" +
                    "Then scroll down to enter genome and replicate data.<br>" +
                    "When you're done, click on <b>\"Parameter Settings\"</b> to continue. " +
                    "You can always come back to this window by clicking on <b>\"Data Settings\"</b>";
    public static final String GENOME_TAB_INFO =
            "Please enter the <b>Name</b> of this genome as well as its <b>Alignment ID</b> in the multiple alignment file.<br>" +
                    "Next, choose the <b>Nucleotide Sequence</b> file (<i>*.fasta</i>) and the <b>Annotation</b> file (<i>*.gff</i>) " +
                    "of this genome by double-clicking on them.<br>" +
                    "Then scroll down to select the <b>RNA-seq Graph Files</b> for this genome.";
    public static final String CONDITION_TAB_INFO =
            "Please enter the <b>Name</b> of this condition.<br>" +
                    "Then scroll down to select the <b>RNA-seq Graph Files</b> for this condition.";
    public static final String REPLICATE_TAB_INFO =
            "You need to select 4 <b>Graph Files</b> (<i>*.gff</i>) per replicate. " +
                    "All the graph files that are contained in your project are listed below (<b>\"Available Graph Files\"</b>).<br>" +
                    "Please <i>drag'n'drop</i> them into the appropriate fields to select them.<br>" +
                    "If you've made a mistake, you can either drag the graph files from one field to another to swap them " +
                    "or you can drag them back into the list of available files.";
    public static final String PARAMETER_INFO =
            "In case you don't want to use default parameters, you can customize them here. " +
                    "There are three kinds of parameters in TSSpredator:" +
                    "<ul>" +
                    "<li><b>Normalization Parameters</b>: TODO</li>" +
                    "<li><b>Pre-Prediction Parameters</b>: TODO</li>" +
                    "<li><b>Post-Prediction Parameters</b>: TODO</li>" +
                    "</ul>";
    public static final String NORMALIZATION_INFO = "TODO";
    public static final String PRE_PREDICTION_INFO = "TODO";
    public static final String POST_PREDICTION_INFO = "TODO";

    //Parameter Tooltip Texts
    public static final String NORMALIZATION_PERCENTILE_TEXT = "This text should describe the parameter";
    public static final String ENRICHMENT_NORMALIZATION_PERCENTILE_TEXT = "";
    public static final String WRITE_GRAPHS_TEXT = "This is the info text for the write graphs checkbox!";
    public static final String SENSITIVITY_SPECIFICITY_TEXT = "In case you're wondering what <i>Sensitivity</i> " +
            "and <i>Specificity</i> are:";
    public static final String STEP_HEIGHT_TEXT = "";
    public static final String STEP_HEIGHT_REDUCTION_TEXT = "";
    public static final String STEP_FACTOR_TEXT = "This text should be displayed atop the image";
    public static final String STEP_FACTOR_REDUCTION_TEXT = "";
    public static final String ENRICHMENT_FACTOR_TEXT = "";
    public static final String PROCESSING_SITE_FACTOR_TEXT = "";
    public static final String STEP_LENGTH_TEXT = "";
    public static final String BASE_HEIGHT_TEXT = "";
    public static final String CLUSTERING_DISTANCE_TEXT = "";
    public static final String CROSS_DATASET_SHIFT_TEXT = "";
    public static final String CROSS_REPLICATE_SHIFT_TEXT = "";
    public static final String MATCHING_REPLICATES_TEXT = "";
    public static final String CLUSTER_METHOD_INFO = "";
    public static final String UTR_LENGTH_TEXT = "";
    public static final String ANTISENSE_UTR_LENGTH_TEXT = "";


    //Parameter Tooltip Images
    public static final String STEP_HEIGHT_IMAGE = "../VAADIN/images/Step_Height_2.svg";
    public static final String STEP_FACTOR_IMAGE = "../VAADIN/images/Step_Factor_2.svg";
    public static final String ENRICHMENT_FACTOR_IMAGE = "../VAADIN/images/Enrichment_Factor_2.svg";
    public static final String SENSITIVITY_SPECIFICITY_IMAGE = "../VAADIN/images/High_SE&SP_Bar.svg";


}
