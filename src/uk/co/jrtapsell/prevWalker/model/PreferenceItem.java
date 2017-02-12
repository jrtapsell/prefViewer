package uk.co.jrtapsell.prevWalker.model;

import java.util.prefs.Preferences;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class PreferenceItem {
  private final Property<Preferences> parent = new SimpleObjectProperty<>();
  private final Property<String> name = new SimpleStringProperty();
  private final Property<String> value = new SimpleStringProperty();

  public PreferenceItem(final Preferences parent, final String name) {
    this.parent.setValue(parent);
    this.name.setValue(name);
    value.setValue(parent.get(name, null));
  }

  public ObservableValue<String> getName() {
    return name;
  }

  public ObservableValue<String> getValue() {
    return value;
  }

  public void delete() {
    parent.getValue().remove(name.getValue());
  }
}
