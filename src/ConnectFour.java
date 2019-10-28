import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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



        ConnectFourGrid grid = new ConnectFourGrid(pane, circleRadius, stageWidth, stageWidth);
        for ( int i=0; i < 7; i++ ){
            for ( int j=0; j < 6; j++ ){
                pane.getChildren().add(grid.ConnectFourGrid[i][j].circle);
            }
        }

        // show window
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();




    }
    private void addCircle(Player player){

    }
}
