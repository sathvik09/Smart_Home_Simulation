package sample;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class scene2 extends Group {
    SmartObject group = new SmartObject();
    Camera camera = new PerspectiveCamera();
    Scene scene = new Scene(group,1400,800,true);

    public void movement(Scene scene){

    }

}
