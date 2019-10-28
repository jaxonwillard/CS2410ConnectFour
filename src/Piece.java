import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    public boolean isEmpty;
    public Player player;
    public Circle circle;
    private int i;
    private int j;
    ConnectFourGrid grid;

    public Piece(boolean isEmpty, Player player, int radius, int xCoord, int yCoord, int i, int j, ConnectFourGrid grid) {
        this.grid = grid;
        this.i = i;
        this.j = j;
        this.isEmpty = isEmpty;
        this.player = player;
        this.circle = new Circle(radius, player.playerColor);
        circle.setCenterX(xCoord);
        circle.setCenterY(yCoord);
        circle.setStroke(Color.BLACK);
        if (isEmpty) circle.setFill(Color.TRANSPARENT);

        circle.setOnMouseClicked(e -> {
            if (isEmpty && !grid.gameOver) {
                int bottom = grid.putToBottom(i, j);
                if (grid.isP1Turn) {
                    grid.ConnectFourGrid[i][bottom].player = grid.player1;
                    grid.ConnectFourGrid[i][bottom].populatePiece(grid.player1);
                }

                else {
                    grid.ConnectFourGrid[i][bottom].player = grid.player2;
                    grid.ConnectFourGrid[i][bottom].populatePiece(grid.player2);
                }

                grid.isP1Turn = (!grid.isP1Turn);
//                grid.checkIsWinnerDown(grid.ConnectFourGrid[i][bottom].player, i, bottom);
                grid.checkBoard();
            }
        });
    }

    public void populatePiece( Player player ){
        if (isEmpty) {
            this.player = player;
            this.isEmpty = false;
            circle.setFill(player.playerColor);
        }
    }



}
