package life.qbic.view.panels;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import life.qbic.model.Globals;
import life.qbic.presenter.Presenter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a component where the user can set every parameter of his TSSPredator run.
 * For a better overview, the parameters are divided into parts (see below)
 *
 * @author jmueller
 */
public class ParametersPanel extends CustomComponent {
    private Presenter presenter;
    private Panel parametersPanel;
    private VerticalLayout contentLayout;
    private RadioButtonGroup<String> presetSelection;
    private Button presetInfo;

    //Normalization part
    private VerticalLayout normalizationLayout;
    private ParameterSetter normalizationPercentile;
    private ParameterSetter enrichmentNormalizationPercentile;
    private CheckBox writeNormalizedGraphs;
    private Button writeNormalizedInfo;

    //Pre-prediction part
    private VerticalLayout prePredictionLayout;
    private ParameterSetter stepHeight, stepHeightReduction;
    private ParameterSetter stepFactor, stepFactorReduction;
    private ParameterSetter enrichmentFactor, processingSiteFactor;
    private ParameterSetter stepLength, baseHeight;

    //Post-prediction part
    private VerticalLayout postPredictionLayout;
    private ComboBox<String> clusterMethod;
    private Button clusterMethodInfo;
    private ParameterSetter clusteringDistance;
    private ParameterSetter crossDatasetShift;
    private ParameterSetter crossReplicateShift;
    private ComboBox<Integer> matchingReplicates;
    private Button matchingReplicatesInfo;
    private ParameterSetter utrLength;
    private ParameterSetter antisenseUtrLength;

    public ParametersPanel(Presenter presenter) {
        this.presenter = presenter;
        parametersPanel = designPanel();
        setCompositionRoot(parametersPanel);
    }

    /**
     * In this method, the main layout of this panel is created, the different layout parts are created and added
     * to the main layout together with an explanatory info bar.
     *
     * @return
     */
    private Panel designPanel() {
        Panel panel = new Panel();
        contentLayout = new VerticalLayout();
        createParameterLayouts();
        contentLayout.addComponents(new InfoBar(Globals.PARAMETER_INFO), normalizationLayout, prePredictionLayout, postPredictionLayout);
        panel.setContent(contentLayout);
        return panel;
    }

    /**
     * This method adds a listener to the preset selection box so the presenter is informed every time the user
     * changes the preset.
     * Additionally, the style of the preset buttons is changed (half-transparent when parameters are preset)
     */
    private void setupPresetListener() {
        presetSelection.addValueChangeListener(vce -> {
            switch (vce.getValue()) {
                case "Very Specific":
                    presenter.setPreset(Presenter.Preset.VERY_SPECIFIC);
                    break;
                case "More Specific":
                    presenter.setPreset(Presenter.Preset.MORE_SPECIFIC);
                    break;
                case "Default":
                    presenter.setPreset(Presenter.Preset.DEFAULT);
                    break;
                case "More Sensitive":
                    presenter.setPreset(Presenter.Preset.MORE_SENSITIVE);
                    break;
                case "Very Sensitive":
                    presenter.setPreset(Presenter.Preset.VERY_SENSITIVE);
                    break;
                case Globals.PARAMETERS_CUSTOM:
                    presenter.setPreset(null);
            }
            if (vce.getValue().equals(Globals.PARAMETERS_CUSTOM)) {
                stepHeight.removeStyleName("non-custom-parameter");
                stepHeightReduction.removeStyleName("non-custom-parameter");
                stepFactor.removeStyleName("non-custom-parameter");
                stepFactorReduction.removeStyleName("non-custom-parameter");
                enrichmentFactor.removeStyleName("non-custom-parameter");
                processingSiteFactor.removeStyleName("non-custom-parameter");
                stepLength.removeStyleName("non-custom-parameter");
                baseHeight.removeStyleName("non-custom-parameter");
            } else {
                stepHeight.addStyleName("non-custom-parameter");
                stepHeightReduction.addStyleName("non-custom-parameter");
                stepFactor.addStyleName("non-custom-parameter");
                stepFactorReduction.addStyleName("non-custom-parameter");
                enrichmentFactor.addStyleName("non-custom-parameter");
                processingSiteFactor.addStyleName("non-custom-parameter");
                stepLength.addStyleName("non-custom-parameter");
                baseHeight.addStyleName("non-custom-parameter");
            }
            presenter.applyPresetParameters();
        });

    }

