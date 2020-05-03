package sample;

import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

import java.awt.*;

public class livingroom extends Bedroom {
    SmartObject group = new SmartObject();
    public Scene livin(){
        Camera camera1 = new PerspectiveCamera();
        lighting(group);
        Box background = getbox(1450,900,20,405,696,62, Color.BLACK,"living_room.jpg");
        group.getChildren().add(background);
        group.getChildren().addAll(ac_btn(),playmovie());
        Scene scene1 = new Scene(group,1400,800,true);
        scene1.setFill(Color.WHITESMOKE);
        scene1.setCamera(camera1);
        scene1.addEventHandler(KeyEvent.KEY_PRESSED, e->{
            switch (e.getCode()){
                case S : group.translateZProperty().set(group.getTranslateZ() + 30);
                    break;
                case W : group.translateZProperty().set(group.getTranslateZ() - 30);
                    break;
                case A: group.translateXProperty().set(group.getTranslateX() + 30);
                    break;
                case D: group.translateXProperty().set(group.getTranslateX() - 30);
                    break;
                case NUMPAD5: group.RoatateByY(25);
                    break;
                case NUMPAD2: group.RoatateByY(-25);
                    break;
            }
        });
        return scene1;
    }
    private Group playmovie(){
        Group group = new Group();
        group.getChildren().add(movie("sleep",500,500,"D:\\IdeaProjects\\SmartHome\\src\\sample\\testing.mp4","movie_playing"));
        return group;
    }

    private Group ac_btn(){
        Group group = new Group();
        Button btn = new Button("AC on");
        btn.getTransforms().add(new Translate(450,400));
        btn.setPadding(new Insets(10));
        btn.setOnMousePressed(e->{
            if(btn.getText()=="AC on")
                btn.setText("AC off");
            else
                btn.setText("AC on");
        });
        group.getChildren().add(btn);
        return group;
    }
}
