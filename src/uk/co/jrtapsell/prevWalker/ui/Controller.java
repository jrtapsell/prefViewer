package uk.co.jrtapsell.prevWalker.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uk.co.jrtapsell.prevWalker.controls.ModeDropdown;
import uk.co.jrtapsell.prevWalker.controls.PrefTable;
import uk.co.jrtapsell.prevWalker.controls.PrefTree;

public class Controller implements Initializable{

  @FXML private PrefTree treeView;
  @FXML private PrefTable tableView;
  @FXML private ModeDropdown mode;

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {}

  public void postInject() {
    mode.attach(treeView);
    treeView.attach(mode);
    tableView.attach(treeView);
  }
}
