package sample;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class cant_go_back extends kitchen {
    public void error_msg_from_scene1(){
        SmartObject group = new SmartObject();
        Camera camera = new PerspectiveCamera();
       Text text = text_update("Please unlock the door to go out of the house",10,120,Color.RED);
        group.getChildren().addAll(camera,text);
        Scene scene = createscene(Color.YELLOW,400,300,true,camera,group);
        scene.setFill(Color.AQUA);
        Stage stage = new Stage();
        stage.setTitle("error");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }
}
