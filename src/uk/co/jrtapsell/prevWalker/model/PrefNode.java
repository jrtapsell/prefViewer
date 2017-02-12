package uk.co.jrtapsell.prevWalker.model;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.scene.control.TreeItem;

public class PrefNode extends TreeItem<Preferences> {

  public PrefNode(final Preferences preferences) throws BackingStoreException {
    super(preferences);
    for (final String node : preferences.childrenNames()) {
      final Preferences childData = preferences.node(node);
      final PrefNode newNode = new PrefNode(childData);
      getChildren().add(newNode);
    }
  }

  @Override
  public boolean isLeaf() {
    return getChildren().isEmpty();
  }

  @Override
  public String toString() {
    return String.format("PrefNode for {%s}", getValue());
  }
}