    /**
     * Template for a component where the user can adjust a numeric parameter.
     * Consists of a slider, a label displaying the slider's value and an info icon with tooltip info about the parameter
     */
    private class ParameterSetter extends CustomComponent {
        VerticalLayout layout;
        Slider slider;
        Button infoButton;
        Label valueDisplay;

        public ParameterSetter(String caption,
                               int minValue, int maxValue, int resolution, String tooltipText, String imagePath) {
            //Setup slider
            slider = new Slider(caption);
            slider.setMin(minValue);
            slider.setMax(maxValue);
            slider.setResolution(resolution);
            slider.addValueChangeListener(vce -> {
                if (vce.isUserOriginated() &&
                        //Make sure the event actually came from a slider in the PrePrediction Part
                        slider.getCaption().matches(".*Step.*|.*Factor.*")) {
                    presetSelection.setSelectedItem(Globals.PARAMETERS_CUSTOM);
                }
            });
            //Setup valueDisplay, bind label to value of slider
            valueDisplay = new Label();
            slider.addValueChangeListener(event -> {
                if (resolution == 0) {
                    valueDisplay.setValue(String.valueOf(event.getValue().intValue()));
                } else {
                    valueDisplay.setValue(String.valueOf(event.getValue()));
                }

                if (caption.contains("Shift") || caption.contains("UTR") || caption.contains("Clustering Distance")) {
                    if (event.getValue() == 1) {
                        valueDisplay.setValue(valueDisplay.getValue() + " Base Pair");
                    } else {
                        valueDisplay.setValue(valueDisplay.getValue() + " Base Pairs");
                    }
                }
            });

            //Setup infoButton with helpGraphic as Tooltip
            infoButton = new Button(VaadinIcons.INFO_CIRCLE);
            infoButton.addStyleNames(
                    ValoTheme.BUTTON_ICON_ONLY,
                    ValoTheme.BUTTON_BORDERLESS,
                    ValoTheme.BUTTON_ICON_ALIGN_TOP,
                    ValoTheme.BUTTON_SMALL);
            infoButton.setDescription(tooltipText +
                    (!imagePath.equals("") ? "<br><br><img src=\"" + imagePath + "\" style=\"width: 75%; height: 75%\"/>" : ""), ContentMode.HTML);
            //Create layout, put all components there and set as root
            layout = new VerticalLayout();
            layout.addComponents(new HorizontalLayout(slider, infoButton), valueDisplay);
            layout.setComponentAlignment(valueDisplay, Alignment.BOTTOM_CENTER);
            layout.addStyleNames("parameter-setter");
            setCompositionRoot(layout);
        }
    }

