//Names here
//Jonathan Dunsmore
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {
    int selected;


    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        Slider slider1 = new Slider(0, 360, 0);
        slider1.setShowTickMarks(true);
        slider1.setShowTickLabels(true);
        slider1.setMaxWidth(200);
        Slider slider2 = new Slider(0, 360, 0);
        slider2.setShowTickMarks(true);
        slider2.setShowTickLabels(true);
        slider2.setMaxWidth(200);


        Cylinder c = new Cylinder(5, 10);
        c.getTransforms().add(new Translate(20, 0, 0));
        Box b = new Box(10, 5, 7);

        Shape3D[] shapeArray = new Shape3D[2];
        shapeArray[0] = c;
        shapeArray[1] = b;

        Group shapeGroup = new Group();

        shapeGroup.getChildren().addAll(shapeArray[0], shapeArray[1]);

        SubScene subScene = new SubScene(shapeGroup, 350, 350, true, SceneAntialiasing.DISABLED);
        subScene.setFill(Color.TEAL);

        PerspectiveCamera pCamera = new PerspectiveCamera(true);
        subScene.setCamera(pCamera);
        Rotate rotateX = new Rotate(40, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(40, Rotate.Y_AXIS);
        pCamera.getTransforms().addAll(rotateX, rotateY, new Translate(5,0,-60));

        root.getChildren().addAll(subScene, slider1, slider2);

        shapeArray[0].setOnMouseClicked(event -> {
            selected = 0;
        });
        shapeArray[1].setOnMouseClicked(event -> {
            selected = 1;
        });


        slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
            //when you set a number it will be equal to slider1.getValue();
            Rotate tX = new Rotate(slider1.getValue(), Rotate.X_AXIS);
            shapeArray[selected].getTransforms().add(tX);
        });

        slider2.valueProperty().addListener((observable, oldValue, newValue) -> {
            Rotate tY = new Rotate(slider2.getValue(), Rotate.Y_AXIS);
            shapeArray[selected].getTransforms().addAll(tY);
        });


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
