package life.qbic.view.panels;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import life.qbic.model.Globals;
import life.qbic.model.beans.AnnotationFileBean;
import life.qbic.model.beans.FastaFileBean;
import life.qbic.presenter.Presenter;
import life.qbic.view.InfoBar;
import life.qbic.view.MyGrid;

import java.util.LinkedList;
import java.util.List;

public class ConditionDataPanel extends DataPanel {
    Grid<FastaFileBean> fastaGrid;
    Grid<AnnotationFileBean> gffGrid;
    private List<FastaFileBean> fastaFileBeans = new LinkedList<>();
    private List<AnnotationFileBean> annotationFileBeans = new LinkedList<>();

    /**
     * After calling the Constructor of the superclass, the components for the fasta file and the annotation file
     * of the project are created and added to the components designed in the superclass. Unlike in the sibling class
     * "GenomeDataPanel", only one Fasta and one GFF file are needed, so there need not be components for them in
     * every DatasetTab.
     *
     * @param presenter
     */
    public ConditionDataPanel(Presenter presenter) {
        super(presenter);
        numberOfDatasetsBox.setCaption("Select number of Conditions");
        fastaGrid = new MyGrid<>("Dataset FASTA");
        fastaGrid.addColumn(FastaFileBean::getName).setCaption("File name");
        fastaGrid.addColumn(FastaFileBean::getCreationDate).setCaption("Creation Date");
        fastaGrid.addColumn(FastaFileBean::getSizeInKB).setCaption("Size (kB)");
        fastaGrid.addStyleName("my-file-grid");
        gffGrid = new MyGrid<>("Dataset annotation (GFF)");
        gffGrid.addColumn(AnnotationFileBean::getName).setCaption("File name");
        gffGrid.addColumn(AnnotationFileBean::getCreationDate).setCaption("Creation Date");
        gffGrid.addColumn(AnnotationFileBean::getSizeInKB).setCaption("Size (kB)");
        gffGrid.addStyleName("my-file-grid");
        contentLayout.addComponents(numberOfDatasetsBox, numberOfReplicatesBox,
                fastaGrid, gffGrid, datasetAccordion);
        wrapperLayout.addComponents(new InfoBar(Globals.CONDITION_DATA_SETTINGS_INFO), contentLayout);

        fastaGrid.setItems(fastaFileBeans);
        gffGrid.setItems(annotationFileBeans);
    }


    /**
     * This class represents a tab in the DatasetAccordion when comparing conditions.
     */
    public class ConditionTab extends DatasetTab {
        TextField nameField;

        /**
         * For a ConditionTab, only the component for its name has to be created here.
         * The other relevant components have been added above (Constructor of ConditionDataPanel),
         * the replicatesSheet is created in the superclass constructor and only needs to be added to the layout.
         *
         * @param index
         */
        public ConditionTab(int index) {
            super(index);
            nameField = new TextField("Name");
            this.tab.addComponents(new InfoBar(Globals.CONDITION_TAB_INFO), nameField,
                    new Label("<b>RNA-seq graph files for this condition:</b>", ContentMode.HTML),
                    replicatesSheet);


        }

        public TextField getNameField() {
            return nameField;
        }
    }

    public ConditionTab createConditionTab(int index) {
        ConditionTab tab = new ConditionTab(index);
        return tab;
    }

    public Grid<FastaFileBean> getFastaGrid() {
        return fastaGrid;
    }

    public Grid<AnnotationFileBean> getGffGrid() {
        return gffGrid;
    }
}
