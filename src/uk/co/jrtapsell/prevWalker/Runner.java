package uk.co.jrtapsell.prevWalker;

import java.io.IOException;
import java.net.URL;
import java.util.prefs.BackingStoreException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import uk.co.jrtapsell.prevWalker.ui.Controller;

public class Runner extends Application {

  private static final URL FXML_FILE = Runner.class.getResource("root.fxml");

  @Override
  public void start(final Stage primaryStage) throws BackingStoreException, IOException {
    final FXMLLoader loader = new FXMLLoader(FXML_FILE);
    final SplitPane pane = loader.load();
    final Controller controller = loader.getController();
    final Scene value = new Scene(pane);
    primaryStage.setScene(value);
    controller.postInject();
    primaryStage.show();
    primaryStage.setTitle("Preference viewer");
    primaryStage.setMaximized(true);
  }

  public static void main(final String... args) {
    launch(args);
  }

}
