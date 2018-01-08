package life.qbic.view;

import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.StyleGenerator;
import com.vaadin.ui.components.grid.ItemClickListener;

/**
 * @author jmueller
 * Just a plain old Vaadin Grid with slight modifications:
 * - It is not possible to deselect, only to select something else
 * - Selected rows have a different style than usual ("selected")
 * - A notification is shown whenever a row is selected TODO: Notifications aren't shown on the server yet
 */
public class MyGrid<T> extends Grid<T> {
    private T selectedItem;

    public MyGrid() {
        new MyGrid("");
    }

    public MyGrid(String caption) {
        super(caption);
        setStyleGenerator((StyleGenerator<T>) t -> this.getSelectedItems().contains(t) ? "selected" : null);

        addSelectionListener((SelectionListener<T>) selectionEvent -> {
            if (!selectionEvent.getFirstSelectedItem().isPresent()) {
                select(selectedItem);
            } else if (selectionEvent.isUserOriginated()) {
                selectedItem = selectionEvent.getFirstSelectedItem().get();
                Notification.show(selectionEvent.getFirstSelectedItem().get().toString() + " has been selected");
            }

        });

    }

}
