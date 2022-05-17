package de.volkswagen.f73.view;

import de.volkswagen.f73.controller.SideBarViewController;
import de.volkswagen.f73.model.TopBar;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The class "OverviewView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class OverviewView extends BorderPane {

    private final TopBar topBar;
    private final HBox formHBox = new HBox();
    private final Button testButton = new Button("Export");

    public OverviewView() {
        this.topBar = new TopBar("Zoo - Dashboard");
        configureView();
        configureContent();
    }

    private void configureView() {
        VBox centerVBox = new VBox(this.testButton, this.formHBox);
        this.setMinSize(1700, 800);
        this.setTop(this.topBar);
        SideBarView sideBarView = new SideBarView();
        SideBarViewController sideBarController = new SideBarViewController(sideBarView);
        this.setLeft(sideBarView);
        this.setCenter(centerVBox);
    }

    private void configureContent() {

    }

    public Button getTestButton() {
        return testButton;
    }
}
