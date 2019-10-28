import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    public boolean isEmpty;
    public Player player;
    public Circle circle;
    private int i;
    private int j;
    connectFourGrid grid;

    public Piece(boolean isEmpty, Player player, int radius, int xCoord, int yCoord, int i, int j, connectFourGrid grid) {
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
            if (isEmpty) {
                System.out.println("i: " + i);
                int bottom = grid.putToBottom(i, j);
                if (grid.isP1Turn) {
                    this.player = grid.player1;
                    grid.connectFourGrid[i][bottom].populatePiece(grid.player1);
                } else {
                    grid.connectFourGrid[i][bottom].populatePiece(grid.player2);
                    this.player = grid.player2;
                }
                grid.isP1Turn = (!grid.isP1Turn);
//                grid.checkIsWinnerUp(player, i, j);
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
