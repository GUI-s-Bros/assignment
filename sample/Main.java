//Names here
//Jonathan Dunsmore
package assignment.sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;



public class Main extends Application {
    int selectedShape;
    Object isEditing;
    int shapesCreated = 0;
    Vector<ShapeInformation> vectorOf3DShapes;


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

        menuItemSave.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Shape Files", "*.shape"));
            for(int i = 0; i < vectorOf3DShapes.size(); i++){
                vectorOf3DShapes.get(i).getStartingXCoordinate();
                vectorOf3DShapes.get(i).getStartingYCoordinate();
                vectorOf3DShapes.get(i).getShape().getTranslateX();
                vectorOf3DShapes.get(i).getShape().getTranslateY();
                if(vectorOf3DShapes.get(i).getCreationID() == 'B')
                {
                    ((Box)vectorOf3DShapes.get(i).getShape()).getHeight();
                    ((Box)vectorOf3DShapes.get(i).getShape()).getDepth();
                    ((Box)vectorOf3DShapes.get(i).getShape()).getWidth();
                }
            }
            File newFile = fileChooser.showSaveDialog(primaryStage);

            try
            {
                // create a new file in the location specified by curFilePath
                FileWriter fw = new FileWriter(newFile);
                PrintWriter pw = new PrintWriter(newFile);
                //for






                pw.println("yeeeeeet");
                pw.close();
            }
            catch (IOException e)
            {
                // Display an alert informing the user the file could not be created
                Alert alert = new Alert(Alert.AlertType. ERROR);
                alert.setHeaderText( "Error Creating File!");
                alert.show();
            }

        });




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

        Label labelRotateZCoordiante = new Label("Rotate Z-Coordinate");
        Slider sliderRotateZ = new Slider(0,360,0);
        sliderRotateZ.setShowTickMarks(true);
        sliderRotateZ.setShowTickLabels(true);

        Label labelTranslateToXCoordinate = new Label("Translate to X-Coordinate");
        TextField textFieldTranslateToXCoordinate = new TextField();
        Button buttonTranslateToXCoordinate = new Button("Apply");

        Label labelTranslateToYCoordinate = new Label("Translate to Y-Coordinate");
        TextField textFieldTranslateToYCoordinate = new TextField();
        Button buttonTranslateToYCoordinate = new Button("Apply");

        Label labelTranslateToZCoordinate = new Label("Translate to Z-Coordinate");
        TextField textFieldTranslateToZCoordinate = new TextField();
        Button buttonTranslateToZCoordinate = new Button("Apply");

        Label labelScaleXCoordinate = new Label("Scale X-Coordinate");
        Slider scaleXCoordinate = new Slider(0.5, 3, 1);
        scaleXCoordinate.setShowTickMarks(true);
        scaleXCoordinate.setShowTickLabels(true);

        Label labelScaleYCoordinate = new Label("Scale Y-Coordinate");
        Slider scaleYCoordinate = new Slider(0.5, 3, 1);
        scaleYCoordinate.setShowTickMarks(true);
        scaleYCoordinate.setShowTickLabels(true);

        Label labelScaleZCoordinate = new Label("Scale Z-Coordinate");
        Slider scaleZCoordinate = new Slider(0.5, 3, 1);
        scaleZCoordinate.setShowTickMarks(true);
        scaleZCoordinate.setShowTickLabels(true);

        Label labelShapeColors = new Label("Shape Colors");
        ChoiceBox<String> choiceBoxShapeColors = new ChoiceBox<>();
        choiceBoxShapeColors.getItems().addAll("White", "Grey", "Black", "Red"); // add more as needed/wanted
                                                                                           // will need to map these strings to Color.RED ect inside the listener

        Label labelSubsceneColors = new Label("Subscene Colors");
        ChoiceBox<String> choiceBoxSubSceneColors = new ChoiceBox<>();
        choiceBoxSubSceneColors.getItems().addAll("White", "Grey", "Black", "Red", "Azure", "Teal"); // same as above

        // put all the side bar items into the vbox
        VBox vboxRightSide = new VBox(5, labelRotateXCoordinate, sliderRotateX,
                labelRotateYCoordinate, sliderRotateY, labelRotateZCoordiante, sliderRotateZ,
                labelTranslateToXCoordinate, textFieldTranslateToXCoordinate, buttonTranslateToXCoordinate,
                labelTranslateToYCoordinate, textFieldTranslateToYCoordinate, buttonTranslateToYCoordinate,
                labelTranslateToZCoordinate, textFieldTranslateToZCoordinate, buttonTranslateToZCoordinate,
                labelScaleXCoordinate, scaleXCoordinate,
                labelScaleYCoordinate, scaleYCoordinate,
                labelScaleZCoordinate, scaleZCoordinate,
                labelSubsceneColors, choiceBoxSubSceneColors,
                labelShapeColors, choiceBoxShapeColors);
        vboxRightSide.setAlignment(Pos.CENTER);
        vboxRightSide.setPadding(new Insets(15));
        mainBorderPane.setRight(vboxRightSide);

        // SubScene stuff
        vectorOf3DShapes = new Vector<>(); // vector is basically an array of variable
        // size that increases as needed
        // will contain all the pointers to the shapes we make
        Group groupShapes = new Group();

        SubScene subScene = new SubScene(groupShapes, 900, 700);
        subScene.setFill(Color.AZURE);
        //set the camera angle
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        subScene.setCamera(perspectiveCamera);
        Rotate rotatePerspectiveCameraX = new Rotate(40, Rotate.X_AXIS);
        Rotate rotatePerspectiveCameraY = new Rotate(40, Rotate.Y_AXIS);
        perspectiveCamera.getTransforms().addAll(new Translate(0,0,-80));
        mainBorderPane.setCenter(subScene);

        // This starts the create shape scene stuff
        //
        //
        //

        // A borderpane to hold all forms and in the darkness bind them
        BorderPane borderPaneCreateShape = new BorderPane();

        //Radio buttons to choose which shape to make
        RadioButton radioButtonSphere = new RadioButton("Sphere");
        RadioButton radioButtonBox = new RadioButton("Box");
        RadioButton radioButtonCylinder = new RadioButton("Cylinder");
        ToggleGroup toggleGroupShapeToMake = new ToggleGroup();
        radioButtonBox.setToggleGroup(toggleGroupShapeToMake);
        radioButtonCylinder.setToggleGroup(toggleGroupShapeToMake);
        radioButtonSphere.setToggleGroup(toggleGroupShapeToMake);

        //Submit button for submitting
        Button buttonSubmit = new Button("Submit");

        //VBox for radio buttons and submit
        VBox vboxRadioButtonsCreateShape = new VBox(10, radioButtonBox, radioButtonCylinder,
                radioButtonSphere, buttonSubmit);
        vboxRadioButtonsCreateShape.setAlignment(Pos.CENTER_LEFT);

        //Labels and text fields to fill out, will be disabled unless applicable
        Label labelCreateShapeXCoordinate = new Label("X Coordinate");
        TextField textFieldCreateShapeXCoordinate = new TextField();
        Label labelCreateShapeYCoordinate = new Label("Y Coordinate");
        TextField textFieldCreateShapeYCoordinate = new TextField();
        Label labelCreateShapeRadius = new Label("Radius");
        TextField textFieldCreateShapeRadius = new TextField();
        Label labelCreateShapeWidth = new Label("Width");
        TextField textFieldCreateShapeWidth = new TextField();
        Label labelCreateShapeHeight = new Label("Height");
        TextField textFieldCreateShapeHeight = new TextField();
        Label labelCreateShapeLength = new Label("Length");
        TextField textFieldCreateShapeLength = new TextField();

        //VBox for all those labels and Text fields
        VBox vboxTextFieldsCreateShape = new VBox(10, labelCreateShapeXCoordinate,
                textFieldCreateShapeXCoordinate, labelCreateShapeYCoordinate, textFieldCreateShapeYCoordinate,
                labelCreateShapeRadius, textFieldCreateShapeRadius, labelCreateShapeHeight,
                textFieldCreateShapeHeight, labelCreateShapeWidth, textFieldCreateShapeWidth,
                labelCreateShapeLength, textFieldCreateShapeLength);
        vboxTextFieldsCreateShape.setAlignment(Pos.CENTER);

        textFieldCreateShapeXCoordinate.setDisable(true);
        textFieldCreateShapeYCoordinate.setDisable(true);
        textFieldCreateShapeHeight.setDisable(true);
        textFieldCreateShapeLength.setDisable(true);
        textFieldCreateShapeRadius.setDisable(true);
        textFieldCreateShapeWidth.setDisable(true);

        //finishing up the UI
        borderPaneCreateShape.setCenter(vboxRadioButtonsCreateShape);
        borderPaneCreateShape.setRight(vboxTextFieldsCreateShape);
        borderPaneCreateShape.setPadding(new Insets(10));


        Scene sceneCreateShape = new Scene(borderPaneCreateShape, 300, 400);
        Stage stageCreateShape = new Stage();
        stageCreateShape.setTitle("Create Shape");
        stageCreateShape.setScene(sceneCreateShape);
        // show happens in the Create Shape Button


        radioButtonBox.setOnAction(event -> {
            textFieldCreateShapeXCoordinate.setDisable(false);
            textFieldCreateShapeYCoordinate.setDisable(false);
            textFieldCreateShapeHeight.setDisable(false);
            textFieldCreateShapeLength.setDisable(false);
            textFieldCreateShapeRadius.setDisable(true);
            textFieldCreateShapeWidth.setDisable(false);
        });

        radioButtonCylinder.setOnAction(event -> {
            textFieldCreateShapeXCoordinate.setDisable(false);
            textFieldCreateShapeYCoordinate.setDisable(false);
            textFieldCreateShapeHeight.setDisable(false);
            textFieldCreateShapeLength.setDisable(true);
            textFieldCreateShapeRadius.setDisable(false);
            textFieldCreateShapeWidth.setDisable(true);
        });

        radioButtonSphere.setOnAction(event -> {
            textFieldCreateShapeXCoordinate.setDisable(false);
            textFieldCreateShapeYCoordinate.setDisable(false);
            textFieldCreateShapeHeight.setDisable(true);
            textFieldCreateShapeLength.setDisable(true);
            textFieldCreateShapeRadius.setDisable(false);
            textFieldCreateShapeWidth.setDisable(true);
        });


        buttonSubmit.setOnAction(event -> {

            if(radioButtonBox.isSelected()){
                double width = new Double(textFieldCreateShapeWidth.getText()).doubleValue();
                double height = new Double(textFieldCreateShapeHeight.getText()).doubleValue();
                double length = new Double(textFieldCreateShapeLength.getText()).doubleValue();
                double x = new Double(textFieldCreateShapeXCoordinate.getText()).doubleValue();
                double y = new Double(textFieldCreateShapeYCoordinate.getText()).doubleValue();

                vectorOf3DShapes.add(new ShapeInformation(new Box(width, height, length), x, y, 'B'));
                groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
            }
            else if(radioButtonCylinder.isSelected()){
                double height = new Double(textFieldCreateShapeHeight.getText()).doubleValue();
                double radius = new Double(textFieldCreateShapeRadius.getText()).doubleValue();
                double x = new Double(textFieldCreateShapeXCoordinate.getText()).doubleValue();
                double y = new Double(textFieldCreateShapeYCoordinate.getText()).doubleValue();

                vectorOf3DShapes.add(new ShapeInformation(new Cylinder(radius, height), x, y, 'c'));
                groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
            }
            else if(radioButtonSphere.isSelected()){
                double radius = new Double(textFieldCreateShapeRadius.getText()).doubleValue();
                double x = new Double(textFieldCreateShapeXCoordinate.getText()).doubleValue();
                double y = new Double(textFieldCreateShapeYCoordinate.getText()).doubleValue();

                vectorOf3DShapes.add(new ShapeInformation(new Sphere(radius), x, y, 'r'));
                groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
            }

            vectorOf3DShapes.get(shapesCreated).getShape().setOnMouseClicked(event1 ->{
                isEditing = event1.getTarget();
                System.out.println(((Shape3D)isEditing).getClass().getName());
                double x = ((Shape3D)isEditing).getTransforms().get(0).getTx();
                System.out.println(x);

            } );
            shapesCreated++;

            //ADDING FUNCTIONALITY
            sliderRotateX.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).getTransforms().addAll(new Rotate(sliderRotateX.getValue(), Rotate.X_AXIS));
            });

            sliderRotateY.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).getTransforms().addAll(new Rotate(sliderRotateY.getValue(), Rotate.Y_AXIS));
            });

            sliderRotateZ.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).getTransforms().addAll(new Rotate(sliderRotateZ.getValue(), Rotate.Z_AXIS));
            });

            buttonTranslateToXCoordinate.setOnAction(event2 ->{
                ((Shape3D)isEditing).setTranslateX(Double.parseDouble(textFieldTranslateToXCoordinate.getText()));
                double x = ((Shape3D)isEditing).getTranslateX();

                System.out.print(x);


            });

            buttonTranslateToYCoordinate.setOnAction(event3->{
                ((Shape3D)isEditing).setTranslateY(Double.parseDouble(textFieldTranslateToYCoordinate.getText()));

            });

            buttonTranslateToZCoordinate.setOnAction(event4->{
                ((Shape3D)isEditing).setTranslateZ(Double.parseDouble(textFieldTranslateToZCoordinate.getText()));
            });


            //FUNCTIONALITY FOR SCALING
            scaleXCoordinate.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).setScaleX(scaleXCoordinate.getValue());

            });

            scaleYCoordinate.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).setScaleY(scaleYCoordinate.getValue());

            });

            scaleZCoordinate.valueProperty().addListener((s,o,n)->{
                ((Shape3D)isEditing).setScaleZ(scaleZCoordinate.getValue());
            });

            choiceBoxShapeColors.setOnAction(event3 -> {
                if(choiceBoxShapeColors.getValue().equals("White"))
                    ((Shape3D)isEditing).setMaterial(new PhongMaterial(Color.WHITE));
                if(choiceBoxShapeColors.getValue().equals("Grey"))
                    ((Shape3D)isEditing).setMaterial(new PhongMaterial(Color.GREY));
                if(choiceBoxShapeColors.getValue().equals("Black"))
                    ((Shape3D)isEditing).setMaterial(new PhongMaterial(Color.GREY));
                if(choiceBoxShapeColors.getValue().equals("Red"))
                    ((Shape3D)isEditing).setMaterial(new PhongMaterial(Color.RED));

            });


            //cleanses the scene for new inputs before closing
            textFieldCreateShapeHeight.setText("");
            textFieldCreateShapeWidth.setText("");
            textFieldCreateShapeLength.setText("");
            textFieldCreateShapeRadius.setText("");
            textFieldCreateShapeXCoordinate.setText("");
            textFieldCreateShapeYCoordinate.setText("");
            radioButtonBox.setSelected(false);
            radioButtonCylinder.setSelected(false);
            radioButtonSphere.setSelected(false);

            stageCreateShape.close();
        });


        // End of create shape scene stuff
        //
        //

        //The Create shape button that brings up the form to make a shape
        buttonCreateShape.setOnAction(event -> {
            stageCreateShape.show();
        });


        // creates actions for each shape when clicked it runs this loop to look for the one in
        // the vector that was clicked

        //Event handler for user selecting a new subscene color
        choiceBoxSubSceneColors.setOnAction(event -> {
            if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("White")) {
                subScene.setFill(Color.WHITE);
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Grey")) {
                subScene.setFill(Color.GRAY);
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Black")) {
                subScene.setFill(Color.BLACK);
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Red")) {
                subScene.setFill(Color.RED);
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Azure")) {
                subScene.setFill(Color.AZURE);
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Teal")) {
                subScene.setFill(Color.TEAL);
            }
        });

        menuItemExit.setOnAction(event -> {
            primaryStage.close();
        });

        primaryStage.setTitle("Homework #4");
        primaryStage.setScene(new Scene(mainBorderPane, 1300, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
