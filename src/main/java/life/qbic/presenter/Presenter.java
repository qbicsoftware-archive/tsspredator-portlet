package life.qbic.presenter;

import com.vaadin.data.*;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.ui.Notification;
import life.qbic.model.Globals;
import life.qbic.model.Model;
import life.qbic.model.beans.*;
import life.qbic.model.config.ConfigFile;
import life.qbic.view.MainView;
import life.qbic.view.panels.ConditionDataPanel;
import life.qbic.view.panels.DataPanel;
import life.qbic.view.panels.GenomeDataPanel;

import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jmueller
 */
public class Presenter {
    private Model model;
    private MainView view;
    private Binder<ConfigFile> configFileBinder;
    private final static Logger presenterLogger = Logger.getLogger(Presenter.class.getName());


    //The parameters of TSS prediction can be set depending on one of the five presets
    public enum Preset {
        VERY_SPECIFIC, MORE_SPECIFIC, DEFAULT, MORE_SENSITIVE, VERY_SENSITIVE
    }

    //The initial preset is "DEFAULT"
    private Preset preset = Preset.DEFAULT;

    /**
     * In the constructor of the presenter, the config file is created with an empty GenomeList
     * The numbers of datasets and replicates are both initialized to 1
     * The Binder that connects the view and the configFile is initialized
     */
    public Presenter(MainView view, Model model) {
        this.view = view;
        this.model = model;
        view.setPresenter(this);
        addDatasets(1);
        addReplicates(1);
        setInitialConfigParameters();
        view.createView();
        configFileBinder = new Binder<>();
        configFileBinder.setBean(model.getConfigFile());

        view.getParametersPanel().getPresetSelection().setSelectedItem("Default");
        view.getGenomeDataPanel().initAccordion();
        displayData();

        //Initialize Bindings between view and model via a configFileBinder
        connectGeneralSettingsToConfigModel();
        connectStaticDataSettingsToConfigModel();
        connectParameterSettingsToConfigModel();


    }

