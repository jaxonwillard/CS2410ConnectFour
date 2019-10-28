import javafx.scene.paint.Color;

public class Player {
    public Color playerColor;
    public int playerNum;

    public Player (){
        playerColor = Color.TRANSPARENT;
        this.playerNum = -1;
    }

    public Player(Color color, int playerNum){
        this.playerNum = playerNum;
        playerColor = color;
    }



}
