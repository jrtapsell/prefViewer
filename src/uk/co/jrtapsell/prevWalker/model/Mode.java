package uk.co.jrtapsell.prevWalker.model;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public enum Mode {
  USER(Preferences.userRoot()),
  SYSTEM(Preferences.systemRoot());

  private final Preferences preferences;

  Mode(final Preferences preferences) {
    this.preferences = preferences;
  }

  public PrefNode getPreferences() {
    try {
      return new PrefNode(preferences);
    } catch (final BackingStoreException ex) {
      throw new AssertionError(ex);
    }
  }
}
