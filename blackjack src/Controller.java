package project;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
/**
 * Name: Josh Nguyen
 * Username: nguyj03
 */
public class Controller {

	@FXML
	private ImageView logoImageView;
	@FXML
    private Button hitButton;
    @FXML
    private Button standButton;
    @FXML
    private Button playButton;
    @FXML
    private HBox playerHandBox;
    @FXML
    private HBox dealerHandBox;
    @FXML
    private Label playerHandValue;
    @FXML
    private Label dealerHandValue;
    @FXML
    private Label gameResult;
    @FXML
    private Label dealerWins;
    @FXML
    private Label playerWins;
    private Player player;
    private Player dealer;

    public Controller() {
        player = new Player();
        dealer = new Player();
    }

    public void initialize() {
        //Initialize player and dealer and hide Hit and Stand Buttons.
        playerHandBox.getChildren().clear();
        dealerHandBox.getChildren().clear();
        hitButton.setVisible(false);
        standButton.setVisible(false);
        gameResult.setVisible(false);
        logoImageView.setImage(new Image(getClass().getResource("/bj_logo.png").toString()));

    }

    public void updateHand(Player p, HBox handBox, Label handValue) {
        //Show the given Player's hand in the hand display and update the hand value display
        handBox.getChildren().clear();
        for (Card card : p.getHand()) {
            ImageView cardImageView = new ImageView(card.getImage());
            cardImageView.setFitWidth(75);
            cardImageView.setFitHeight(100);
            handBox.getChildren().add(cardImageView);
            playerHandBox.setAlignment(Pos.CENTER);
            dealerHandBox.setAlignment(Pos.CENTER);
        }
        handValue.setText("Value: " + String.valueOf(p.valueOfHand()));
    }

    public void hit() {
        //Event for the Hit Button. Use player methods and updateHand
        player.hit();
        updateHand(player, playerHandBox, playerHandValue);
        if (player.busted()) {
            endGame();
        }
    }

    public void stand() {
        //Event for the Stand Button.
        while (!dealer.stand(player.valueOfHand())) {
            dealer.hit();
            updateHand(dealer, dealerHandBox, dealerHandValue);
            if (dealer.busted()) {
                break;
            }
        }
        endGame();
    }
    public void startGame() {
        //Event for the Play Button.
        Player.deck.reset();
        player.clearHand();
        dealer.clearHand();
        dealer.hit();

        updateHand(player, playerHandBox, playerHandValue);
        updateHand(dealer, dealerHandBox, dealerHandValue);
        gameResult.setText("");

        playButton.setVisible(false);
        hitButton.setVisible(true);
        standButton.setVisible(true);
    }
    
    public void endGame() {
        hitButton.setVisible(false);
        standButton.setVisible(false);
        playButton.setVisible(true);
        gameResult.setVisible(true);

        int playerValue = player.valueOfHand();
        int dealerValue = dealer.valueOfHand();

        if (player.busted()) {
            gameResult.setText("You busted! Dealer wins.");
            dealerWins.setText("Dealer Wins: " + dealer.win());
        } else if (dealer.busted()) {
            gameResult.setText("Dealer busted! You win!");
            playerWins.setText("Player Wins: " + player.win());
        } else if (playerValue > dealerValue) {
            gameResult.setText("You win!");
            playerWins.setText("Player Wins: " + player.win());
        } else if (playerValue < dealerValue) {
            gameResult.setText("Dealer wins!");
            dealerWins.setText("Dealer Wins: " + dealer.win());
        } else {
            gameResult.setText("It's a tie!");
        }
    }
}
