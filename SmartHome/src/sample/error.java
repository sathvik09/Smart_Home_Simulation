package sample;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
// nothing crazy, jst an error scene if password is incorrect
public class error extends kitchen{
    public Scene error_msg(){
        SmartObject group = new SmartObject();
        Camera camera = new PerspectiveCamera();
        Box wall = getbox(1450,900,20,405,696,62, Color.BLACK,"hacker.jpg");
        Text text = text_update("Not so easy to get in buddy?!? :}",600,150,Color.WHITE);
        group.getChildren().addAll(camera,wall,text);
        Scene scene = createscene(Color.WHITESMOKE,1400,800,true,camera,group);
        return scene;
    }
}
