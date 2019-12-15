//Group Members
//Jonathan Dunsmore
//Arturo Blandon
//Howard Montes de Oca
//
package assignment.sample;

import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;

//This class stores all the information we need to make a shape
public class ShapeInformation {
    Shape3D shape;
    double startingXCoordinate, startingYCoordinate;
    char creationID;
    boolean isSelected = false;
    double currentXCoordinate;
    String color;


    public ShapeInformation(Shape3D s, double x, double y, char c){
        shape = s;
        startingXCoordinate = x;
        startingYCoordinate = y;
        creationID = c;


        currentXCoordinate = x;
        s.setTranslateX(x);
        s.setTranslateY(y);

        //this here shows we can do the clicked even inside here... just have to figure out how to get the information that its been clicked outside
        //IF ALL ELSE FAILS
        //We can move the creation of this object class to the main.java and simply use a global variable for which creationID is selected
        shape.setOnMouseClicked(event -> {
            isSelected = true;
        });

    }
    public Shape3D getShape(){
        return shape;
    }
    public double getStartingXCoordinate(){
        return startingXCoordinate;
    }
    public double getStartingYCoordinate(){
        return startingYCoordinate;
    }
    public char getCreationID(){ // this was intended to be what we tied to the object for when we select it with the mouse
        return creationID;
    }



    //This was some stuff I was trying with booleans but it still wont function if I can't index into the vector within the lambda

    public void select(){
        isSelected = true;
    }
    public boolean isSelected(){
        return isSelected;
    }
    public void resetSelected(){
        isSelected = false;
    }

}