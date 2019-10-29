import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class ConnectFourGrid {
    public Piece[][] ConnectFourGrid;
    public Pane pane;
    public Player player1 = new Player(Color.RED, 1);
    public Player player2 = new Player(Color.BLUE, 2);
    public boolean isP1Turn = true;
    public boolean gameOver;
    int rows = 6;
    int columns = 7;


    int x = 200;
    int y = 500;
    public ImageView iv = new ImageView();
    public Image im = new Image("https://www.placecage.com/200/500");

    public ImageView iv1 = new ImageView();
    public Image im1 = new Image("https://www.placecage.com/200/500");

    public ImageView iv2 = new ImageView();
    public Image im2 = new Image("https://www.placecage.com/200/500");

    public ImageView iv3 = new ImageView();
    public Image im3 = new Image("https://www.placecage.com/200/500");

    public ConnectFourGrid(Pane pane, int radius, int stageWidth, int stageHeight){
        this.gameOver = false;
        this.pane = pane;
        ConnectFourGrid = new Piece[columns][rows];
        for (int i=0; i < columns; i++){
            for (int j=0; j < rows; j++){
                this.ConnectFourGrid[i][j] = new Piece(true, new Player(), radius, (i * stageWidth / columns) + radius ,
                        (j * stageHeight / columns) + radius + 15, i , j, this);
            }
        }
    }
    public int putToBottom(int i, int j){
        if (j < rows - 1){
            if (ConnectFourGrid[i][j+1].isEmpty)
            return putToBottom(i, j+1);
        }
        return j;
    }

    public void endGame(Piece[] pieces){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(3000), e -> {
            for(Piece pc : pieces) {
                im = new Image("https://www.placecage.com/" + x  + "/" +  y);
//                im = new Image("https://www.placecage.com/200/500");
                iv = new ImageView(im);
                iv.setX(pc.xCoord - 30);
                iv.setY(pc.yCoord - 30);
                iv.setFitHeight(60);
                iv.setFitWidth(60);
                pane.getChildren().add(iv);

                x = x + new Random().nextInt(300);
                y = y + new Random().nextInt(300);


            }

        }));

        t1.setCycleCount(1000);
        t1.play();

//        flashPieces(pieces);
        this.gameOver = true;
    }

    public void flashPieces(Piece[] pieces){
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(750), e -> {
            Color color = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            for (Piece pc : pieces){
                pc.circle.setFill(color);
            }}));
        t1.setCycleCount(-1);
        t1.play();
    }

    public void checkBoard(){
        for (int i=0; i < columns; i++){
            for (int j=0; j < rows; j++){
//                if ( checkIsWinnerUp(i,j) != null || checkIsWinnerLeft(i,j) != null || checkIsWinnerUpLeft(i,j) != null
//                        || checkIsWinnerDownRight(i,j) != null ){
//                    addWinnerNode(i,j);
//                }
                if ( checkIsWinnerDownLeft(i,j) != null)endGame(checkIsWinnerDownLeft(i,j));
                if ( checkIsWinnerUpLeft(i,j) != null) endGame(checkIsWinnerUpLeft(i,j));
                if ( checkIsWinnerUp(i,j) != null) endGame(checkIsWinnerUp(i,j));
                if ( checkIsWinnerLeft(i,j) != null) endGame(checkIsWinnerLeft(i,j));
            }
        }
    }
    public boolean checkIsWinnerDown(int i, int j){
        if (j - rows < rows + 1){
            if (j + 3 < rows){
                if (ConnectFourGrid[i][j+1].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                        ConnectFourGrid[i][j+2].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                        ConnectFourGrid[i][j+3].player.playerNum == ConnectFourGrid[i][j].player.playerNum) {
                    return true;
                }
            }
        }
        return false;
    }
    public Piece[] checkIsWinnerUp( int i, int j){
        if (j - rows < 0){
            if (j - 3 > 0){
                if (!ConnectFourGrid[i][j].isEmpty && !ConnectFourGrid[i][j-1].isEmpty && !ConnectFourGrid[i][j-2].isEmpty
                        && !ConnectFourGrid[i][j-3].isEmpty &&
                        ConnectFourGrid[i][j-1].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                        ConnectFourGrid[i][j-2].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                        ConnectFourGrid[i][j-3].player.playerNum == ConnectFourGrid[i][j].player.playerNum) {
                    Piece[] pieces = new Piece[4];
                    for (int k=0; k<4; k++){
                        pieces[k] = ConnectFourGrid[i][j-k];
                    }
                    return pieces;
                }
            }
        }
        return null;
    }
    public Piece[] checkIsWinnerLeft( int i, int j){
        if (i - 3 >= 0) {
            if (!ConnectFourGrid[i][j].isEmpty && !ConnectFourGrid[i - 1][j].isEmpty && !ConnectFourGrid[i - 2][j].isEmpty &&
                    !ConnectFourGrid[i - 3][j].isEmpty &&
                    ConnectFourGrid[i - 1][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i - 2][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i - 3][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum) {
                Piece[] pieces = new Piece[4];
                for (int k = 0; k < 4; k++) {
                    pieces[k] = ConnectFourGrid[i - k][j];
                }
                return pieces;
            }
        }
        return null;
    }
    public Piece checkIsWinnerRight( int i, int j){
        if (i + 3 <= rows){
            if (!ConnectFourGrid[i][j].isEmpty && !ConnectFourGrid[i+1][j].isEmpty && !ConnectFourGrid[i+2][j].isEmpty &&
                    !ConnectFourGrid[i+3][j].isEmpty &&
                    ConnectFourGrid[i+1][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i+2][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i+3][j].player.playerNum == ConnectFourGrid[i][j].player.playerNum
            )
                return ConnectFourGrid[i][j];
        }
        return null;
    }
    public Piece[] checkIsWinnerUpLeft( int i, int j ){
        if (!ConnectFourGrid[i][j].isEmpty && i - 3 >= 0 && j - 3 >= 0){
            if ( !ConnectFourGrid[i-1][j-1].isEmpty && !ConnectFourGrid[i-2][j-2].isEmpty &&
                    !ConnectFourGrid[i-3][j-3].isEmpty &&
                    ConnectFourGrid[i-1][j-1].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i-2][j-2].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i-3][j-3].player.playerNum == ConnectFourGrid[i][j].player.playerNum){
                Piece[] pieces = new Piece[4];
                for (int k=0; k<4; k++){
                    pieces[k] = ConnectFourGrid[i-k][j-k];
                }
                return pieces;            }
        }
        return null;
    }
    public Piece[] checkIsWinnerDownLeft( int i, int j ){
        if (!ConnectFourGrid[i][j].isEmpty && i - 3 >= 0 && j + 3 < rows){
            if ( !ConnectFourGrid[i-1][j+1].isEmpty && !ConnectFourGrid[i-2][j+2].isEmpty &&
                    !ConnectFourGrid[i-3][j+3].isEmpty &&
                    ConnectFourGrid[i-1][j+1].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i-2][j+2].player.playerNum == ConnectFourGrid[i][j].player.playerNum &&
                    ConnectFourGrid[i-3][j+3].player.playerNum == ConnectFourGrid[i][j].player.playerNum){
                Piece[] pieces = new Piece[4];
                for (int k=0; k<4; k++){
                    pieces[k] = ConnectFourGrid[i-k][j+k];
                }
                return pieces;
            }
        }
        return null;
    }


}
