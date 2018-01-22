package life.qbic.view.panels;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import life.qbic.model.Globals;
import life.qbic.model.beans.AnnotationFileBean;
import life.qbic.model.beans.FastaFileBean;
import life.qbic.presenter.Presenter;
import life.qbic.testing.TestData;
import life.qbic.view.MyGrid;

import java.util.LinkedList;
import java.util.List;

public class GenomeDataPanel extends DataPanel {

    /**
     * After calling the Constructor of the superclass, the components are added to the wrapperLayout.
     * Unlike in the sibling class "ConditionDataPanel", no additional components are added.
     *
     * @param presenter
     */
    public GenomeDataPanel(Presenter presenter) {
        super(presenter);
        numberOfDatasetsBox.setCaption("Select number of Genomes");
        contentLayout.addComponents(numberOfDatasetsBox, numberOfReplicatesBox, datasetAccordion);
        wrapperLayout.addComponents(new InfoBar(Globals.GENOME_DATA_SETTINGS_INFO), contentLayout);

    }


    /**
     * This class represents a tab in the DatasetAccordion when comparing genomes.
     */
    public class GenomeTab extends DatasetTab {
        TextField nameField, idField;
        Grid<FastaFileBean> fastaGrid;
        Grid<AnnotationFileBean> gffGrid;

        /**
         * Each genome tab gets components for its name, its alignment id, its fasta file and its annotation file.
         * They are lain out in a FormLayout.
         * The replicates sheet (defined in the superclass DatasetTab) is added as well.
         */
        public GenomeTab(int index) {
            super(index);
            FormLayout genomeData = new FormLayout();
            nameField = new TextField("Name");
            idField = new TextField("Alignment ID");
            fastaGrid = new MyGrid<>("Genome FASTA");
            fastaGrid.addColumn(FastaFileBean::getName).setCaption("File name");
            fastaGrid.addColumn(FastaFileBean::getCreationDate).setCaption("Creation Date");
            fastaGrid.addColumn(FastaFileBean::getSizeInKB).setCaption("Size (kB)");
            fastaGrid.addStyleName("my-file-grid");
            gffGrid = new MyGrid<>("Genome annotation (GFF)");
            gffGrid.addColumn(AnnotationFileBean::getName).setCaption("File name");
            gffGrid.addColumn(AnnotationFileBean::getCreationDate).setCaption("Creation Date");
            gffGrid.addColumn(AnnotationFileBean::getSizeInKB).setCaption("Size (kB)");
            gffGrid.addStyleName("my-file-grid");
            genomeData.addComponents(new HorizontalLayout(nameField, idField), fastaGrid, gffGrid);
            this.tab.addComponents(new InfoBar(Globals.GENOME_TAB_INFO),
                    genomeData,
                    new Label("<b>RNA-seq graph files for this condition:</b>", ContentMode.HTML),
                    replicatesSheet);



            //<-- TESTING
            fastaGrid.setItems(TestData.createFastaFileBeanList());
            gffGrid.setItems(TestData.createAnnotationFileBeanList());
            //TESTING -->

        }

        public TextField getNameField() {
            return nameField;
        }

        public TextField getIdField() {
            return idField;
        }

        public Grid<FastaFileBean> getFastaGrid() {
            return fastaGrid;
        }

        public Grid<AnnotationFileBean> getGffGrid() {
            return gffGrid;
        }
    }

    public GenomeTab createGenomeTab(int index) {
        return new GenomeTab(index);
    }


}