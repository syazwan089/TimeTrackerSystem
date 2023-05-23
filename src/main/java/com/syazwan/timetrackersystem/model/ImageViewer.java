package com.syazwan.timetrackersystem.model;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class ImageViewer {
    private VBox imageContainer;
    private ScrollPane scrollPane;

    ImageViewer() {
        imageContainer = new VBox();
        imageContainer.setSpacing(10);
        scrollPane = new ScrollPane(imageContainer);
        scrollPane.setFitToWidth(true);
    }

    ScrollPane getScrollPane() {
        return scrollPane;
    }

    void displayImages(List<Image> images) {
        for (Image image : images) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            imageContainer.getChildren().add(imageView);
        }
    }
}
