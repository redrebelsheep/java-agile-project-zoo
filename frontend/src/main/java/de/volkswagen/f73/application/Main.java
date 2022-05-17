package de.volkswagen.f73.application;

import de.volkswagen.f73.controller.OverviewViewController;
import de.volkswagen.f73.view.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class "Main"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OverviewView overviewView = new OverviewView();
        OverviewViewController overviewController = new OverviewViewController(overviewView);
        Scene scene = new Scene(overviewView);
        scene.getStylesheets().add("file:src/main/resources/style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zooverwaltung");
    }
}
