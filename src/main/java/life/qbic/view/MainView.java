package life.qbic.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import life.qbic.model.Globals;
import life.qbic.presenter.Presenter;
import life.qbic.view.panels.*;

import java.io.File;

/**
 * Contains the main layout of the GUI. Its core component is an Accordion whose tabs make up the parts of the TSSPredator workflow:
 * - General Configuration
 * - Data Settings
 * - Parameter Settings
 *
 * @author jmueller
 */
public class MainView extends CustomComponent {
    private VerticalLayout mainLayout;
    private Presenter presenter;
    private Accordion contentAccordion;
    private Button createConfigButton, downloadButton, loadConfigButton;
    private FileDownloader downloader;
    private GeneralConfigPanel generalConfigPanel;
    private ConditionDataPanel conditionDataPanel;
    private GenomeDataPanel genomeDataPanel;
    private ParametersPanel parametersPanel;

    public MainView() {
        this.mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
    }

    public void createView(){
        buildContentAccordion();
        createConfigButton = new Button("Create Config File", (Button.ClickListener) clickEvent -> {
            File file = this.presenter.produceConfigFile();
            downloader = new FileDownloader(new FileResource(file));
            downloader.extend(downloadButton);
        });
        downloadButton = new Button("Download Config File");
        downloadButton.setEnabled(false); //Button is not enabled until config file has been successfully created
        loadConfigButton = new Button("Load existing configuration");
        loadConfigButton.setVisible(false);
        mainLayout.addComponents(contentAccordion, createConfigButton, downloadButton, loadConfigButton);
    }

    /**
     * Creates the main Accordion and adds the panels to it
     */
    private void buildContentAccordion() {
        contentAccordion = new Accordion();
        generalConfigPanel = new GeneralConfigPanel(presenter);

        genomeDataPanel = new GenomeDataPanel(presenter);
        conditionDataPanel = new ConditionDataPanel(presenter);

        parametersPanel = new ParametersPanel(presenter);

        contentAccordion.addTab(generalConfigPanel, Globals.GENERAL_PANEL_CAPTION).setIcon(VaadinIcons.EDIT);
        contentAccordion.addTab(genomeDataPanel, Globals.DATA_PANEL_CAPTION).setIcon(VaadinIcons.DATABASE);
        contentAccordion.addTab(conditionDataPanel, Globals.DATA_PANEL_CAPTION).setIcon(VaadinIcons.DATABASE);
        //Only one of the two data panels is visible
        contentAccordion.getTab(1).setVisible(!Globals.IS_CONDITIONS_INIT);
        contentAccordion.getTab(2).setVisible(Globals.IS_CONDITIONS_INIT);
        contentAccordion.addTab(parametersPanel, Globals.PARAMETERS_PANEL_CAPTION).setIcon(VaadinIcons.SLIDERS);

        contentAccordion.setWidth(100, Unit.PERCENTAGE);
    }




    public Accordion getContentAccordion() {
        return contentAccordion;
    }

    public GeneralConfigPanel getGeneralConfigPanel() {
        return generalConfigPanel;
    }

    public ParametersPanel getParametersPanel() {
        return parametersPanel;
    }

    public GenomeDataPanel getGenomeDataPanel() {
        return genomeDataPanel;
    }

    public ConditionDataPanel getConditionDataPanel() {
        return conditionDataPanel;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
