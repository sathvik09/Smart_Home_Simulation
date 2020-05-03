package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

public class kitchen extends Bedroom {
    newScene scene1 = new newScene();
    SmartObject group = new SmartObject();
    AmbientLight light = new AmbientLight();
    public Scene kitchen_scene(){
        lighting(group);
        Camera camera = new PerspectiveCamera();
        Box wall = getbox(1450,900,20,405,696,62,Color.BLACK,"kitchen_rm.jpg");
        group.getChildren().addAll(wall,checkboxes());
        // creating the scene
       Scene scene = createscene(Color.WHITESMOKE,1400,800,true,camera,group);
       return scene;
    }

    private Group checkboxes(){
        Group group = new Group();
        Text text = text_update("Select recipie",600,150,Color.WHITE);
        CheckBox it1 = new CheckBox("Martini");
        it1.setOnMousePressed(e->{
            if(!it1.isSelected())
                initiate("D:\\IdeaProjects\\SmartHome\\src\\sample\\martini.mp4","martini!");
        });
        it1.getTransforms().add(new Translate(600,180));
        CheckBox it2 = new CheckBox("Pasta");
        it2.setOnMousePressed(e->{
            if(!it2.isSelected())
                initiate("D:\\IdeaProjects\\SmartHome\\src\\sample\\vedios\\BologneseSauce.mp4","Pasta!");
        });
        it2.getTransforms().add(new Translate(600,230));
        CheckBox it3 = new CheckBox("Cup cakes");
        it3.getTransforms().add(new Translate(600,280));
        it3.setOnMousePressed(e->{
            if(!it3.isSelected())
                initiate("D:\\IdeaProjects\\SmartHome\\src\\sample\\cupcake.mp4","Fresh Cupcakes at Home!");
        });
        group.getChildren().addAll(text,it1,it2,it3);
        return group;
    }


    protected Text text_update(String input, int x, int y,Color color){
        Text temp_text = new Text();
        temp_text.setText(input);
        temp_text.getTransforms().add(new Translate(x,y));
        temp_text.setFont(Font.font("serif", FontWeight.LIGHT, FontPosture.ITALIC,20));
        temp_text.fillProperty().setValue(color);
        return temp_text;
    }
}
