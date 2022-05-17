package de.volkswagen.f73.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class "TopBar"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class TopBar extends HBox {
    private final Label headlineLabel = new Label();
    private final Label dateLabel = new Label();

    public TopBar(String headlineString) {
        this.headlineLabel.setText(headlineString);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.dateLabel.setText(LocalDate.now().format(dateFormatter));
        configureView();
        configureContent();
    }

    private void configureView() {
        this.setMinHeight(100);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(this.headlineLabel, this.dateLabel);
    }

    private void configureContent() {
        this.headlineLabel.setMinSize(400, 95);
        this.headlineLabel.setFont(Font.font("Arial", 30));
        this.dateLabel.setMinSize(100, 95);
        this.dateLabel.setFont(Font.font("Arial",20));
    }
}
