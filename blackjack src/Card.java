package project;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {

    public static final String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q"};
    public static final int HEIGHT = 130;

    private String face;

    public Card(String face) {
        this.face = face;
        
        setImage(new Image(getClass().getResource("/cards/" + face + ".png").toString()));
        setFitHeight(HEIGHT);
        setPreserveRatio(true);
    }

    public String getFace() {
        return face;
    }

    public int valueOf() {
    	switch (face) {
        case "A":
            return 11;
        case "K":
        case "Q":
        case "J":
            return 10;
        default:
            return Integer.parseInt(face);
    	}
    }
    	
}
