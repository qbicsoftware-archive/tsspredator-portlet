package life.qbic.view.panels;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import life.qbic.model.Globals;
import life.qbic.model.beans.GraphFileBean;
import life.qbic.presenter.Presenter;
import life.qbic.view.InfoBar;
import life.qbic.view.MyGraphFileGrid;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In this abstract class, the parts of the DataPanel which are common to both
 * the genome and the condition variant of the workflow are defined.
 * The particular parts are in the inheriting classes GenomeDataPanel or ConditionDataPanel, respectively.
 *
 * @author jmueller
 */
public abstract class DataPanel extends CustomComponent {
    private Presenter presenter;
    private Panel dataPanel;
    Layout contentLayout, wrapperLayout;
    Accordion datasetAccordion;
    ComboBox<Integer> numberOfDatasetsBox;
    ComboBox<Integer> numberOfReplicatesBox;
    private List<GraphFileBean> graphFileBeans = new LinkedList<>();


    public DataPanel(Presenter presenter) {
        dataPanel = designPanel();
        setCompositionRoot(dataPanel);
        this.presenter = presenter;
    }

    /**
     * The panel is designed as a Vertical Layout "wrapperLayout".
     * The common parts that are initialized here are two comboboxes where the user can select how many
     * datasets and replicates he has and the Accordion where the datasets are listed.
     * The particular contents of the wrapperLayout are not added here, but in the constructors of
     * GenomeDataPanel and ConditionDataPanel
     *
     * @return
     */
    private Panel designPanel() {
        Panel panel = new Panel();
        contentLayout = new FormLayout();
        wrapperLayout = new VerticalLayout();
        numberOfDatasetsBox = new ComboBox<>();
        numberOfReplicatesBox = new ComboBox<>("Select number of Replicates");


        List<Integer> possibleDatasetNumber =
                IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        numberOfDatasetsBox.setItems(possibleDatasetNumber);

        List<Integer> possibleReplicateNumber =
                IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        numberOfReplicatesBox.setItems(possibleReplicateNumber);

        datasetAccordion = new Accordion();
        datasetAccordion.setWidth("100%");

        panel.setContent(wrapperLayout);
        return panel;
    }

    /**
     * The dataset accordion is always initialized with one dataset (genome/condition) and one replicate.
     * Initialization happens at startup as well as every time the user switches between modes.
     */
    public void initAccordion() {
        if (this instanceof GenomeDataPanel) {
            Component initialTab = ((GenomeDataPanel) this).createGenomeTab(0);
            datasetAccordion.addTab(initialTab, "Dataset " + 1);
        } else {
            Component initialTab = ((ConditionDataPanel) this).createConditionTab(0);
            datasetAccordion.addTab(initialTab, "Condition " + 1);
        }

        //Tell presenter to refill the grid of available graph files;
        presenter.resetGraphFileGrid();
        //Tell presenter to remove old bindings and set up new ones
        presenter.clearDynamicBindings();
        presenter.initDatasetBindings(0);
        presenter.initReplicateBindings(0, 0);

    }


    /**
     * When the user changes the number of datasets or replicates, the Accordion has to be updated,
     * which happens in this method.
     */
    public void updateAccordion(int oldDatasetCount, int oldReplicateCount) {
        //Adjust number of replicate tabs for each dataset tab
        int replicateDelta = presenter.getNumberOfReplicates() - oldReplicateCount;
        for (int datasetIndex = 0; datasetIndex < oldDatasetCount; datasetIndex++) {
            datasetAccordion.getTab(datasetIndex);
            TabSheet.Tab tab = datasetAccordion.getTab(datasetIndex);
            //Access the replicate sheet of the current tab
            //It's the last of its three components, so its index is 2
            TabSheet currentReplicateSheet = ((DatasetTab) tab.getComponent()).replicatesSheet;
            if (replicateDelta > 0) {
                //Add new replicate tabs
                for (int replicateIndex = oldReplicateCount;
                     replicateIndex < presenter.getNumberOfReplicates();
                     replicateIndex++) {
                    Component replicateTab = new ReplicateTab(datasetIndex, replicateIndex);
                    currentReplicateSheet.addTab(replicateTab, "Replicate " + createAlphabeticalIndex(replicateIndex));
                    presenter.initReplicateBindings(datasetIndex, replicateIndex);
                }
            } else {
                //Remove excess replicate tabs
                for (int replicateIndex = oldReplicateCount; replicateIndex > presenter.getNumberOfReplicates(); replicateIndex--) {
                    currentReplicateSheet.removeTab(currentReplicateSheet.getTab(replicateIndex - 1));
                }
            }
        }


        int datasetDelta = presenter.getNumberOfDatasets() - oldDatasetCount;
        if (datasetDelta > 0) {
            //Add new dataset tabs
            for (int datasetIndex = oldDatasetCount; datasetIndex < presenter.getNumberOfDatasets(); datasetIndex++) {
                if (this instanceof GenomeDataPanel) {
                    Component currentTab = ((GenomeDataPanel) this).createGenomeTab(datasetIndex);
                    datasetAccordion.addTab(currentTab, "Dataset " + (datasetIndex + 1));
                } else {
                    Component currentTab = ((ConditionDataPanel) this).createConditionTab(datasetIndex);
                    datasetAccordion.addTab(currentTab, "Condition " + (datasetIndex + 1));

                }
                //Tell presenter to set up bindings
                presenter.initDatasetBindings(datasetIndex);
                for (int replicateIndex = 0;
                     replicateIndex < presenter.getNumberOfReplicates();
                     replicateIndex++) {
                    presenter.initReplicateBindings(datasetIndex, replicateIndex);
                }
            }
        } else {
            //Remove excess dataset tabs
            for (int i = oldDatasetCount; i > presenter.getNumberOfDatasets(); i--) {
                datasetAccordion.removeTab(datasetAccordion.getTab(i - 1));
            }
        }

    }

