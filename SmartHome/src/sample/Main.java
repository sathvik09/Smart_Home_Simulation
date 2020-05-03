package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class Main extends Application {

    private double anchorX,anchorY;
    private double anchorAngleX;
    private double anchorAngleY;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private PointLight a = new PointLight();
    @Override

    public void start(Stage primaryStage){
        main_screen main = new main_screen();
        Scene scene = main.mainscene();

     //  elements of the house
        SmartObject group1 = new SmartObject();
        group1.getChildren().addAll(getCreateLight());
        //group1.getChildren().add(new AmbientLight());
        Box base_for_scene2;
        Sphere sphere1;
        sphere1 = gym();
        Box wall;
        Box wall1,wall2,wall3,bed,kitchen;
        kitchen=prepareKitchen();
        bed=prepareBed();
        wall3=getWall3();
        wall2= getWall2();
        wall1= getWall1();
        wall = getWall();
        base_for_scene2 = base_for_scene2();
        group1.getChildren().add(base_for_scene2);
        group1.getChildren().addAll(wall,wall1,wall2,wall3);
        group1.getChildren().addAll(bed,kitchen,sphere1,walls_side());

        //making the scene of house
        Scene scene1 = new Scene(group1,1400,800,true);
        Camera camera1 = new PerspectiveCamera();
        scene1.setFill(Color.BISQUE);
        scene1.setCamera(camera1);

        // animation for the lights and moving light
        newScene newScene=  new newScene();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
               a.setRotate(a.getRotate() + 1);
               a.setColor(newScene.getColor());
            }
        };

        timer.start();
          /// the house design
        Scene control_sc = newScene.newScenee();

        // for main control
        Button contols = new Button("Controls");
        contols.getTransforms().add(new Translate(20,20));
        contols.getTransforms().add(new Translate(-50,-70,200));
        Stage stage = new Stage();
        group1.getChildren().add(contols);
        contols.setOnMouseClicked(e->{
            stage.setTitle("Control");
            stage.setScene(control_sc);
            stage.resizableProperty().setValue(false);
            stage.show();
        });


        scene1.addEventHandler(KeyEvent.KEY_PRESSED,e->{
            switch (e.getCode()){
                case S : group1.translateZProperty().set(group1.getTranslateZ() + 30);
                    break;
                case W : group1.translateZProperty().set(group1.getTranslateZ() - 30);
                    break;
                case A: group1.translateXProperty().set(group1.getTranslateX() + 30);
                    break;
                case D: group1.translateXProperty().set(group1.getTranslateX() - 30);
                    break;
                case NUMPAD5: group1.RoatateByY(25);
                    break;
                case NUMPAD2: group1.RoatateByY(-25);
                    break;
            }
        });
        group1.translateXProperty().set(700);
        group1.translateYProperty().set(400);
        group1.translateZProperty().set(-1100);
        initMouseControl(group1,scene1);

        Bedroom bedd = new Bedroom();
        Scene bedd_sc = bedd.Bedrm();

        //living room scene
        livingroom Livingroom = new livingroom();
        Scene livn_room = Livingroom.livin();

        //kitchen
        kitchen kitchen1 = new kitchen();
        Scene kitn = kitchen1.kitchen_scene();

        // error scene
         error Er = new error();
         Scene errscene = Er.error_msg();

         // gym scene
        Gym gym = new Gym();
        Scene gym_scene = gym.gym_scene();

         // changing scenes
        primaryStage.setTitle("Smart Home Simulation");
       primaryStage.setScene(scene);
       primaryStage.resizableProperty().setValue(false);
       // only if key matches then user is allowed to enter
       scene.addEventHandler(KeyEvent.KEY_PRESSED,e->{
           if(e.getCode()== KeyCode.ENTER && main.isSecurity()){
               primaryStage.setScene(scene1);
               main.setSecurity(false);
           }

           else if (e.getCode()==KeyCode.ENTER && !main.isSecurity())
               primaryStage.setScene(errscene);
       });
       errscene.addEventHandler(KeyEvent.KEY_PRESSED,e1->{
           if(e1.getCode()==KeyCode.BACK_SPACE)
               primaryStage.setScene(scene);
       });
       // can go back outside house only if it is unlocked
            cant_go_back cant = new cant_go_back();
        scene1.addEventHandler(KeyEvent.KEY_PRESSED,e2->{
            if(newScene.getInputText().equals("unlocked")){
                if(e2.getCode()==KeyCode.BACK_SPACE)
                    primaryStage.setScene(scene);

            }
            else{
                if(e2.getCode()==KeyCode.BACK_SPACE)
                    cant.error_msg_from_scene1();
            }
        });
        //moving to the bedroom KEY B, moving to living room KEY L, moving to kitchen KEY K
       primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,e1->{
           if (e1.getCode()==KeyCode.B){
               if(primaryStage.getScene()==scene1)
                   primaryStage.setScene(bedd_sc);
               else
                   primaryStage.setScene(scene1);
           }
           else if(e1.getCode()==KeyCode.L){
               if(primaryStage.getScene()==scene1)
                   primaryStage.setScene(livn_room);
               else
                   primaryStage.setScene(scene1);
           }
           else if(e1.getCode()==KeyCode.K){
               if(primaryStage.getScene()==scene1)
                   primaryStage.setScene(kitn);
               else
                   primaryStage.setScene(scene1);
           }
           else if(e1.getCode()==KeyCode.G){
               if(primaryStage.getScene()==scene1)
                   primaryStage.setScene(gym_scene);
               else
                   primaryStage.setScene(scene1);
           }
       });
        primaryStage.show();

    }
    // side walls design
    private Group walls_side(){
        Group group = new Group();
         Box side_wall1 = new Box(10,130,600);
         side_wall1.getTransforms().add(new Translate(-200,50,200));
        Box side_wall2 = new Box(10,130,600);
        side_wall2.getTransforms().add(new Translate(200,50,200));
        Box roof1 = new Box(10,290,600);
        roof1.getTransforms().addAll(new Translate(-120,-90,200),new Rotate(55));
        Box roof2 = new Box(10,280,600);
        roof2.getTransforms().addAll(new Translate(120,-90,200),new Rotate(-55));
         group.getChildren().addAll(side_wall1,side_wall2,roof1,roof2);
        return group;
    }
     // door in the 3d model
    public Box getDoor(int x_translate,int y_translate,int z_translate) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.BLACK);
        Box box = new Box(5,50,20);
        box.setMaterial(material);
        box.translateYProperty().set(-25);
        box.translateXProperty().set(-96);
        box.translateZProperty().set(-62);
        return box;
    }
     // pointlight design in the 3d house model
    //TextField field = new TextField();
    public Node[] getCreateLight() {
       a.getTransforms().add(new Translate(-200,-100,100));
       a.setColor(Color.AQUAMARINE);
       a.setRotationAxis(Rotate.Z_AXIS);
       // makling a sphere to show how the light will move during dynamic lighting
       Sphere sphere = new Sphere(5);
       sphere.getTransforms().setAll(a.getTransforms());
       sphere.rotateProperty().bind(a.rotateProperty());
       sphere.rotationAxisProperty().bind(a.rotationAxisProperty());
        return new Node[]{a,sphere};
    }
     // bed design
    private Box prepareBed(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("bed.jpg")));
        material.setSpecularColor(Color.BLACK);
        Box box = new Box(100,50,150);
        box.translateZProperty().set(400);
        box.translateXProperty().set(130);
        box.translateYProperty().set(100);
        box.setMaterial(material);
        return box;
    }
    private Box prepareKitchen(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("background.jpg")));
        //material.setSpecularColor(Color.BLACK);
        material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("illuminate.jpg")));
        Box box = new Box(100,50,150);
        box.translateZProperty().set(450);
        box.translateXProperty().set(-120);
        box.translateYProperty().set(90);
        box.setMaterial(material);
        return box;
    }

    private Sphere gym(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.WHITE);
        Sphere sphere = new Sphere(50,2);
        sphere.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        sphere.translateZProperty().set(10);
        sphere.translateXProperty().set(-140);
        sphere.translateYProperty().set(90);
        sphere.setMaterial(material);
        return sphere;
    }
     // creating base on which scene exists
    private Box base_for_scene2(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("img.jpg")));
        material.setSpecularColor(Color.BLACK);
        Box box = new Box(400,10,650);
        box.translateZProperty().set(250);
        box.translateYProperty().set(100);
        box.setMaterial(material);
        return box;
    }
    // partition design
    private Box getWall(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.WHITE);
        Box box = new Box(100,100,5);
       // box.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        box.translateXProperty().set(-150);
        box.translateZProperty().set(100);
        box.translateYProperty().set(50);
        box.setMaterial(material);
        return box;
    }

    private Box getWall1(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.WHITE);
        Box box = new Box(100,100,5);
        // box.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        box.translateXProperty().set(-150);
        box.translateZProperty().set(300);
        box.translateYProperty().set(50);
        box.setMaterial(material);
        return box;
    }

    private Box getWall2(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.WHITE);
        Box box = new Box(300,100,5);
         box.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        box.translateXProperty().set(50);
        box.translateZProperty().set(400);
        box.translateYProperty().set(50);
        box.setMaterial(material);
        return box;
    }

    private Box getWall3(){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("door.jpeg")));
        material.setSpecularColor(Color.WHITE);
        Box box = new Box(100,100,5);
       // box.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        box.translateXProperty().set(120);
        box.translateZProperty().set(30);
        box.translateYProperty().set(50);
        box.setMaterial(material);
        return box;
    }

      //** imp part:  here wer using mouse drag prpty and giving a 360 roation feature to the model, damn cool no?
    private void initMouseControl(SmartObject group,Scene scene){
          Rotate xrotate;
          Rotate yrotate;
          group.getTransforms().addAll(
                  xrotate= new Rotate(0,Rotate.X_AXIS),
                  yrotate= new Rotate(0,Rotate.Y_AXIS)
          );
          xrotate.angleProperty().bind(angleX);
          yrotate.angleProperty().bind(angleY);

          scene.setOnMousePressed( e -> {
              anchorX = e.getSceneX();
              anchorY = e.getSceneY();

              anchorAngleX = angleX.get();
              anchorAngleY = angleY.get();
          });

          scene.setOnMouseDragged(e->{
              angleX.set(anchorAngleX-(anchorY-e.getSceneY()));
              angleY.set(anchorAngleY+(anchorX-e.getSceneX()));
          });
    }


    public static void main(String[] args) {
        launch(args);
    }

}