    /**
     * In this method, all the parts of the layout are created with their components and their styles.
     */
    private void createParameterLayouts() {

        //Normalization Part
        normalizationPercentile = new ParameterSetter(
                "Normalization Percentile", 0, 1, 1,
                Globals.NORMALIZATION_PERCENTILE_TEXT, "");
        enrichmentNormalizationPercentile = new ParameterSetter(
                "Enrichment Normalization Percentile", 0, 1, 1,
                Globals.ENRICHMENT_NORMALIZATION_PERCENTILE_TEXT, "");
        writeNormalizedGraphs = new CheckBox("<b>Write Normalized Graph Files</b>");
        writeNormalizedGraphs.setCaptionAsHtml(true);
        writeNormalizedInfo = new Button(VaadinIcons.INFO_CIRCLE);
        writeNormalizedInfo.addStyleNames(
                ValoTheme.BUTTON_ICON_ONLY,
                ValoTheme.BUTTON_BORDERLESS,
                ValoTheme.BUTTON_ICON_ALIGN_TOP,
                ValoTheme.BUTTON_SMALL);
        writeNormalizedInfo.setDescription(
                Globals.WRITE_GRAPHS_TEXT, ContentMode.HTML);


        //Pre-Prediction Part

        //Preset
        presetSelection = new RadioButtonGroup<>("<b>Choose Parameter Preset:</b>");
        presetSelection.setCaptionAsHtml(true);
        //TODO: Add some kind of separator between Presets and "Custom"
        presetSelection.setItems("Very Specific", "More Specific", "Default", "More Sensitive", "Very Sensitive", Globals.PARAMETERS_CUSTOM);
        presetSelection.addStyleName("my-radio-button-group");
        //Setup infoButton with helpGraphic as Tooltip
        presetInfo = new Button(VaadinIcons.INFO_CIRCLE);
        presetInfo.addStyleNames(
                ValoTheme.BUTTON_ICON_ONLY,
                ValoTheme.BUTTON_BORDERLESS,
                ValoTheme.BUTTON_ICON_ALIGN_TOP,
                ValoTheme.BUTTON_SMALL);
        presetInfo.setDescription(
                Globals.SENSITIVITY_SPECIFICITY_TEXT + "<br><br><img src=\"" + Globals.SENSITIVITY_SPECIFICITY_IMAGE + "\" style=\"width: 75%; height: 75%\"/>", ContentMode.HTML);
        setupPresetListener();

        //Prediction Parameters
        stepHeight = new ParameterSetter("Step Height",
                0, 1, 1, Globals.STEP_HEIGHT_TEXT,
                Globals.STEP_HEIGHT_IMAGE);
        stepHeightReduction = new ParameterSetter("Step Height Reduction",
                0, 1, 1, Globals.STEP_HEIGHT_REDUCTION_TEXT,
                "");
        stepHeight.slider.addValueChangeListener(vce -> stepHeightReduction.slider.setMax(vce.getValue()));

        stepFactor = new ParameterSetter("Step Factor",
                1, 5, 1, Globals.STEP_FACTOR_TEXT,
                Globals.STEP_FACTOR_IMAGE);
        stepFactorReduction = new ParameterSetter("Step Factor Reduction",
                0, 2, 1, Globals.STEP_FACTOR_REDUCTION_TEXT,
                "");
        stepFactor.slider.addValueChangeListener(vce -> stepFactorReduction.slider.setMax(vce.getValue()));

        enrichmentFactor = new ParameterSetter("Enrichment Factor",
                0, 10, 1, Globals.ENRICHMENT_FACTOR_TEXT,
                Globals.ENRICHMENT_FACTOR_IMAGE);
        processingSiteFactor = new ParameterSetter("Processing Site Factor",
                0, 10, 1, Globals.PROCESSING_SITE_FACTOR_TEXT,
                "");
        stepLength = new ParameterSetter("Step Length",
                0, 100, 0, Globals.STEP_LENGTH_TEXT,
                "");
        baseHeight = new ParameterSetter("Base Height (disabled by default)",
                0, 1, 0, Globals.BASE_HEIGHT_TEXT,
                "");
        baseHeight.setEnabled(false);


        //Post-prediction Part
        clusterMethod = new ComboBox<>("Clustering Method");
        clusterMethod.setItems(Globals.CLUSTER_METHOD_HIGHEST, Globals.CLUSTER_METHOD_FIRST);
        clusterMethodInfo = new Button(VaadinIcons.INFO_CIRCLE);
        clusterMethodInfo.addStyleNames(
                ValoTheme.BUTTON_ICON_ONLY,
                ValoTheme.BUTTON_BORDERLESS,
                ValoTheme.BUTTON_ICON_ALIGN_TOP,
                ValoTheme.BUTTON_SMALL);
        clusterMethodInfo.setDescription(
                Globals.CLUSTER_METHOD_INFO, ContentMode.HTML);
        clusteringDistance = new ParameterSetter("TSS Clustering Distance", 0, 3, 0,
                Globals.CLUSTERING_DISTANCE_TEXT, "");

        crossDatasetShift = new ParameterSetter("Allowed Cross-Genome Shift", 0, 3, 0,
                Globals.CROSS_DATASET_SHIFT_TEXT, "");

        crossReplicateShift = new ParameterSetter("Allowed Cross-Replication Shift", 0, 3, 0,
                Globals.CROSS_REPLICATE_SHIFT_TEXT, "");
        matchingReplicates = new ComboBox<>("Matching Replicates");
        matchingReplicates.setItems(IntStream.rangeClosed(1, presenter.getNumberOfReplicates())
                .boxed().collect(Collectors.toList()));
        matchingReplicatesInfo = new Button(VaadinIcons.INFO_CIRCLE);
        matchingReplicatesInfo.addStyleNames(
                ValoTheme.BUTTON_ICON_ONLY,
                ValoTheme.BUTTON_BORDERLESS,
                ValoTheme.BUTTON_ICON_ALIGN_TOP,
                ValoTheme.BUTTON_SMALL);
        matchingReplicatesInfo.setDescription(Globals.MATCHING_REPLICATES_TEXT, ContentMode.HTML);
        HorizontalLayout utrLengths = new HorizontalLayout();
        utrLength = new ParameterSetter("UTR length", 0, 1000, 0,
                Globals.UTR_LENGTH_TEXT, "");
        antisenseUtrLength = new ParameterSetter("Antisense UTR length", 0, 1000, 0,
                Globals.ANTISENSE_UTR_LENGTH_TEXT, "");
        utrLengths.addComponents(utrLength, antisenseUtrLength);

        //Layouts
        normalizationLayout = new VerticalLayout(
                new Label("<b>Normalization</b>", ContentMode.HTML),
                new InfoBar(Globals.NORMALIZATION_INFO),
                new HorizontalLayout(writeNormalizedGraphs, writeNormalizedInfo),
                new HorizontalLayout(normalizationPercentile, enrichmentNormalizationPercentile));
        Label adjustByHand = new Label("<b>Adjust Parameters by Hand:</b>", ContentMode.HTML);
        adjustByHand.setStyleName("v-caption");
        VerticalLayout predictionParametersLayout = new VerticalLayout(
                adjustByHand,
                new HorizontalLayout(stepHeight, stepHeightReduction, stepFactor, stepFactorReduction),
                new HorizontalLayout(enrichmentFactor, processingSiteFactor, stepLength, baseHeight));
        predictionParametersLayout.addStyleNames("prediction-parameters-layout");
        predictionParametersLayout.setMargin(false);
        predictionParametersLayout.setId("PredictionParameters");
        prePredictionLayout = new VerticalLayout(
                new Label("<b>Pre-prediction</b>", ContentMode.HTML),
                new InfoBar(Globals.PRE_PREDICTION_INFO),
                new HorizontalLayout(new HorizontalLayout(presetSelection, presetInfo),
                        predictionParametersLayout));
        postPredictionLayout = new VerticalLayout(
                new Label("<b>Post-Prediction</b>", ContentMode.HTML),
                new InfoBar(Globals.POST_PREDICTION_INFO),
                new HorizontalLayout(matchingReplicates, matchingReplicatesInfo, utrLengths),
                new HorizontalLayout(crossDatasetShift, crossReplicateShift),
                new HorizontalLayout(clusterMethod, clusterMethodInfo, clusteringDistance));

        normalizationLayout.addStyleName("parameter-section");
        prePredictionLayout.addStyleName("parameter-section");
        postPredictionLayout.addStyleName("parameter-section");

    }


