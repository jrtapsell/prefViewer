package uk.co.jrtapsell.prevWalker.controls;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import uk.co.jrtapsell.prevWalker.model.Mode;
import uk.co.jrtapsell.prevWalker.model.PrefNode;

public class ModeDropdown extends ChoiceBox<Mode> {
  private PrefTree refs;

  public void attach(final PrefTree tree) {
    refs = tree;
  }

  public void changed(final ObservableValue<? extends Mode> p, final Mode q, final Mode r) {
    final PrefNode preferences = r.getPreferences();
    refs.setRoot(preferences);
  }

  public ModeDropdown() {
    final ObservableList<Mode> items = getItems();
    items.clear();
    items.addAll(Mode.values());
    setValue(Mode.USER);
    selected().addListener(this::changed);
  }

  public ReadOnlyObjectProperty<Mode> selected() {
    return getSelectionModel().selectedItemProperty();
  }

  public PrefNode getCurrent() {
    return selected().get().getPreferences();
  }
}
