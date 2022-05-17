package de.volkswagen.f73.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The class "SideBarView"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class SideBarView extends VBox {

    private final Button overviewButton = new Button("Übersicht");
    private final Button animalButton = new Button("Tiere");
    private final Button enclosureButton = new Button("Gehege");
    private final Button stallButton = new Button("Verkaufsstände");
    private final Button employeeButton = new Button("Mitarbeiter");
    private final Button financeButton = new Button("Finanzen");
    private final Button reportButton = new Button("Report");

    /**
     * Instantiates a new Side bar view.
     */
    public SideBarView( ) {
       configureView();
       configureContent();
    }

    private void configureView() {
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(this.overviewButton, this.animalButton, this.enclosureButton, this.stallButton,
                this.employeeButton, this.financeButton, this.reportButton);
    }

    private void configureContent() {
        configureButton(this.overviewButton);
        configureButton(this.animalButton);
        configureButton(this.enclosureButton);
        configureButton(this.stallButton);
        configureButton(this.employeeButton);
        configureButton(this.financeButton);
        configureButton(this.reportButton);
    }

    private void configureButton(Button button) {
        button.setMinSize(120, 40);
        button.setPadding(new Insets(20, 15, 20, 15));
        button.setAlignment(Pos.CENTER);
        this.setMargin(button, new Insets(15, 15, 0, 15));
    }

    /**
     * Gets overview button.
     *
     * @return the overview button
     */
    public Button getOverviewButton() {
        return this.overviewButton;
    }

    /**
     * Gets animal button.
     *
     * @return the animal button
     */
    public Button getAnimalButton() {
        return animalButton;
    }

    /**
     * Gets enclosure button.
     *
     * @return the enclosure button
     */
    public Button getEnclosureButton() {
        return this.enclosureButton;
    }

    /**
     * Get stall button button.
     *
     * @return the button
     */
    public Button getStallButton(){
        return this.stallButton;
    }

    /**
     * Gets employee button.
     *
     * @return the employee button
     */
    public Button getEmployeeButton() {
        return employeeButton;
    }

    /**
     * Gets finance button.
     *
     * @return the finance button
     */
    public Button getFinanceButton() {
        return financeButton;
    }

    /**
     * Gets report button.
     *
     * @return the report button
     */
    public Button getReportButton() {
        return reportButton;
    }
}
