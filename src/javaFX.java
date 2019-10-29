import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;


public class javaFX extends Application {
    int x = 0;
    int y = 0;
    private ImageView iv = new ImageView();
    private Image im = new Image("http://placekitten.com/" + x + "/" + y);

    public void start(Stage primaryStage){
        Pane pane = new Pane();
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        Button bt = new Button("Press Me For Kittens");
        bt.setOnAction(e -> {
            im = new Image("http://placekitten.com/" + y + "/" + x);
            iv = new ImageView(im);
            iv.setX(50);
            iv.setY(50);
            iv.setFitHeight(300);
            iv.setFitWidth(300);

            pane.getChildren().set(1, iv);
            Integer ranInt = new Random().nextInt();
            x = x + new Random().nextInt(300);
            y = y + new Random().nextInt(300);
        });
        pane.getChildren().add(bt);
        pane.getChildren().add(iv);
        Scene sc = new Scene(pane);
        primaryStage.setScene(sc);
        primaryStage.show();


    }
}



