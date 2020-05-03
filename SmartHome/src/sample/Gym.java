package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Gym extends kitchen {
    newScene scene1 = new newScene();
    SmartObject group = new SmartObject();
    public Scene gym_scene(){
        lighting(group);
        Camera camera = new PerspectiveCamera();
        Box wall = getbox(1450,900,20,405,696,62, Color.BLACK,"gym.jpg");
        Button btn = new Button("click me");
        btn.setOnMouseClicked(e->calorie_counter());
        group.getChildren().addAll(wall,btn);
        // creating the scene
        Scene scene = createscene(Color.WHITESMOKE,1400,800,true,camera,group);
        return scene;
    }

    private void calorie_counter(){
        Group group = new Group();
        TextField show = new TextField();
        show.getTransforms().add(new Translate(50,370));
        Text answer = text_update("you have burnt the following calories",10,10,Color.BLACK);
        answer.getTransforms().add(new Translate(20,300));
        TextField option1txt = field_set(100,100);
        option1txt.setText("enter no of hrs");
        option1txt.setOnMouseClicked(e->{
            option1txt.setText("");
        });
        TextField option2txt = field_set(100,140);
        option2txt.setText("enter no of hrs");
        option2txt.setOnMouseClicked(e->{
            option2txt.setText("");
        });
        TextField option3txt = field_set(100,180);
        option3txt.setText("enter no of hrs");
        option3txt.setOnMouseClicked(e->{
            option3txt.setText("");
        });
        Text heading = text_update("Choose an one excercise",10,80,Color.RED);
        RadioButton option1 = radio_set("Burpies",10,100);
        option1.setOnMouseClicked(e->{
            float hr = Integer.parseInt(option1txt.getText());
            show.setText(String.valueOf(hr*680));
            option1.selectedProperty().setValue(false);
        });
        RadioButton option2 = radio_set("Bench Press",10,140);
        option2.setOnMouseClicked(e->{
            float hr = Integer.parseInt(option2txt.getText());
            show.setText(String.valueOf(hr*360));
            option2.selectedProperty().setValue(false);
        });
        RadioButton option3 = radio_set("Dead Lift",10,180);
        option3.setOnMouseClicked(e->{
            int hr = Integer.parseInt(option3txt.getText());
            show.setText(String.valueOf(hr*500));
            option3.selectedProperty().setValue(false);
        });
        group.getChildren().addAll(answer,option1,heading,option2,option3,option1txt,option2txt,option3txt,show);

        Scene scene = new Scene(group,400,600);
        scene.setFill(Color.PEACHPUFF);
        Stage stage = new Stage();
        stage.setTitle("Calorie Counter");
        stage.setScene(scene);
        stage.show();
    }
    private RadioButton radio_set(String text,int x_translate,int y_translate){
        RadioButton radioButton = new RadioButton(text);
        radioButton.getTransforms().add(new Translate(x_translate,y_translate));
        return radioButton;
    }
    private TextField field_set(int x_translate,int y_translate){
        TextField field = new TextField();
        field.getTransforms().add(new Translate(x_translate,y_translate));
        return field;
    }
}