    /**
     * This class abstractly represents one tab in the dataset accordion.
     * It is extended by GenomeTab in GenomeDataPanel and ConditionTab in ConditionDataPanel.
     */
    public abstract class DatasetTab extends CustomComponent {
        VerticalLayout tab;
        TabSheet replicatesSheet;

        /**
         * Every dataset tab contains a replicate tab for each of its replicates.
         *
         * @param index
         */
        public DatasetTab(int index) {
            tab = new VerticalLayout();
            replicatesSheet = new TabSheet();
            for (int replicateIndex = 0;
                 replicateIndex < presenter.getNumberOfReplicates();
                 replicateIndex++) {
                ReplicateTab replicateTab = new ReplicateTab(index, replicateIndex);
                replicatesSheet.addTab(replicateTab, "Replicate " + createAlphabeticalIndex(replicateIndex));
            }
            setCompositionRoot(tab);
        }

        public ReplicateTab getReplicateTab(int index) {
            return (ReplicateTab) replicatesSheet.getTab(index).getComponent();
        }

    }

    /**
     * This class represents a replicate tab in the dataset accordion.
     * It's the same for both the genome and the condition variants, so it doesn't have to be abstract like its container,
     * the DatasetTab
     */
    public class ReplicateTab extends CustomComponent {
        VerticalLayout layout;
        MyGraphFileGrid treatedCoding, treatedTemplate, untreatedCoding, untreatedTemplate;
        private Grid<GraphFileBean> graphFileGrid;


        private GraphFileBean draggedItem;

        /**
         * A replicateTab consists of a vertical layout containing the grid of available graph files and
         * the four MyGraphFileGrids for the four strands of the replicate
         * After creating the former, drag and drop is implemented.
         *
         * @param datasetIndex
         * @param replicateIndex
         */
        public ReplicateTab(int datasetIndex, int replicateIndex) {
            layout = new VerticalLayout();

            treatedCoding = new MyGraphFileGrid("Treated Coding <b><i>(+)</i></b> Strand");
            treatedCoding.setCaptionAsHtml(true);
            treatedTemplate = new MyGraphFileGrid("Treated Template <b><i>(-)</i></b> Strand");
            treatedTemplate.setCaptionAsHtml(true);
            untreatedCoding = new MyGraphFileGrid("Untreated Coding <b><i>(+)</i></b> Strand");
            untreatedCoding.setCaptionAsHtml(true);
            untreatedTemplate = new MyGraphFileGrid("Untreated Template <b><i>(-)</i></b> Strand");
            untreatedTemplate.setCaptionAsHtml(true);

            graphFileGrid = new Grid<>("Available Graph Files");
            float graphFileGridWidth = 900;
            graphFileGrid.setWidth(graphFileGridWidth, Unit.PIXELS);
            graphFileGrid.addColumn(GraphFileBean::getName)
                    .setCaption("File name")
                    .setWidth(graphFileGridWidth / 2)
                    .setId("Name");
            graphFileGrid.addColumn(GraphFileBean::getCreationDate)
                    .setCaption("Creation Date")
                    .setWidth(graphFileGridWidth / 3.5);
            graphFileGrid.addColumn(GraphFileBean::getSizeInKB)
                    .setCaption("Size (kB)")
                    .setWidthUndefined(); //Column takes up remaining space

            graphFileGrid.addStyleName("my-file-grid");
            graphFileGrid.sort(graphFileGrid.getColumn("Name"));

            graphFileGrid.setItems(graphFileBeans);


            //Drag and drop implementation
            setupDragSource(graphFileGrid);
            setupDragSource(treatedCoding);
            setupDragSource(treatedTemplate);
            setupDragSource(untreatedCoding);
            setupDragSource(untreatedTemplate);

            //Setup drop target for the graph file grid
            GridDropTarget dropTarget = new GridDropTarget<>(graphFileGrid, DropMode.ON_TOP_OR_BETWEEN);
            dropTarget.setDropEffect(DropEffect.MOVE);
            dropTarget.addGridDropListener(event -> {
                //Remove dragged item from source
                Grid<GraphFileBean> dragSourceGrid = (Grid<GraphFileBean>) event.getDragSourceComponent().get();
                ListDataProvider<GraphFileBean> sourceProvider = (ListDataProvider<GraphFileBean>) dragSourceGrid.getDataProvider();
                sourceProvider.getItems().remove(draggedItem);
                dragSourceGrid.deselectAll();
                sourceProvider.refreshAll();
                Collection<GraphFileBean> items = ((ListDataProvider<GraphFileBean>) graphFileGrid.getDataProvider()).getItems();
                if (!items.contains(draggedItem))
                    items.add(draggedItem);
                graphFileGrid.getDataProvider().refreshAll();
            });


            //Layout the components
            presenter.updateReplicateID(datasetIndex, replicateIndex, createAlphabeticalIndex(replicateIndex));
            VerticalLayout treatedLayout = new VerticalLayout(treatedCoding, treatedTemplate);
            VerticalLayout untreatedLayout = new VerticalLayout(untreatedCoding, untreatedTemplate);
            HorizontalLayout gridLayout = new HorizontalLayout(treatedLayout, untreatedLayout);
            gridLayout.setComponentAlignment(treatedLayout, Alignment.MIDDLE_LEFT);
            gridLayout.setComponentAlignment(untreatedLayout, Alignment.MIDDLE_RIGHT);
            gridLayout.setWidth(100, Unit.PERCENTAGE);
            layout.addComponents(new InfoBar(Globals.REPLICATE_TAB_INFO), gridLayout, graphFileGrid);
            layout.setComponentAlignment(graphFileGrid, Alignment.BOTTOM_CENTER);
            setCompositionRoot(layout);


        }

