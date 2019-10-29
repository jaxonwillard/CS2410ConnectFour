import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ConnectFour extends Application {
    private int stageWidth = 600;
    private int circleRadius = 35;


    public void start(Stage primaryStage){
        // establish stage, grid,  and pane
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageWidth);
        primaryStage.setTitle("Connect Four");
        Pane pane = new Pane();
//
//        String cat = "http://placekitten.com/200/300";
//        String nic  = "https://www.placecage.com/200/500";
//        Image image = new Image(nic);
//        ImageView imageView = new ImageView(image);
//        imageView.setX(50);
//        imageView.setY(50);
//        imageView.setFitWidth(300);
//        imageView.setFitHeight(300);
//        Text text = new Text("Hello");
//        text.setX(50);
//        text.setY(50);
//        pane.getChildren().add(text);
//        pane.getChildren().add(imageView);

//







        ConnectFourGrid grid = new ConnectFourGrid(pane, circleRadius, stageWidth, stageWidth);
        for ( int i=0; i < 7; i++ ){
            for ( int j=0; j < 6; j++ ){
                pane.getChildren().add(grid.ConnectFourGrid[i][j].circle);
            }
        }

//         show window
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();




    }

}
