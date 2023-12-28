package com.cgvsu.rasterizationfxapp;

import com.cgvsu.rasterization.triangle.TriangleRasterization;
import com.cgvsu.rasterization.triangle.Triangle;
import com.cgvsu.rasterization.triangle.Vector2f;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RasterizationController {
    private final Triangle triangle = new Triangle(
            new Vector2f(100.0f, 300.0f),
            new Vector2f(400.0f, 200.0f),
            new Vector2f(700.0f, 300.0f),
            Color.RED,
            Color.LIME,
            Color.BLUE
    );

    private Vector2f vertToMove = triangle.v2;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        redrawTriangle();
    }

    public void handleMouse(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            vertToMove.set((float) e.getSceneX(), (float) e.getSceneY());
            redrawTriangle();
        }
    }

    public void handleKey(KeyEvent e) {
        if (e.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (e.getCode()) {
                case SPACE -> {
                    triangle.randomizeVertices((float) canvas.getWidth(), (float) canvas.getHeight());
                    redrawTriangle();
                }
                case Q -> {
                    triangle.resetColors();
                    redrawTriangle();
                }
                case W -> {
                    triangle.randomizeColors();
                    redrawTriangle();
                }
            }
            switch (e.getText()) {
                case "1" -> vertToMove = triangle.v1;
                case "2" -> vertToMove = triangle.v2;
                case "3" -> vertToMove = triangle.v3;
            }
        }
    }

    private void redrawTriangle() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        TriangleRasterization.drawTriangle(canvas.getGraphicsContext2D().getPixelWriter(), triangle);
    }
}
