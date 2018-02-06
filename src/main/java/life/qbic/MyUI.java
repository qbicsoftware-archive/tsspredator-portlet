package life.qbic;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import life.qbic.model.Model;
import life.qbic.model.config.ConfigFile;
import life.qbic.presenter.Presenter;
import life.qbic.view.MainView;


@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.AppWidgetSet")
public class MyUI extends UI {

//  Might be useful later
//  private static Log log = LogFactoryUtil.getLog(MyUI.class);


    protected void init(VaadinRequest vaadinRequest) {
        Model model = new Model();
        MainView view = new MainView();
        Presenter presenter = new Presenter(view, model);
        presenter.initFields();
        presenter.initBindings();
        presenter.displayData();
        view.setWidth(UI.getCurrent().getPage().getBrowserWindowWidth(), Unit.PIXELS);
        setContent(view);
    }
}
