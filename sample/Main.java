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

    //Fields to store shape info for "Save"
    double xCoordinate;
    double yCoordinate;
    double zCoordinate;
    double height;
    double width;
    double depth;
    double radius;

    FileWriter fw;
    PrintWriter pw;
    Vector<ShapeInformation> vectorOf3DShapes;


    @Override
    public void start(Stage primaryStage) throws Exception{

        vectorOf3DShapes = new Vector<>(); // vector is basically an array of variable
        Group groupShapes = new Group();

        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setPadding(new Insets(5));

        // Menu at the top of the page
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);

        MenuItem menuItemSave = new MenuItem("Save");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemExit = new MenuItem("Exit");


        //Save event handler, stores shape information in text file.
            menuItemSave.setOnAction(event -> {

                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                        File newFile = fileChooser.showSaveDialog(primaryStage);
                        try {
                            fw = new FileWriter(newFile);
                            pw = new PrintWriter(fw);
                            for (int i = 0; i < vectorOf3DShapes.size(); i++) {

                                xCoordinate = vectorOf3DShapes.get(i).getShape().getTranslateX();
                                yCoordinate = vectorOf3DShapes.get(i).getShape().getTranslateY();
                                zCoordinate = vectorOf3DShapes.get(i).getShape().getTranslateZ();


                                if (vectorOf3DShapes.get(i).getCreationID() == 'B') {
                                    height = ((Box) vectorOf3DShapes.get(i).getShape()).getHeight();
                                    depth = ((Box) vectorOf3DShapes.get(i).getShape()).getDepth();
                                    width = ((Box) vectorOf3DShapes.get(i).getShape()).getWidth();

                                    pw.println(vectorOf3DShapes.get(i).getShape().getClass().getName());
                                    pw.println(height);
                                    pw.println(depth);
                                    pw.println(width);
                                    pw.println(xCoordinate);
                                    pw.println(yCoordinate);
                                    pw.println(zCoordinate);


                                } else if (vectorOf3DShapes.get(i).getCreationID() == 'S') {
                                    radius = ((Sphere) vectorOf3DShapes.get(i).getShape()).getRadius();

                                    pw.println(vectorOf3DShapes.get(i).getShape().getClass().getName());
                                    pw.println(xCoordinate);
                                    pw.println(yCoordinate);
                                    pw.println(zCoordinate);
                                    pw.println(radius);
                                } else if (vectorOf3DShapes.get(i).getCreationID() == 'C') {
                                    height = ((Cylinder) vectorOf3DShapes.get(i).getShape()).getHeight();
                                    radius = ((Cylinder) vectorOf3DShapes.get(i).getShape()).getRadius();

                                    pw.println(vectorOf3DShapes.get(i).getShape().getClass().getName());
                                    pw.println(radius);
                                    pw.println(height);
                                    pw.println(xCoordinate);
                                    pw.println(yCoordinate);
                                    pw.println(zCoordinate);

                                }
                            }
                        } catch(IOException e ){
                            // Display an alert informing the user the file could not be created
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Error Creating File!");
                            alert.show();
                        }
                        catch (NullPointerException n){
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setHeaderText("Save file canceled");
                            alert2.show();
                        }

                try {
                    fw.close();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException n){
                    //////////////////
                }


                //pw.close();


            });

            // "Open" file menu event handler, scanner reads input line by line then creates shape objects.
            menuItemOpen.setOnAction(actionEvent -> {
                //clears out any shapes currently on subscene
                vectorOf3DShapes.clear();
                groupShapes.getChildren().clear();
                shapesCreated = 0;

                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File newFile = fileChooser.showOpenDialog(primaryStage);
                try {
                    Scanner scanner = new Scanner(newFile);
                    String shapeName;
                    while(scanner.hasNext()) {

                        shapeName = scanner.nextLine();

                        if (shapeName.equals("javafx.scene.shape.Sphere")) {
                            radius = scanner.nextDouble();
                            xCoordinate = scanner.nextDouble();
                            yCoordinate = scanner.nextDouble();
                            zCoordinate = scanner.nextDouble();
                            vectorOf3DShapes.add(new ShapeInformation(new Sphere(radius), xCoordinate, yCoordinate, 'S'));
                            groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
                            vectorOf3DShapes.get(shapesCreated).getShape().setTranslateZ(zCoordinate);
                            shapesCreated++;

                        } else if (shapeName.equals("javafx.scene.shape.Box")) {
                            height = scanner.nextDouble();
                            depth = scanner.nextDouble();
                            width = scanner.nextDouble();
                            xCoordinate = scanner.nextDouble();
                            yCoordinate = scanner.nextDouble();
                            zCoordinate = scanner.nextDouble();
                            vectorOf3DShapes.add(new ShapeInformation(new Box(width, height, depth), xCoordinate, yCoordinate, 'B'));
                            groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
                            vectorOf3DShapes.get(shapesCreated).getShape().setTranslateZ(zCoordinate);
                            shapesCreated++;
                        } else if (shapeName.equals("javafx.scene.shape.Cylinder")) {
                            radius = scanner.nextDouble();
                            height = scanner.nextDouble();
                            xCoordinate = scanner.nextDouble();
                            yCoordinate = scanner.nextDouble();
                            zCoordinate = scanner.nextDouble();
                            vectorOf3DShapes.add(new ShapeInformation(new Cylinder(radius, height), xCoordinate, yCoordinate, 'C'));
                            groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
                            vectorOf3DShapes.get(shapesCreated).getShape().setTranslateZ(zCoordinate);
                            shapesCreated++;
                        }

                        for (int i = 0; i < vectorOf3DShapes.size(); i++) {
                            vectorOf3DShapes.get(i).getShape().setOnMouseClicked(event1 -> {
                                isEditing = event1.getTarget();
                                System.out.println(((Shape3D) isEditing).getClass().getName());
                            });
                        }



                    }
                    scanner.close();

                }
                catch (IOException e){
                    System.out.println("Error opening file");
                }

            });




        menuFile.getItems().addAll(menuItemSave, new SeparatorMenuItem(), menuItemOpen, new SeparatorMenuItem(), menuItemExit);

        mainBorderPane.setTop(menuBar);

        // Create Shape button
        Button buttonCreateShape = new Button("Create Shape");


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
        choiceBoxSubSceneColors.getItems().addAll("White", "Gray", "Black", "Red", "Azure", "Teal"); // same as above

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
//        vectorOf3DShapes = new Vector<>(); // vector is basically an array of variable
        // size that increases as needed
        // will contain all the pointers to the shapes we make
