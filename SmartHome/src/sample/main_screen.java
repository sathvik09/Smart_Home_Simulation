package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

public class main_screen extends Bedroom {
    private final String key = "sathvik";
    private boolean security = false;
    PasswordField textField = new PasswordField();
    public Scene mainscene(){
        SmartObject group = new SmartObject();
        Camera camera = new PerspectiveCamera();
        group.getChildren().addAll(getBackground());
        group.setOnMouseClicked(e->{
            System.out.println(key);
            System.out.println(textField.getText());
            if(textField.getText().equals(key)){
                System.out.print(textField.getText());
                security=true;
            }
            System.out.println(security);
        });
        Scene scene = createscene(Color.WHITESMOKE,1400,800,true,camera,group);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,e->{
            if(e.getCode()==KeyCode.R) {
                if (textField.isDisabled())
                    textField.setDisable(false);
                else
                    textField.setDisable(true);
            }
        });
        return scene;
    }

    public boolean isSecurity(){
        return security;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    private Group getBackground(){
        Group group = new Group();
        textField.getTransforms().add(new Translate(200,150,30));
        textField.setDisable(true);
        Box wall = getbox(1450,900,20,405,696,62, Color.BLACK,"house.jpg");
        group.getChildren().addAll(wall,textField);
        return group;
    }
}
