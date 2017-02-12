package uk.co.jrtapsell.prevWalker.controls;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.co.jrtapsell.prevWalker.model.PrefNode;
import uk.co.jrtapsell.prevWalker.ui.PrefNodeCell;

public class PrefTree extends TreeView<Preferences> {
  private ModeDropdown dropdown;

  public PrefTree() {
    try {
      setRoot(new PrefNode(Preferences.userRoot()));
      setCellFactory(PrefNodeCell::new);
    } catch (final BackingStoreException ex) {
      throw new AssertionError(ex);
    }
    setOnKeyPressed(this::onKeypress);
  }

  public ReadOnlyObjectProperty<TreeItem<Preferences>> selected() {
    return getSelectionModel().selectedItemProperty();
  }

  public void attach(final ModeDropdown dropdown) {
    this.dropdown = dropdown;
  }

  public void onKeypress(final KeyEvent event) {
      if (event.getCode() == KeyCode.DELETE) {
        final TreeItem<Preferences> item = selected().get();
        final Preferences node = item.getValue();
        final Preferences parent = node.parent();
        if (parent == null) {
          return;
        }
        try {
          node.removeNode();
        } catch (final BackingStoreException ex) {
          throw new AssertionError(ex);
        }
        setRoot(dropdown.getCurrent());
      }
    }
}