    /**
     * Connects the general settings of the view to the config file via the configFileBinder:
     * Each binding is labelled in the code as follows:
     * <Config File Value> -> <View Component>
     */
    private void connectGeneralSettingsToConfigModel() {
        //Project Name -> Project Name Grid
        configFileBinder.forField(view.getGeneralConfigPanel().getProjectGrid().asSingleSelect())
                .withValidator(projectBean -> {
                    if (projectBean.toString().equals("null (null)")) {
                        view.getGeneralConfigPanel().getProjectGrid().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please select a project by clicking on it!";
                            }
                        });
                        return false;
                    } else {
                        view.getGeneralConfigPanel().getProjectGrid().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, ProjectBean>) configFile -> {
                            return new ProjectBean(); //TODO: Return something useful here
                        },
                        (Setter<ConfigFile, ProjectBean>) (configFile, projectBean) -> configFile.setProjectName(projectBean.getName()));

        //Project Type (Mode) -> Project Type Button Group
        configFileBinder.forField(view.getGeneralConfigPanel().getProjectTypeButtonGroup())
                .bind(new ValueProvider<ConfigFile, String>() {
                          @Override
                          public String apply(ConfigFile configFile) {
                              return configFile.isModeConditions() ? Globals.COMPARE_CONDITIONS : Globals.COMPARE_GENOMES;
                          }
                      },
                        new Setter<ConfigFile, String>() {
                            @Override
                            public void accept(ConfigFile configFile, String s) {
                                view.getGenomeDataPanel().getNumberOfDatasetsBox().setValue(1);
                                view.getGenomeDataPanel().getNumberOfReplicatesBox().setValue(1);
                                view.getConditionDataPanel().getNumberOfDatasetsBox().setValue(1);
                                view.getConditionDataPanel().getNumberOfReplicatesBox().setValue(1);


                                boolean isModeConditions = s.equals(Globals.COMPARE_CONDITIONS);
                                //Hide one of the two DataPanels and show the other one:
                                //The genomeDataPanel has tab index 1, the conditionDataPanel has tab index 2
                                // in the contentAccordion
                                view.getContentAccordion().getTab(1).setVisible(!isModeConditions);
                                view.getContentAccordion().getTab(2).setVisible(isModeConditions);
                                view.getParametersPanel().getCrossDatasetShift().setCaption(
                                        isModeConditions ? "Allowed Cross-Condition Shift" : "Allowed Cross-Genome Shift"
                                );
                                configFile.setModeConditions(isModeConditions);
                                configFile.setNumberOfDatasets(1);
                                configFile.setNumberOfReplicates(1);
                                view.getGeneralConfigPanel().getAlignmentFileGrid().setVisible(!isModeConditions);
                                if (isModeConditions) {
                                    view.getGenomeDataPanel().getDatasetAccordion().removeAllComponents();
                                    view.getConditionDataPanel().initAccordion();
                                } else {
                                    view.getConditionDataPanel().getDatasetAccordion().removeAllComponents();
                                    view.getGenomeDataPanel().initAccordion();
                                }


                            }
                        }
                );

        //Alignment File -> Alignment File Grid
        configFileBinder.forField(view.getGeneralConfigPanel().getAlignmentFileGrid().asSingleSelect())
                .withValidator(alignmentFileBean -> {
                    if (alignmentFileBean.toString().equals("null (null, 0kB)")) {
                        view.getGeneralConfigPanel().getAlignmentFileGrid().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please select an alignment file by clicking on it!";
                            }
                        });
                        return false;
                    } else {
                        view.getGeneralConfigPanel().getAlignmentFileGrid().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, AlignmentFileBean>) configFile -> {
                            return new AlignmentFileBean(); //TODO: Return something useful here
                        },
                        (Setter<ConfigFile, AlignmentFileBean>) (configFile, alignmentFileBean) -> configFile.setAlignmentFile(alignmentFileBean.getName()));

    }

    /**
     * Connects the data settings of the view to the config file via the configFileBinder:
     * All the static parameters (i.e. all parameters except the dataset and replicate parameters, which might
     * increase or decrease in number at runtime) are bound here. That means, if a parameter, e.g. a slider,
     * is changed in the view, the change is directly applied to the config file.
     * Each binding is labelled in the code as follows:
     * <Config File Value> -> <View Component>
     */
    private void connectStaticDataSettingsToConfigModel() {
        //Number of Datasets -> Number of Datasets Box (Genome Data Panel)
        configFileBinder.forField(view.getGenomeDataPanel().getNumberOfDatasetsBox())
                .asRequired("Please set a number")
                .withValidator(new IntegerRangeValidator("Please set at least to 1", 1, Integer.MAX_VALUE))
                .bind(
                        (ValueProvider<ConfigFile, Integer>) configFile1 -> configFile1.getNumberOfDatasets(),
                        (Setter<ConfigFile, Integer>) (configFile, number) -> {
                            int oldDatasetCount = configFile.getNumberOfDatasets();
                            //Add or remove datasets in the ConfigFile so they match the given number
                            int delta = number - oldDatasetCount;
                            configFile.setNumberOfDatasets(number);
                            if (delta > 0) {
                                addDatasets(delta);
                            } else {
                                removeDatasets(-delta);
                            }
                            //Update view
                            view.getGenomeDataPanel().updateAccordion(oldDatasetCount, getNumberOfReplicates());

                        });

        //Number of Datasets -> Number of Datasets Box (Condition Data Panel)
        configFileBinder.forField(view.getConditionDataPanel().getNumberOfDatasetsBox())
                .asRequired("Please set a number")
                .withValidator(new IntegerRangeValidator("Please set at least to 1", 1, Integer.MAX_VALUE))
                .bind(
                        (ValueProvider<ConfigFile, Integer>) configFile1 -> configFile1.getNumberOfDatasets(),
                        (Setter<ConfigFile, Integer>) (configFile, number) -> {
                            int oldDatasetCount = configFile.getNumberOfDatasets();
                            //Add or remove datasets in the ConfigFile so they match the given number
                            int delta = number - oldDatasetCount;
                            configFile.setNumberOfDatasets(number);
                            if (delta > 0)
                                addDatasets(delta);
                            else
                                removeDatasets(-delta);
                            //Update view
                            view.getConditionDataPanel().updateAccordion(oldDatasetCount, getNumberOfReplicates());
                        });

        //Number of Replicates -> Number of Replicates Box (Genome Data Panel)
        configFileBinder.forField(view.getGenomeDataPanel().getNumberOfReplicatesBox())
                .asRequired("Please set a number")
                .withValidator(new IntegerRangeValidator("Please set at least to 1", 1, Integer.MAX_VALUE))
                .bind((ValueProvider<ConfigFile, Integer>) configFile1 -> configFile1.getNumberOfReplicates(),
                        (Setter<ConfigFile, Integer>) (configFile, number) -> {
                            int oldReplicateCount = configFile.getNumberOfReplicates();
                            int delta = number - oldReplicateCount;
                            configFile.setNumberOfReplicates(number);
                            //Add or remove replicates so they match the given number
                            if (delta > 0)
                                addReplicates(delta);
                            else
                                removeReplicates(-delta);
                            //Update view
                            view.getGenomeDataPanel().updateAccordion(getNumberOfDatasets(), oldReplicateCount);
                            //Update range of the 'matching replicates' slider in the ParametersPanel
                            view.getParametersPanel().getMatchingReplicates().setItems(IntStream.rangeClosed(1, getNumberOfReplicates())
                                    .boxed().collect(Collectors.toList()));
                        });

        //Number of Replicates -> Number of Replicates Box (Condition Data Panel)
        configFileBinder.forField(view.getConditionDataPanel().getNumberOfReplicatesBox())
                .asRequired("Please set a number")
                .withValidator(new IntegerRangeValidator("Please set at least to 1", 1, Integer.MAX_VALUE))
                .bind(configFile1 -> configFile1.getNumberOfReplicates(),
                        (Setter<ConfigFile, Integer>) (configFile, number) -> {
                            int oldReplicateCount = configFile.getNumberOfReplicates();
                            int delta = number - oldReplicateCount;
                            configFile.setNumberOfReplicates(number);
                            //Add or remove replicates so they match the given number
                            if (delta > 0)
                                addReplicates(delta);
                            else
                                removeReplicates(-delta);
                            view.getConditionDataPanel().updateAccordion(getNumberOfDatasets(), oldReplicateCount);
                        });

        //Bindings for conditionDataPanel exclusively
        //Fasta File -> Fasta Grid
        configFileBinder.forField(view.getConditionDataPanel().getFastaGrid().asSingleSelect())
                .withValidator(fastaFileBean -> {
                    if (fastaFileBean.toString().equals("null (null, 0kB)")) {
                        view.getConditionDataPanel().getFastaGrid().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please select a Fasta file by double clicking it!";
                            }
                        });
                        return false;
                    } else {
                        view.getConditionDataPanel().getFastaGrid().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, FastaFileBean>) configFile -> new FastaFileBean(),
                        (Setter<ConfigFile, FastaFileBean>) (configFile, fastaFileBean) -> configFile.setConditionFasta(fastaFileBean.getName()));
        //Alignment File -> GFF Grid
        configFileBinder.forField(view.getConditionDataPanel().getGffGrid().asSingleSelect())
                .withValidator(annotationFileBean -> {
                    if (annotationFileBean.toString().equals("null (null, 0kB)")) {
                        view.getConditionDataPanel().getGffGrid().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please select an annotation file by clicking on it!";
                            }
                        });
                        return false;
                    } else {
                        view.getConditionDataPanel().getGffGrid().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, AnnotationFileBean>) configFile -> new AnnotationFileBean(),
                        (Setter<ConfigFile, AnnotationFileBean>) (configFile, annotationFileBean) -> configFile.setConditionGFF(annotationFileBean.getName()));

        //Bind the matching replicates combobox in the parameters panel
        //to the number of replicates comboboxes in the two data panels so the first can't exceed the latter
        view.getGenomeDataPanel().getNumberOfReplicatesBox().addValueChangeListener(vce -> {
            view.getParametersPanel().getMatchingReplicates().setItems(IntStream.rangeClosed(1, getNumberOfReplicates())
                    .boxed().collect(Collectors.toList()));
        });
        view.getConditionDataPanel().getNumberOfReplicatesBox().addValueChangeListener(vce -> {
            view.getParametersPanel().getMatchingReplicates().setItems(IntStream.rangeClosed(1, getNumberOfReplicates())
                    .boxed().collect(Collectors.toList()));
        });


    }

    /**
     * Connects the parameter settings of the view to the config file via the configFileBinder:
     * Each binding is labelled in the code as follows:
     * <Config File Value> -> <View Component>
     */
    private void connectParameterSettingsToConfigModel() {
        //Write Normalized Graphs? -> Write Normalized Graphs CheckBox
        configFileBinder.forField(view.getParametersPanel().getWriteNormalizedGraphs())
                .bind(ConfigFile::isWriteGraphs,
                        ConfigFile::setWriteGraphs);
        //Step Height -> Step Height Slider
        configFileBinder.forField(view.getParametersPanel().getStepHeight()).bind(
                ConfigFile::getStepHeight,
                ConfigFile::setStepHeight);
        //Step Height Reduction -> Step Height Reduction Slider
        configFileBinder.forField(view.getParametersPanel().getStepHeightReduction()).bind(
                ConfigFile::getStepHeightReduction,
                ConfigFile::setStepHeightReduction);
        //Step Factor -> Step Factor Slider
        configFileBinder.forField(view.getParametersPanel().getStepFactor()).bind(
                ConfigFile::getStepFactor,
                ConfigFile::setStepFactor);
        //Step Factor Reduction -> Step Factor Reduction Slider
        configFileBinder.forField(view.getParametersPanel().getStepFactorReduction()).bind(
                ConfigFile::getStepFactorReduction,
                ConfigFile::setStepFactorReduction);
        //Enrichment Factor -> Enrichment Factor Slider
        configFileBinder.forField(view.getParametersPanel().getEnrichmentFactor()).bind(
                ConfigFile::getEnrichmentFactor,
                ConfigFile::setEnrichmentFactor);
        //Processing Site Factor -> Processing Site Factor Slider
        configFileBinder.forField(view.getParametersPanel().getProcessingSiteFactor()).bind(
                ConfigFile::getProcessingSiteFactor,
                ConfigFile::setProcessingSiteFactor);
        //Step Length -> Step Length Slider
        configFileBinder.forField(view.getParametersPanel().getStepLength())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getStepLength, ConfigFile::setStepLength);
        //Base Height -> Base Height Slider
        configFileBinder.forField(view.getParametersPanel().getBaseHeight()).bind(
                ConfigFile::getBaseHeight,
                ConfigFile::setBaseHeight);
        //Normalization Percentile -> Normalization Percentile Slider
        configFileBinder.forField(view.getParametersPanel().getNormalizationPercentile()).bind(
                ConfigFile::getNormalizationPercentile,
                ConfigFile::setNormalizationPercentile);
        //Enrichment Norm. Percentile -> Enrichment Norm. Percentile Slider
        configFileBinder.forField(view.getParametersPanel().getEnrichmentNormalizationPercentile()).bind(
                ConfigFile::getEnrichmentNormalizationPercentile,
                ConfigFile::setEnrichmentNormalizationPercentile);
        //Cluster Method -> Cluster Method ComboBox
        configFileBinder.forField(view.getParametersPanel().getClusterMethod()).bind(
                ConfigFile::getClusterMethod,
                ConfigFile::setClusterMethod);
        //Clustering Distance -> Clustering Distance Slider
        configFileBinder.forField(view.getParametersPanel().getClusteringDistance())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getTssClusteringDistance, ConfigFile::setTssClusteringDistance);
        //Cross Dataset Shift -> Cross Dataset Shift Slider
        configFileBinder.forField(view.getParametersPanel().getCrossDatasetShift())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getAllowedCrossDatasetShift, ConfigFile::setAllowedCrossDatasetShift);
        //Cross Replicate Shift -> Cross Replicate Shift Slider
        configFileBinder.forField(view.getParametersPanel().getCrossReplicateShift())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getAllowedCrossReplicateShift, ConfigFile::setAllowedCrossReplicateShift);
        //Matching Replicates -> Matching Replicates Slider
        configFileBinder.forField(view.getParametersPanel().getMatchingReplicates())
                .asRequired("Please set a number")
                .bind(
                        ConfigFile::getMatchingReplicates,
                        (configFile, value) -> {
                            if (value != null)
                                configFile.setMatchingReplicates(value);

                        });
        //UTR Length -> UTR Length Slider
        configFileBinder.forField(view.getParametersPanel().getUtrLength())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getUtrLength, ConfigFile::setUtrLength);
        //Antisense UTR Length -> Antisense UTR Length Slider
        configFileBinder.forField(view.getParametersPanel().getAntisenseUtrLength())
                .withConverter(Double::intValue, Integer::doubleValue)
                .bind(ConfigFile::getAntisenseUtrLength, ConfigFile::setAntisenseUtrLength);

    }


    /**
     * Accesses data from the model and hands it over to the view components
     */
    public void displayData() {
        view.getGeneralConfigPanel().getProjectGrid().setItems(model.getProjectBeans());
        view.getGeneralConfigPanel().getAlignmentFileGrid().setItems(model.getAlignmentFileBeans());
        view.getGenomeDataPanel().getFastaFileBeans().addAll(model.getFastaFileBeans());
        view.getGenomeDataPanel().getAnnotationFileBeans().addAll(model.getAnnotationFileBeans());
        view.getGenomeDataPanel().getGraphFileBeans().addAll(model.getGraphFileBeans());
        view.getConditionDataPanel().getFastaGrid().setItems(model.getFastaFileBeans());
        view.getConditionDataPanel().getGffGrid().setItems(model.getAnnotationFileBeans());
        view.getConditionDataPanel().getGraphFileBeans().addAll(model.getGraphFileBeans());
    }

    public void setInitialConfigParameters() {
        ConfigFile configFile = model.getConfigFile();
        configFile.setModeConditions(Globals.IS_CONDITIONS_INIT);
        configFile.setNumberOfDatasets(Globals.NUMBER_OF_DATASETS_INIT);
        configFile.setNumberOfReplicates(Globals.NUMBER_OF_REPLICATES_INIT);
        configFile.setNormalizationPercentile(Globals.NORMALIZATION_PERCENTILE_INIT);
        configFile.setEnrichmentNormalizationPercentile(Globals.ENRICHMENT_NORMALIZATION_PERCENTILE_INIT);
        configFile.setClusterMethod(Globals.CLUSTER_METHOD_INIT);
        configFile.setTssClusteringDistance(Globals.TSS_CLUSTERING_DISTANCE_INIT);
        configFile.setAllowedCrossDatasetShift(Globals.CROSS_DATASET_SHIFT_INIT);
        configFile.setAllowedCrossReplicateShift(Globals.CROSS_REPLICATE_SHIFT_INIT);
        configFile.setMatchingReplicates(Globals.MATCHING_REPLICATES_INIT);
        configFile.setUtrLength(Globals.UTR_LENGTH_INIT);
        configFile.setAntisenseUtrLength(Globals.ANTISENSE_UTR_LENGTH_INIT);
    }

    /**
     * All parameters of one dataset (referred to by its index) are bound to the config file here.
     * Depending on the current mode of the program (conditions/genomes), only the name of the project
     * or name, fasta, gff and alignment id are set.
     * This method is called whenever a new dataset is added to the project.
     */
    public void initDatasetBindings(int index) {
        if (model.getConfigFile().isModeConditions()) {
            ConditionDataPanel.ConditionTab conditionTab = (ConditionDataPanel.ConditionTab) view.getConditionDataPanel().getDatasetTab(index);
            configFileBinder.forField(conditionTab.getNameField()).asRequired("Please enter the name of the condition")
                    .bind(new ValueProvider<ConfigFile, String>() {
                              @Override
                              public String apply(ConfigFile configFile) {
                                  return configFile.getGenomeList().get(index).getName();
                              }
                          },
                            new Setter<ConfigFile, String>() {
                                @Override
                                public void accept(ConfigFile configFile, String name) {
                                    configFile.getGenomeList().get(index).setName(name);
                                }
                            });
        } else {
            GenomeDataPanel.GenomeTab genomeTab = (GenomeDataPanel.GenomeTab) view.getGenomeDataPanel().getDatasetTab(index);
            configFileBinder.forField(genomeTab.getNameField()).asRequired("Please enter the name of the genome")
                    .bind(new ValueProvider<ConfigFile, String>() {
                              @Override
                              public String apply(ConfigFile configFile) {
                                  return configFile.getGenomeList().get(index).getName();
                              }
                          },
                            new Setter<ConfigFile, String>() {
                                @Override
                                public void accept(ConfigFile configFile, String name) {
                                    configFile.getGenomeList().get(index).setName(name);
                                }
                            });

            configFileBinder.forField(genomeTab.getFastaGrid().asSingleSelect())
                    .withValidator(fastaFileBean -> {
                        if (fastaFileBean.toString().equals("null (null, 0kB)")) {
                            genomeTab.getFastaGrid().setComponentError(new ErrorMessage() {
                                @Override
                                public ErrorLevel getErrorLevel() {
                                    return ErrorLevel.ERROR;
                                }

                                @Override
                                public String getFormattedHtmlMessage() {
                                    return "Please select the fasta file of this genome (*.fasta)!";
                                }
                            });
                            return false;
                        } else {
                            genomeTab.getFastaGrid().setComponentError(null);
                            return true;
                        }
                    }, "")
                    .bind((ValueProvider<ConfigFile, FastaFileBean>) configFile -> new FastaFileBean(),
                            (Setter<ConfigFile, FastaFileBean>) (configFile, fastaFileBean) -> configFile.getGenomeList().get(index).setFasta(fastaFileBean.getName()));

            configFileBinder.forField(genomeTab.getIdField()).asRequired("Please enter the alignment id of this genome")
                    .bind(new ValueProvider<ConfigFile, String>() {
                              @Override
                              public String apply(ConfigFile configFile) {
                                  return configFile.getGenomeList().get(index).getAlignmentID();
                              }
                          },
                            new Setter<ConfigFile, String>() {
                                @Override
                                public void accept(ConfigFile configFile, String id) {
                                    configFile.getGenomeList().get(index).setAlignmentID(id);
                                }
                            });

            configFileBinder.forField(genomeTab.getAnnotationFileGrid().asSingleSelect())
                    .withValidator(annotationFileBean -> {
                        if (annotationFileBean.toString().equals("null (null, 0kB)")) {
                            genomeTab.getAnnotationFileGrid().setComponentError(new ErrorMessage() {
                                @Override
                                public ErrorLevel getErrorLevel() {
                                    return ErrorLevel.ERROR;
                                }

                                @Override
                                public String getFormattedHtmlMessage() {
                                    return "Please select the annotation file of this genome (*.gff)";
                                }
                            });
                            return false;
                        } else {
                            genomeTab.getAnnotationFileGrid().setComponentError(null);
                            return true;
                        }
                    }, "")
                    .bind((ValueProvider<ConfigFile, AnnotationFileBean>) configFile -> new AnnotationFileBean(),
                            (Setter<ConfigFile, AnnotationFileBean>) (configFile, annotationFileBean) -> configFile.getGenomeList().get(index).setGff(annotationFileBean.getName()));
        }


    }

    /**
     * All parameters of one replicate (referred to by its index) are bound to the config file here.
     * This method is called whenever a new replicate is added to the project.
     */
    public void initReplicateBindings(int datasetIndex, int replicateIndex) {
        DataPanel.ReplicateTab replicateTab;
        if (model.getConfigFile().isModeConditions()) {
            replicateTab = view.getConditionDataPanel().getDatasetTab(datasetIndex).getReplicateTab(replicateIndex);
        } else {
            replicateTab = view.getGenomeDataPanel().getDatasetTab(datasetIndex).getReplicateTab(replicateIndex);
        }

        //Treated Coding Strand
        configFileBinder.forField(replicateTab.getTreatedCoding().asSingleSelect())
                .withValidator(graphFileBean -> {
                    if (graphFileBean.toString().equals("null (null, 0kB)")) {
                        replicateTab.getTreatedCoding().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please drag a graph file here!";
                            }
                        });
                        return false;
                    } else {
                        replicateTab.getTreatedCoding().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, GraphFileBean>) configFile -> {
                            return new GraphFileBean(); //TODO: Do something useful here
                        }, (Setter<ConfigFile, GraphFileBean>) (configFile, graphFileBean) -> {
                            if (graphFileBean != null) {
                                configFile.getGenomeList().get(datasetIndex)
                                        .getReplicateList().get(replicateIndex)
                                        .setTreatedCodingStrand(graphFileBean.getName());
                            }
                        }
                );

        //Treated Template Strand
        configFileBinder.forField(replicateTab.getTreatedTemplate().asSingleSelect())
                .withValidator(graphFileBean -> {
                    if (graphFileBean.toString().equals("null (null, 0kB)")) {
                        replicateTab.getTreatedTemplate().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please drag a graph file here!";
                            }
                        });
                        return false;
                    } else {
                        replicateTab.getTreatedTemplate().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, GraphFileBean>) configFile -> new GraphFileBean(),
                        (Setter<ConfigFile, GraphFileBean>) (configFile, graphFileBean) -> {
                            if (graphFileBean != null) {
                                configFile.getGenomeList().get(datasetIndex)
                                        .getReplicateList().get(replicateIndex)
                                        .setTreatedTemplateStrand(graphFileBean.getName());
                            }
                        });

        //Untreated Coding Strand
        configFileBinder.forField(replicateTab.getUntreatedCoding().asSingleSelect())
                .withValidator(graphFileBean -> {
                    if (graphFileBean.toString().equals("null (null, 0kB)")) {
                        replicateTab.getUntreatedCoding().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please drag a graph file here!";
                            }
                        });
                        return false;
                    } else {
                        replicateTab.getUntreatedCoding().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, GraphFileBean>) configFile -> new GraphFileBean(),
                        (Setter<ConfigFile, GraphFileBean>) (configFile, graphFileBean) -> {
                            if (graphFileBean != null) {
                                configFile.getGenomeList().get(datasetIndex)
                                        .getReplicateList().get(replicateIndex)
                                        .setUntreatedCodingStrand(graphFileBean.getName());
                            }
                        });

        //Untreated Template Strand
        configFileBinder.forField(replicateTab.getUntreatedTemplate().asSingleSelect())
                .withValidator(graphFileBean -> {
                    if (graphFileBean.toString().equals("null (null, 0kB)")) {
                        replicateTab.getUntreatedTemplate().setComponentError(new ErrorMessage() {
                            @Override
                            public ErrorLevel getErrorLevel() {
                                return ErrorLevel.ERROR;
                            }

                            @Override
                            public String getFormattedHtmlMessage() {
                                return "Please drag a graph file here!";
                            }
                        });
                        return false;
                    } else {
                        replicateTab.getUntreatedTemplate().setComponentError(null);
                        return true;
                    }
                }, "")
                .bind((ValueProvider<ConfigFile, GraphFileBean>) configFile -> new GraphFileBean(),
                        (Setter<ConfigFile, GraphFileBean>) (configFile, graphFileBean) -> {
                            if (graphFileBean != null) {
                                configFile.getGenomeList().get(datasetIndex)
                                        .getReplicateList().get(replicateIndex)
                                        .setUntreatedTemplateStrand(graphFileBean.getName());
                            }
                        });
    }

    /**
     * Adds a number of datasets to the config File; automatically adds the correct number of replicates to each of the new datasets
     *
     * @param datasetsToAdd
     */
    private void addDatasets(int datasetsToAdd) {
        model.addDatasets(datasetsToAdd);

    }


    /**
     * Removes a number of datasets from the config File
     *
     * @param datasetsToRemove
     */
    public void removeDatasets(int datasetsToRemove) {
        model.removeDatasets(datasetsToRemove);
    }


    /**
     * Adds a number of replicates to EACH genome in the config File
     * Note that this method is NOT called by addDatasets, since it doesn't distinguish between new and old datasets
     *
     * @param replicatesToAdd
     */
    public void addReplicates(int replicatesToAdd) {
        model.addReplicates(replicatesToAdd);
    }

    /**
     * Removes a number of replicates from EACH genome in the config file
     *
     * @param replicatesToRemove
     */
    public void removeReplicates(int replicatesToRemove) {
        model.removeReplicates(replicatesToRemove);
    }

    /**
     * Sets the replicate id of a replicate that is part of a dataset, both specified by their indices
     *
     * @param datasetIndex
     * @param replicateIndex
     * @param id
     */
    public void updateReplicateID(int datasetIndex, int replicateIndex, String id) {
        model.getConfigFile()
                .getGenomeList()
                .get(datasetIndex)
                .getReplicateList()
                .get(replicateIndex)
                .setReplicateID(id);

    }

    /**
     * This method uses the configFile object to generate an actual config file.
     * First, the configFileBinder is validated and optionally, an error message is displayed.
     * Second, the configFile.toString() is called and its output written to a file, which is then returned
     *
     * @return
     */
    public File produceConfigFile() {
        File file = new File(Globals.CONFIG_FILE_TMP_PATH);
        //Check all validators and fields that are set as required
        BinderValidationStatus<ConfigFile> validationStatus = configFileBinder.validate();
        //There will always be 'silent' errors because of non-visible fields.
        //If we're in condition mode, it's 3 errors, in genome mode it's 4 errors
        //TODO: It's bad practice to hard-code the silent error numbers - is there a way to work around this?
        int silentErrors = model.getConfigFile().isModeConditions() ? 0 : 2;
        if (validationStatus.getValidationErrors().size() > silentErrors) {
            Notification.show("Your configuration couldn't be saved because there are errors (" + (validationStatus.getValidationErrors().size() - silentErrors)
                    + "). Please check the fields marked with a red '!'.");
        } else {
            try {
                String configText = model.getConfigFile().toString();
                FileWriter writer = new FileWriter(file);
                writer.write(configText);
                writer.close();
                //Enable the download button
                view.getDownloadButton().setEnabled(true);

            } catch (Exception e) {
                presenterLogger.log(Level.ALL, "Error producing config file:" + e.getMessage());
                //Disable the download button
                view.getDownloadButton().setEnabled(false);
            }
        }
        return file;

    }

    /**
     * Sets the TSS prediction parameters according to the chosen preset
     */
    public void applyPresetParameters() {
        if (preset == null) { //Happens when custom is selected
            return;
        }
        ConfigFile configFile = model.getConfigFile();
        switch (preset) {

            case VERY_SPECIFIC:
                configFile.setStepHeight(1);
                configFile.setStepHeightReduction(0.5);
                configFile.setStepFactor(2);
                configFile.setStepFactorReduction(0.5);
                configFile.setEnrichmentFactor(3);
                configFile.setProcessingSiteFactor(1);
                configFile.setStepLength(0);
                configFile.setBaseHeight(0);
                break;
            case MORE_SPECIFIC:
                configFile.setStepHeight(0.5);
                configFile.setStepHeightReduction(0.2);
                configFile.setStepFactor(2);
                configFile.setStepFactorReduction(0.5);
                configFile.setEnrichmentFactor(2);
                configFile.setProcessingSiteFactor(1.2);
                configFile.setStepLength(0);
                configFile.setBaseHeight(0);
                break;
            case DEFAULT:
                configFile.setStepHeight(0.3);
                configFile.setStepHeightReduction(0.2);
                configFile.setStepFactor(2);
                configFile.setStepFactorReduction(0.5);
                configFile.setEnrichmentFactor(2);
                configFile.setProcessingSiteFactor(1.5);
                configFile.setStepLength(0);
                configFile.setBaseHeight(0);
                break;
            case MORE_SENSITIVE:
                configFile.setStepHeight(0.2);
                configFile.setStepHeightReduction(0.15);
                configFile.setStepFactor(1.5);
                configFile.setStepFactorReduction(0.5);
                configFile.setEnrichmentFactor(1.5);
                configFile.setProcessingSiteFactor(2);
                configFile.setStepLength(0);
                configFile.setBaseHeight(0);
                break;
            case VERY_SENSITIVE:
                configFile.setStepHeight(0.1);
                configFile.setStepHeightReduction(0.09);
                configFile.setStepFactor(1);
                configFile.setStepFactorReduction(0.);
                configFile.setEnrichmentFactor(1);
                configFile.setProcessingSiteFactor(3);
                configFile.setStepLength(0);
                configFile.setBaseHeight(0);
                break;

        }
        //This line makes sure the other parameters are initialized with valid values
        configFileBinder.readBean(configFile);

    }

    public void setView(MainView view) {
        this.view = view;
    }

    public Preset getPreset() {
        return preset;
    }

    public void setPreset(Preset preset) {
        this.preset = preset;
    }

    public boolean isModeConditions() {
        return model.getConfigFile().isModeConditions();
    }

    public int getNumberOfDatasets() {
        return model.getConfigFile().getNumberOfDatasets();
    }

    public int getNumberOfReplicates() {
        return model.getConfigFile().getNumberOfReplicates();
    }
}
