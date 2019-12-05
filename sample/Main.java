//Names here
//Jonathan Dunsmore
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Vector;

public class Main extends Application {
//    int selected;


    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setPadding(new Insets(5));

        // Menu at the top of the page
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);

        MenuItem menuItemSave = new MenuItem("Save");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemExit = new MenuItem("Exit");

        menuFile.getItems().addAll(menuItemSave, new SeparatorMenuItem(), menuItemOpen, new SeparatorMenuItem(), menuItemExit);

        mainBorderPane.setTop(menuBar);

        // Create Shape button
        Button buttonCreateShape = new Button("Create Shape");

        HBox hboxBottom = new HBox(10, buttonCreateShape);
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(15));
        mainBorderPane.setBottom(hboxBottom);

        // side menu

        Label labelRotateXCoordinate = new Label("Rotate X-Coordinate");
        Slider sliderRotateX = new Slider(0, 360, 0);
        sliderRotateX.setShowTickMarks(true);
        sliderRotateX.setShowTickLabels(true);

        Label labelRotateYCoordinate = new Label("Rotate Y-Coordinate");
        Slider sliderRotateY = new Slider(0, 360, 0);
        sliderRotateY.setShowTickMarks(true);
        sliderRotateY.setShowTickLabels(true);

        Label labelTranslateToXCoordinate = new Label("Translate to X-Coordinate");
        TextField textFieldTranslateToXCoordinate = new TextField();
        Button buttonTranslateToXCoordinate = new Button("Apply");

        Label labelTranslateToYCoordinate = new Label("Translate to Y-Coordinate");
        TextField textFieldTranslateToYCoordinate = new TextField();
        Button buttonTranslateToYCoordinate = new Button("Apply");

        Label labelScaleXCoordinate = new Label("Scale X-Coordinate");
        Slider scaleXCoordinate = new Slider(0, 100, 0);
        sliderRotateX.setShowTickMarks(true);
        sliderRotateX.setShowTickLabels(true);

        Label labelScaleYCoordinate = new Label("Scale Y-Coordinate");
        Slider scaleYCoordinate = new Slider(0, 100, 0);
        sliderRotateX.setShowTickMarks(true);
        sliderRotateX.setShowTickLabels(true);

        Label labelScaleZCoordinate = new Label("Scale Z-Coordinate");
        Slider scaleZCoordinate = new Slider(0, 100, 0);
        sliderRotateX.setShowTickMarks(true);
        sliderRotateX.setShowTickLabels(true);

        Label labelShapeColors = new Label("Shape Colors");
        ChoiceBox<String> choiceBoxShapeColors = new ChoiceBox<>();
        choiceBoxShapeColors.getItems().addAll("White", "Grey", "Black", "Red"); // add more as needed/wanted
                                                                                           // will need to map these strings to Color.RED ect inside the listener

        Label labelSubsceneColors = new Label("Subscene Colors");
        ChoiceBox<String> choiceBoxSubSceneColors = new ChoiceBox<>();
        choiceBoxSubSceneColors.getItems().addAll("White", "Grey", "Black", "Red", "Azure", "Teal"); // same as above

        // put all the side bar items into the vbox
        VBox vboxRightSide = new VBox(5, labelRotateXCoordinate, sliderRotateX,
                labelRotateYCoordinate, sliderRotateY,
                labelTranslateToXCoordinate, textFieldTranslateToXCoordinate, buttonTranslateToXCoordinate,
                labelTranslateToYCoordinate, textFieldTranslateToYCoordinate, buttonTranslateToYCoordinate,
                labelScaleXCoordinate, scaleXCoordinate,
                labelScaleYCoordinate, scaleYCoordinate,
                labelScaleZCoordinate, scaleZCoordinate,
                labelSubsceneColors, choiceBoxSubSceneColors,
                labelShapeColors, choiceBoxShapeColors);
        vboxRightSide.setAlignment(Pos.CENTER);
        vboxRightSide.setPadding(new Insets(15));
        mainBorderPane.setRight(vboxRightSide);

        // SubScene stuff
        Vector<Shape3D> vectorOf3DShapes = new Vector<>(); // vector is basically an array of variable size that increases as needed
                                                           // will contain all the pointers to the shapes we make
        Group groupShapes = new Group();

        SubScene subScene = new SubScene(groupShapes, 900, 700);
        subScene.setFill(Color.GRAY);
        //set the camera angle
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        subScene.setCamera(perspectiveCamera);
        Rotate rotatePerspectiveCameraX = new Rotate(40, Rotate.X_AXIS);
        Rotate rotatePerspectiveCameraY = new Rotate(40, Rotate.Y_AXIS);
        perspectiveCamera.getTransforms().addAll(rotatePerspectiveCameraX, rotatePerspectiveCameraY);
        mainBorderPane.setCenter(subScene);




        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(mainBorderPane, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
// My own example code for how to select the shapes and modify them with the sliders

//
//        VBox root = new VBox();
//        root.setAlignment(Pos.CENTER);
//
//        Slider slider1 = new Slider(0, 360, 0);
//        slider1.setShowTickMarks(true);
//        slider1.setShowTickLabels(true);
//        slider1.setMaxWidth(200);
//        Slider slider2 = new Slider(0, 360, 0);
//        slider2.setShowTickMarks(true);
//        slider2.setShowTickLabels(true);
//        slider2.setMaxWidth(200);
//
//
//        Cylinder c = new Cylinder(5, 10);
//        c.getTransforms().add(new Translate(20, 0, 0));
//        Box b = new Box(10, 5, 7);
//
//        Shape3D[] shapeArray = new Shape3D[2];
//        shapeArray[0] = c;
//        shapeArray[1] = b;
//
//        Group shapeGroup = new Group();
//
//        shapeGroup.getChildren().addAll(shapeArray[0], shapeArray[1]);
//
//        SubScene subScene = new SubScene(shapeGroup, 350, 350, true, SceneAntialiasing.DISABLED);
//        subScene.setFill(Color.TEAL);
//
//        PerspectiveCamera pCamera = new PerspectiveCamera(true);
//        subScene.setCamera(pCamera);
//        Rotate rotateX = new Rotate(40, Rotate.X_AXIS);
//        Rotate rotateY = new Rotate(40, Rotate.Y_AXIS);
//        pCamera.getTransforms().addAll(rotateX, rotateY, new Translate(5,0,-60));
//
//        root.getChildren().addAll(subScene, slider1, slider2);
//
//        shapeArray[0].setOnMouseClicked(event -> {
//            selected = 0;
//        });
//        shapeArray[1].setOnMouseClicked(event -> {
//            selected = 1;
//        });
//
//
//        slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
//            //when you set a number it will be equal to slider1.getValue();
//            Rotate tX = new Rotate(slider1.getValue(), Rotate.X_AXIS);
//            shapeArray[selected].getTransforms().add(tX);
//        });
//
//        slider2.valueProperty().addListener((observable, oldValue, newValue) -> {
//            Rotate tY = new Rotate(slider2.getValue(), Rotate.Y_AXIS);
//            shapeArray[selected].getTransforms().addAll(tY);
//        });
