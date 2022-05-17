package de.volkswagen.f73.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The class "LabeledTextField"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class LabeledTextField extends VBox {

    private final Label nameLabel = new Label();
    private final TextField labeledTextField;
    private final String name;
    private final double labelWidth;
    private final double labelHeight;
    private final double textFieldWidth;
    private final double textFieldHeight;

    public LabeledTextField(String name, double labelWidth, double labelHeight, double textFieldWidth,
                            double textFieldHeight, boolean isDoubleTextField) {
        this.name = name;
        this.labeledTextField = isDoubleTextField ? new DoubleTextField() : new TextField();
        this.labelWidth = labelWidth;
        this.labelHeight = labelHeight;
        this.textFieldWidth = textFieldWidth;
        this.textFieldHeight = textFieldHeight;
        initLabel();
        initTextField();
        configureVBox();
    }

    public LabeledTextField(String name, boolean isDoubleTextField) {
        this(name, 200, 60, 200, 60, isDoubleTextField);
    }

    public LabeledTextField(String name) {
        this(name, 200, 60, 200, 60, false);
    }

    private void initLabel() {
        this.nameLabel.setText(this.name + ":");
        this.nameLabel.setMinSize(this.labelWidth, this.labelHeight);
        this.nameLabel.setMaxSize(this.labelWidth, this.labelHeight);
        this.nameLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
    }

    private void initTextField() {
        this.labeledTextField.setMinSize(this.textFieldWidth, this.textFieldHeight);
        this.labeledTextField.setMaxSize(this.textFieldWidth, this.textFieldHeight);
        this.labeledTextField.setPromptText(this.name);
    }

    private void configureVBox() {
        this.getChildren().addAll(this.nameLabel, this.labeledTextField);
        this.setPadding(new Insets(2, 5, 0, 5));
        this.setAlignment(Pos.CENTER);
    }

    public TextField getLabeledTextField() {
        return this.labeledTextField;
    }
}
