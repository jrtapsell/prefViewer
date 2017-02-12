package uk.co.jrtapsell.prevWalker.controls;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.co.jrtapsell.prevWalker.model.PreferenceItem;

public class PrefTable extends TableView<PreferenceItem> {

  public PrefTable() {
    final ObservableList<TableColumn<PreferenceItem, ?>> columns = getColumns();
    final TableColumn<PreferenceItem, String> name = new TableColumn<>("Name");
    name.setCellValueFactory(p -> p.getValue().getName());
    final TableColumn<PreferenceItem, String> value = new TableColumn<>("Value");
    value.setCellValueFactory(p -> p.getValue().getValue());
    setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    columns.add(name);
    columns.add(value);
    setOnKeyPressed(this::onKeypress);
  }

  public ReadOnlyObjectProperty<PreferenceItem> selected() {
    return getSelectionModel().selectedItemProperty();
  }

  public void changeNode(final Preferences node) {
    getItems().clear();
    if (node == null) {
      return;
    }
    try {
      for (final String name : node.keys()) {
        final PreferenceItem item = new PreferenceItem(node, name);
        getItems().add(item);
      }
    } catch (final BackingStoreException ex) {
      throw new AssertionError(ex);
    }
  }

  public void onKeypress(final KeyEvent event) {
    if (event.getCode() == KeyCode.DELETE) {
      final PreferenceItem item = selected().getValue();
      item.delete();
      getItems().remove(item);
    }
  }

  public void attach(final PrefTree treeView) {
    treeView.selected().addListener(new ChangeListener<TreeItem<Preferences>>() {
      @Override
      public void changed(final ObservableValue<? extends TreeItem<Preferences>> observable,
                          final TreeItem<Preferences> oldValue,
                          final TreeItem<Preferences> newValue) {
        if (newValue == null) {
          return;
        }
        changeNode(newValue.getValue());
      }
    });
  }
}
