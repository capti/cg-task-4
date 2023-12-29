package com.cgvsu;

import com.cgvsu.objreader.IncorrectFileException;
import com.cgvsu.objwriter.ObjWriter;
import com.cgvsu.render_engine.RenderEngine;
import com.cgvsu.scene.Scene;
import javafx.fxml.FXML;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.vecmath.Vector3f;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.render_engine.Camera;

public class GuiController {

    final private float TRANSLATION = 0.5F;
    private boolean isStructure = true;
    public static boolean isLight = false;
    private BufferedImage image = null;
    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    private Model mesh = null;

    Scene scene = new Scene();

    private Camera camera = new Camera(
            new Vector3f(0, 0, 100),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    private Timeline timeline;


    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);


        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            scene.camera.setAspectRatio((float) (width / height));
            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
            camera.setAspectRatio((float) (width / height));

            if (mesh != null) {
                RenderEngine.render(canvas.getGraphicsContext2D(), camera, mesh, (int) width, (int) height);
                if (isStructure) {
                    RenderEngine.render(canvas.getGraphicsContext2D(), camera, mesh, (int) width, (int) height);
                }
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            String fileContent = Files.readString(fileName);
            mesh = ObjReader.read(fileContent);
            //mesh = ObjReader.read(fileContent);
            // todo: обработка ошибок
        } catch (IOException | IncorrectFileException exception) {

        }
    }

    public void saveModelMenuItemClick(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Сохранить файл");
        File selectedFile = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        if (selectedFile != null) {
            try {
                Model model = new Model();
                //model = ;
                ObjWriter.write(String.valueOf(selectedFile), model);
                JOptionPane.showMessageDialog(null, "Модель успешно сохранена");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ошибка при сохранении модели: " + e.getMessage(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadStructure() {
        isStructure = !isStructure;
    }//сетка будет включаться/выключаться, когда будет растеризация
    public void loadLight() {
        isLight = !isLight;
    }
    @FXML
    private void loadTexture() throws IOException {

        /**if (!mesh.isTexture) {  // isTexture это метод, описанный в классе Model, с помощью которого загружается текстура (делает 3 человек)
            FileChooser fileChooser = new FileChooser();
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG (*.jpg)", "*.jpg"));
            fileChooser.setTitle("Загрузить текстуру");
            File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());

            if (file == null) {
                return;
            }
            image = ImageIO.read(file);
        }
        mesh.isTexture = !mesh.isTexture;*/

    }




    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, -TRANSLATION));
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, TRANSLATION));
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(-TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, TRANSLATION, 0));
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, -TRANSLATION, 0));
    }

    public void addCamera(ActionEvent actionEvent) {
    }

    public void deleteCamera(ActionEvent actionEvent) {
    }

    public void choosingActualModel(ActionEvent actionEvent) {


    }

    public void choosingCamera(ActionEvent actionEvent) {

    }

    public void onClick(ActionEvent actionEvent) { //при нажатии кнопки "Применить" вызываем метод transform
        /**transform(
                parseTextField(scaleX, false), parseTextField(scaleY, false), parseTextField(scaleZ, false),
                parseTextField(rotateX, false), parseTextField(rotateY, false), parseTextField(rotateZ, false),
                parseTextField(translateX, true), parseTextField(translateY, true), parseTextField(translateZ, true)
        );*/
    }

    private float parseTextField(TextField textField, boolean isTranslate) {//используем в методе onClick
        try {
            return Float.parseFloat(textField.getText());
        } catch (NumberFormatException e) {
            if (!isTranslate) {
                return 1;
            }
            return 0;
        }
    }

    public void oldModel(ActionEvent actionEvent) {
    }
}