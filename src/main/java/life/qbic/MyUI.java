package life.qbic;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import life.qbic.presenter.Presenter;
import life.qbic.view.AccordionLayoutMain;


@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.AppWidgetSet")
public class MyUI extends UI {

//  Might be useful later
//  private static Log log = LogFactoryUtil.getLog(MyUI.class);


    protected void init(VaadinRequest vaadinRequest) {
        Presenter presenter = new Presenter();
        AccordionLayoutMain layout = new AccordionLayoutMain(presenter);
        presenter.setView(layout);
        presenter.initFields();
        presenter.initBindings();
        setContent(layout);
    }
}