        /**
         * Makes the rows of a GraphFileBean-Grid draggable
         *
         * @param grid
         */
        private void setupDragSource(Grid<GraphFileBean> grid) {
            GridDragSource<GraphFileBean> dragSource = new GridDragSource<>(grid);
            dragSource.setEffectAllowed(EffectAllowed.MOVE);
            dragSource.addGridDragStartListener(event ->
                    setDraggedItemInGraphFileGrids((GraphFileBean) event.getDraggedItems().toArray()[0]));
        }

        /**
         * Notifies the four MyGraphFileGrids of the item currently being dragged
         *
         * @param draggedItem
         */
        private void setDraggedItemInGraphFileGrids(GraphFileBean draggedItem) {
            this.draggedItem = draggedItem;
            treatedCoding.setDraggedItem(draggedItem);
            treatedTemplate.setDraggedItem(draggedItem);
            untreatedCoding.setDraggedItem(draggedItem);
            untreatedTemplate.setDraggedItem(draggedItem);
        }
        //Getters

        public MyGraphFileGrid getTreatedCoding() {
            return treatedCoding;
        }

        public MyGraphFileGrid getTreatedTemplate() {
            return treatedTemplate;
        }

        public MyGraphFileGrid getUntreatedCoding() {
            return untreatedCoding;
        }

        public MyGraphFileGrid getUntreatedTemplate() {
            return untreatedTemplate;
        }

        public Grid<GraphFileBean> getGraphFileGrid() {
            return graphFileGrid;
        }
    } //End of inner class ReplicateTab

    /**
     * Converts a numerical replicateIndex to an alphabetical value as follows:
     * 0 -> a
     * 1 -> b
     * ...
     * 25 -> z
     * 26 -> aa
     * 27 -> ab
     * ...
     */
    private String createAlphabeticalIndex(int replicateIndex) {
        StringBuilder builder = new StringBuilder();
        while (replicateIndex >= 26) {
            builder.append((char) (97 + (replicateIndex % 26)));
            replicateIndex /= 26;
            replicateIndex--;
        }
        builder.append((char) (97 + replicateIndex));
        return builder.reverse().toString();

    }

    public ComboBox<Integer> getNumberOfDatasetsBox() {
        return numberOfDatasetsBox;
    }

    public ComboBox<Integer> getNumberOfReplicatesBox() {
        return numberOfReplicatesBox;
    }

    public Accordion getDatasetAccordion() {
        return datasetAccordion;
    }

    public DatasetTab getDatasetTab(int index) {
        return (DatasetTab) datasetAccordion.getTab(index).getComponent();
    }

    public List<GraphFileBean> getGraphFileBeans() {
        return graphFileBeans;
    }
}