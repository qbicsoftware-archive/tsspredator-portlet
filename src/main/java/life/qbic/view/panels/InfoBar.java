package life.qbic.view.panels;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * This component consists of a blue bar with an info-icon and a text. It is placed at various positions in TSSpredator
 * and serves to explain the components of the workflow to the user
 *
 * @author jmueller
 */
public class InfoBar extends CustomComponent {

    public InfoBar(String infoText){
        Label infoLabel = new Label(VaadinIcons.INFO_CIRCLE.getHtml() + "\t" + infoText, ContentMode.HTML);
        addStyleName("my-info-bar");
        infoLabel.setWidth(100, Unit.PERCENTAGE);
        setCompositionRoot(infoLabel);
    }
}
