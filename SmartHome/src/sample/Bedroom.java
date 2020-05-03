package sample;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;

public class Bedroom  {
    newScene newScene = new newScene();
    private SmartObject group = new SmartObject();
    private PDFViewer m_PDFViewer;
    public Scene Bedrm(){
        lighting(group);
        Camera camera1 = new PerspectiveCamera();
        Box wall = getbox(1450,900,20,405,696,62,Color.BLACK,"bed_room.jpg");
        Box harrypotter = getbox(80,60,30,480,1100,50,Color.BLACK,"harrypotter.jpg");
        group.getChildren().addAll(wall,movie("sleep",500,500,"D:\\IdeaProjects\\SmartHome\\src\\sample\\testing.mp4","movie_playing"),harrypotter);
        harrypotter.setOnMousePressed(e->{
            try {
                group.getChildren().add(pdfreader());
            } catch (PDFException ex) {
                ex.printStackTrace();
            }
        });

        // no adding beyond this point
        Scene scene = createscene(Color.WHITESMOKE,1400,800,true,camera1,group);
       // group.getChildren().add(lights(group,scene));
        return scene;
    }
   /* public Group lights(SmartObject group,Scene scene){
        PointLight light = new PointLight(Color.PEACHPUFF);
        group.getChildren().add(light);
        scene.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.T)
                group.getChildren().remove(light);
        });
        return group;
    }*/
   public void lighting(SmartObject group){
       PointLight pointLight = new PointLight();
       AnimationTimer timer = new AnimationTimer() {
           @Override
           public void handle(long l) {
               group.setOnKeyPressed(e->{
                   if(e.getCode()==KeyCode.T){
                       if(group.getChildren().contains(pointLight))
                           group.getChildren().remove(pointLight);
                       else
                           group.getChildren().add(pointLight);
                   }
               });
           }
       };
       timer.start();
   }
    public Scene createscene(Color scene_color,int width,int height,boolean deapth_buffer,Camera camera1,SmartObject group){
        Scene scene1 = new Scene(group,width,height,deapth_buffer);
        scene1.setFill(scene_color);
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
    private Group pdfreader() throws PDFException {
        Group group = new Group();
        m_PDFViewer = new PDFViewer();
        m_PDFViewer.loadPDF("harryptr");
       // m_PDFViewer.getDocument();
        group.getChildren().add(m_PDFViewer);
        return group;
    }
    public SmartObject getobj(){
        return this.group;
    }

    public Group movie(String button_name,int x_translate,int y_translate,String path,String title){
        Group group = new Group();
        Button button = new Button(button_name);
        button.getTransforms().add(new Translate(x_translate,y_translate));
        button.setOnMousePressed(e->{
           initiate(path,title);
        });
        group.getChildren().addAll(button);
        return group;
    }
    public void initiate(String path,String title){
        //The location of your file
        Media media = new Media(new File(path).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mediaView);
        //borderPane.setBottom(addToolBar());

        borderPane.setStyle("-fx-background-color: Black");

        Scene scene = new Scene(borderPane, 600, 600);
        scene.setFill(Color.BLACK);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,e->{
            if (e.getCode()==KeyCode.C) {
                    mediaPlayer.stop();
            }
        });
        stage.show();
    }

    public Box getbox(int width,int height,int depth,int x_translate,int y_translate,int z_translate,Color specularcolor,String imagecolor) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream(imagecolor)));
        material.setSpecularColor(specularcolor);
        Box box = new Box(width,height,depth);
        box.setMaterial(material);
        box.translateYProperty().set(x_translate);
        box.translateXProperty().set(y_translate);
        box.translateZProperty().set(z_translate);
        return box;
    }

}