    public RadioButtonGroup<String> getPresetSelection() {
        return presetSelection;
    }

    public Slider getStepHeight() {
        return stepHeight.slider;
    }

    public Slider getStepHeightReduction() {
        return stepHeightReduction.slider;
    }

    public Slider getStepFactor() {
        return stepFactor.slider;
    }

    public Slider getStepFactorReduction() {
        return stepFactorReduction.slider;
    }

    public Slider getEnrichmentFactor() {
        return enrichmentFactor.slider;
    }

    public Slider getProcessingSiteFactor() {
        return processingSiteFactor.slider;
    }

    public Slider getStepLength() {
        return stepLength.slider;
    }

    public Slider getBaseHeight() {
        return baseHeight.slider;
    }

    public Slider getNormalizationPercentile() {
        return normalizationPercentile.slider;
    }

    public Slider getEnrichmentNormalizationPercentile() {
        return enrichmentNormalizationPercentile.slider;
    }

    public ComboBox<String> getClusterMethod() {
        return clusterMethod;
    }

    public Slider getClusteringDistance() {
        return clusteringDistance.slider;
    }

    public Slider getCrossDatasetShift() {
        return crossDatasetShift.slider;
    }

    public Slider getCrossReplicateShift() {
        return crossReplicateShift.slider;
    }

    public ComboBox<Integer> getMatchingReplicates() {
        return matchingReplicates;
    }

    public Slider getUtrLength() {
        return utrLength.slider;
    }

    public Slider getAntisenseUtrLength() {
        return antisenseUtrLength.slider;
    }

    public CheckBox getWriteNormalizedGraphs() {
        return writeNormalizedGraphs;
    }
}
