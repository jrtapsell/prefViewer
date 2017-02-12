package uk.co.jrtapsell.prevWalker.ui;

import java.util.prefs.Preferences;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;

public class PrefNodeCell extends TreeCell<Preferences> {

  public PrefNodeCell(final TreeView<Preferences> preferences) {}

  @Override
  protected void updateItem(final Preferences item, final boolean empty) {
    super.updateItem(item, empty);
    if (empty || item == null) {
      setText(null);
    } else {
      final String name = item.name();
      setText(name);
    }
  }
}