//        Group groupShapes = new Group();

        SubScene subScene = new SubScene(groupShapes, 700, 500);
        subScene.setFill(Color.AZURE);
        //set the camera angle
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        subScene.setCamera(perspectiveCamera);
        Rotate rotatePerspectiveCameraX = new Rotate(40, Rotate.X_AXIS);
        Rotate rotatePerspectiveCameraY = new Rotate(40, Rotate.Y_AXIS);
        perspectiveCamera.getTransforms().addAll(new Translate(0,0,-80));
        VBox bottomVbox = new VBox(20, subScene, buttonCreateShape);
        bottomVbox.setAlignment(Pos.CENTER);
        bottomVbox.setPadding(new Insets(15));
        mainBorderPane.setCenter(bottomVbox);

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

                vectorOf3DShapes.add(new ShapeInformation(new Cylinder(radius, height), x, y, 'C'));
                groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
            }
            else if(radioButtonSphere.isSelected()){
                double radius = new Double(textFieldCreateShapeRadius.getText()).doubleValue();
                double x = new Double(textFieldCreateShapeXCoordinate.getText()).doubleValue();
                double y = new Double(textFieldCreateShapeYCoordinate.getText()).doubleValue();

                vectorOf3DShapes.add(new ShapeInformation(new Sphere(radius), x, y, 'S'));
                groupShapes.getChildren().add(vectorOf3DShapes.get(shapesCreated).getShape());
            }

            vectorOf3DShapes.get(shapesCreated).getShape().setOnMouseClicked(event1 ->{
                isEditing = event1.getTarget();
                System.out.println(((Shape3D)isEditing).getClass().getName());
//                xCoordinate = vectorOf3DShapes.get(0).getShape().getTranslateX();
//                System.out.println(xCoordinate);

            } );

            shapesCreated++;


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

        //ADDING FUNCTIONALITY
        sliderRotateX.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).getTransforms().addAll(new Rotate(sliderRotateX.getValue(), Rotate.X_AXIS));
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to rotate");
                alert.show();
                sliderRotateX.setValue(1);
            }
        });

        sliderRotateY.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).getTransforms().addAll(new Rotate(sliderRotateY.getValue(), Rotate.Y_AXIS));
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to rotate");
                alert.show();
                sliderRotateY.setValue(1);
            }
        });

        sliderRotateZ.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).getTransforms().addAll(new Rotate(sliderRotateZ.getValue(), Rotate.Z_AXIS));
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to translate");
                alert.show();
                sliderRotateZ.setValue(1);
            }
        });

        buttonTranslateToXCoordinate.setOnAction(event2 ->{
            try {
                ((Shape3D) isEditing).setTranslateX(Double.parseDouble(textFieldTranslateToXCoordinate.getText()));
            }
            catch (NullPointerException n){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to translate");
                alert.show();
                textFieldTranslateToXCoordinate.setText("");
            }
        });

        buttonTranslateToYCoordinate.setOnAction(event3->{
            try {
                ((Shape3D) isEditing).setTranslateY(Double.parseDouble(textFieldTranslateToYCoordinate.getText()));
            }
            catch (NullPointerException n){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to translate");
                alert.show();
                textFieldTranslateToYCoordinate.setText("");
            }
        });

        buttonTranslateToZCoordinate.setOnAction(event4->{
            try {
                ((Shape3D) isEditing).setTranslateZ(Double.parseDouble(textFieldTranslateToZCoordinate.getText()));
            }
            catch (NullPointerException n){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to translate");
                alert.show();
                textFieldTranslateToZCoordinate.setText("");
            }
        });


        //FUNCTIONALITY FOR SCALING
        scaleXCoordinate.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).setScaleX(scaleXCoordinate.getValue());
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to scale");
                alert.show();
            }
        });

        scaleYCoordinate.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).setScaleY(scaleYCoordinate.getValue());
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to scale");
                alert.show();
            }
        });

        scaleZCoordinate.valueProperty().addListener((s,o,n)->{
            try {
                ((Shape3D) isEditing).setScaleZ(scaleZCoordinate.getValue());
            }
            catch (NullPointerException np){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Select a shape to scale");
                alert.show();
            }
        });

        choiceBoxShapeColors.setOnAction(event3 -> {
            try {
                if (choiceBoxShapeColors.getValue().equals("White"))
                    ((Shape3D) isEditing).setMaterial(new PhongMaterial(Color.WHITE));
                if (choiceBoxShapeColors.getValue().equals("Grey"))
                    ((Shape3D) isEditing).setMaterial(new PhongMaterial(Color.GREY));
                if (choiceBoxShapeColors.getValue().equals("Black"))
                    ((Shape3D) isEditing).setMaterial(new PhongMaterial(Color.BLACK));
                if (choiceBoxShapeColors.getValue().equals("Red"))
                    ((Shape3D) isEditing).setMaterial(new PhongMaterial(Color.RED));
            }catch(NullPointerException n){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("No shape selected");
                    alert.show();
                    choiceBoxShapeColors.setValue(null);
            }
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
            }else if (choiceBoxSubSceneColors.getSelectionModel().getSelectedItem().equals("Gray")) {
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
        primaryStage.setScene(new Scene(mainBorderPane, 1000, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}