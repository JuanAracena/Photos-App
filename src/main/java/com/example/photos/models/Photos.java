package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main class
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class Photos extends Application{
    
    private Pane root;
    private Scene scene;

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login screen_nocancel.fxml"));
        
        root = (Pane)loader.load();
        scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
}