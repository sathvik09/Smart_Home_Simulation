package sample;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import java.sql.Timestamp;
import java.util.Date;

class SmartObject extends Group {
    Rotate r;
    Transform t = new Rotate();

    void RoatateByX(int ang){
        r= new Rotate(ang,Rotate.X_AXIS);
        t=t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

    void RoatateByY(int ang){
        r= new Rotate(ang,Rotate.Y_AXIS);
        t=t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

    void RoatateByZ(int ang){
        r= new Rotate(ang,Rotate.Z_AXIS);
        t=t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().add(t);
    }

}

class newScene extends Group{
    private Color color = Color.AQUAMARINE;
    private TextField inputText = new TextField();
    private Button lock;
    public Scene newScenee(){
        Group group = new Group();
        Rectangle rectangle = new Rectangle(400,600,Color.AQUAMARINE);
        rectangle.getTransforms().add(new Translate(0,0));

        group.getChildren().addAll(rectangle,temprature_segment(),security(),playAudio(),timeanddate(),color_set());
        Scene scene = new Scene(group,400,600);
        return scene;
    }

    public Color getColor(){
        return color;
    }

    private Group color_set(){
        Group group = new Group();
        Text text = text_update("Color Settings",30,230);
        // for main model scene
        Text main_modeltxt = text_update("Main Model",50,290);
        main_modeltxt.setFont(Font.font(14));
        TextField main_model = set_construct(180,280);
        main_model.setOnMouseClicked(e->{
            if(main_model.getText().equals("blue"))
                color= Color.valueOf("#21cfde");
            else if(main_model.getText().equals("green"))
                color=Color.valueOf("#17e8aa");
            else if(main_model.getText().equals("purple"))
                color=Color.valueOf("#e372ca");
            else if(main_model.getText().equals("black"))
                color=Color.valueOf("#080808");
            else if(main_model.getText().equals("red"))
                color=Color.valueOf("#f82c2b");
            else if(main_model.getText().equals("orange"))
                color=Color.valueOf("#f6b21d");
            else if(main_model.getText().equals("brown"))
                color=Color.valueOf("#906608");
            else if(main_model.getText().equals("yellow"))
                color=Color.valueOf("#dcdb2e");
            else if(main_model.getText().equals("light blue"))
                color=Color.valueOf("#21c8de");
        });
        group.getChildren().addAll(main_model,text,main_modeltxt);
        return group;
    }

    private TextField set_construct(int x_translate,int y_translate){
        TextField textField = new TextField();
        textField.getTransforms().add(new Translate(x_translate,y_translate));
        return textField;
    }

    private Group playAudio(){
        Group grp = new Group();
        Text music = text_update("Music",40,350);
        AudioClip note = new AudioClip(this.getClass().getResource("comedy.wav").toString());
        Button playmusic = new Button("play Music");
        playmusic.getTransforms().add(new Translate(80,380));
        playmusic.setOnMousePressed(e->{
            if(note.isPlaying())
                note.stop();
            else
               note.play();
        });
        grp.getChildren().addAll(playmusic,music);
        return grp;
    }

    private Group timeanddate(){
        Group grp = new Group();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        Text text=text_update(date.toString(),40,500);
        grp.getChildren().add(text);
        Text sent = text_update("Today is",40,450);
        grp.getChildren().add(sent);
        return grp;
    }

    private Slider Temprature(){
        Slider slider = new Slider();
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMax(200);
        slider.setMin(-40);
        slider.setLayoutX(80);
        slider.setLayoutY(45);
        return slider;
    }

    public String getInputText() {
        return lock.getText();
    }

    private Group temprature_segment(){
        Group temprature_seg = new Group();
        Slider slider = Temprature();
       // slider.getTransforms().add(new Translate(-30,-20));
        inputText.getTransforms().add(new Translate(15,80));
        Text temp_text = text_update("Temprature",25,35);
        inputText.setText("0");
        inputText.setMaxWidth(50.0);
        inputText.setOnKeyPressed((e)->{
            if(e.getCode() == KeyCode.ENTER){
                String string= inputText.getText();
                slider.setValue(string.toCharArray()[0]);
            }
        });
        inputText.focusedProperty().addListener((obs, o, n)->{
            if(!n.booleanValue()){
                inputText.setText(String.valueOf((int)slider.getValue()));
            }
        });
        slider.valueProperty().addListener((obs, o, n)->{
            inputText.setText(String.valueOf((int)slider.getValue()));
        });
        temprature_seg.getChildren().addAll(slider,inputText,temp_text);
        return temprature_seg;
    }
    private Text text_update(String input,int x,int y){
        Text temp_text = new Text();
        temp_text.setText(input);
        temp_text.getTransforms().add(new Translate(x,y));
        temp_text.setFont(Font.font("serif", FontWeight.LIGHT, FontPosture.ITALIC,20));
        return temp_text;
    }
    private Group security(){
        Group group = new Group();
        Text security = text_update("Security",280,40);
        lock = new Button("locked");
        lock.setPadding(new Insets(15));
        lock.getTransforms().add(new Translate(280,80));
        lock.setOnMousePressed(e->{
            if(lock.getText()=="locked")
                lock.setText("unlocked");
            else
                lock.setText("locked");
        });
        group.getChildren().addAll(security,lock);
        return group;
    }
}