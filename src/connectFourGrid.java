import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class connectFourGrid {
    public Piece[][] connectFourGrid;
    public Player player1 = new Player(Color.RED);
    public Player player2 = new Player(Color.BLUE);
    public boolean isP1Turn = true;
    int rows = 6;
    int columns = 7;
    public connectFourGrid(int radius, int stageWidth, int stageHeight){
        connectFourGrid = new Piece[columns][rows];
        for (int i=0; i < columns; i++){
            System.out.println(i);
            for (int j=0; j < rows; j++){
                this.connectFourGrid[i][j] = new Piece(true, new Player(), radius, (i * stageWidth / columns) + radius ,
                        (j * stageHeight / columns) + radius + 15, i , j, this);
            }
        }
    }
    public int putToBottom(int i, int j){
        if (j < rows - 1){
            if (connectFourGrid[i][j+1].isEmpty)
            return putToBottom(i, j+1);
        }
        return j;
    }
    public void checkIsWinnerUp(Player player, int i, int j){
        if (j - rows < rows - 4){
            if (connectFourGrid[i][j-1].player == player){
                System.out.println("Winner!");
            }
        }
    }
}
