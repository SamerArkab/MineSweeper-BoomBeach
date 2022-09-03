package gameGUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardCONTROLLER {
	Button b;

	@FXML
	public GridPane TheBoard;

	@FXML
	public Label winLose;

	@FXML
	private Button BackMenu;

	@FXML
	private Button Music;

	@FXML
	private Button ResetBoard;

	@FXML
	void BackMenu(ActionEvent event) throws IOException {

		Image backgroundColor = new Image("file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/menu.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(1280, 720, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		FXMLLoader loader = new FXMLLoader(); // Create loading object
		loader.setLocation(getClass().getResource("MainMenuFXML.fxml")); // fxml location
		VBox root = loader.load(); // Load layout
//		root.setStyle("-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/menu.jpg\")");
		Scene scene = new Scene(root); // Create scene with chosen layout
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

//		primaryStage.setFullScreen(true);
		root.setBackground(new Background(backgroundImage));

		primaryStage.setTitle("BOOM Beach"); // Set stage's title
//		primaryStage.setMinWidth(400); // Won't be allowed to make width/height smaller
//		primaryStage.setMinHeight(350);
//		primaryStage.setMaxWidth(600);
//		primaryStage.setMaxHeight(450);
		primaryStage.setScene(scene); // Set scene to stage
		primaryStage.show(); // Show stage
	}

	@FXML
	void Music(ActionEvent event) {
		Music.setOnAction(e -> {
			boolean check = MainFX.music();
			if (check)
				Music.setText("ON");
			else
				Music.setText("OFF");
		});
	}

	@FXML
	void ResetBoard(ActionEvent event) throws IOException {

		Image backgroundColor = new Image("file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/backgroundImage.png");
		BackgroundSize backgroundSize = new BackgroundSize(1280, 853, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		NewGameCONTROLLER.game = new gameLogic.Mines(NewGameCONTROLLER.rows, NewGameCONTROLLER.columns,
				NewGameCONTROLLER.mines);

		FXMLLoader loader = new FXMLLoader(); // Create loading object
		loader.setLocation(getClass().getResource("BoardFXML.fxml")); // fxml location

		AnchorPane root = loader.load(); // Load layout
//		root.setStyle("-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/backgroundImage.png\")");
		Scene scene = new Scene(root); // Create scene with chosen layout
		Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

//		gameStage.setFullScreen(true);
		root.setBackground(new Background(backgroundImage));

		gameStage.setTitle("BOOM Beach"); // Set stage's title
//		gameStage.setMinWidth(1000); // Won't be allowed to make width/height smaller
//		gameStage.setMinHeight(500);
//		gameStage.setMaxWidth(2000);
//		gameStage.setMaxHeight(1000);
		gameStage.setScene(scene); // Set scene to stage

		BoardCONTROLLER bCont = loader.getController(); // Prepare board in BoardCONTROLLER

		bCont.winLose.setVisible(false);

		for (int i = 0; i < NewGameCONTROLLER.columns; i++) {
			for (int j = 0; j < NewGameCONTROLLER.rows; j++) {
				b = new Button(" ");
				b.setMinSize(30, 30);
				b.setMaxSize(30, 30);
				b.setStyle("-fx-font-size:11");
				bCont.TheBoard.add(b, i, j);
				GridPane.setMargin(b, new Insets(5, 5, 5, 5));
				GridPane.setHalignment(b, HPos.CENTER);
				GridPane.setValignment(b, VPos.CENTER);
			}
		}
		for (int i = 0; i < bCont.TheBoard.getChildren().size(); i++) {
			((ButtonBase) bCont.TheBoard.getChildren().get(i)).setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int x, y;
					y = (int) ((Button) event.getSource()).getProperties().get("gridpane-column");
					x = (int) ((Button) event.getSource()).getProperties().get("gridpane-row");
					if (event.getButton().equals(MouseButton.PRIMARY))
						NewGameCONTROLLER.game.open(x, y);
					else if (event.getButton().equals(MouseButton.SECONDARY))
						NewGameCONTROLLER.game.toggleFlag(x, y);
					else if (event.getButton().equals(MouseButton.MIDDLE))
						NewGameCONTROLLER.game.toggleQM(x, y);
					for (Node child : bCont.TheBoard.getChildren()) {
						int j = (int) ((Button) child).getProperties().get("gridpane-column");
						int i = (int) ((Button) child).getProperties().get("gridpane-row");
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T') {
							((Button) child).setText(NewGameCONTROLLER.game.board[i][j].substring(2, 3));
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'F'
								&& NewGameCONTROLLER.game.board[i][j].charAt(0) == 'D')
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/flag.png\")");
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'F'
								&& NewGameCONTROLLER.game.board[i][j].charAt(0) == '?')
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/QM.jpg\")");
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == 'E') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/empty.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == 'M') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/mine.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == 'B') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/boom.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '1') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/1.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '2') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/2.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '3') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/3.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '4') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/4.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '5') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/5.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '6') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/6.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '7') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/7.jpg\")");
							((Button) child).setText(" ");
						}
						if (NewGameCONTROLLER.game.board[i][j].charAt(1) == 'T'
								&& NewGameCONTROLLER.game.board[i][j].charAt(2) == '8') {
							((Button) child).setStyle(
									"-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/8.jpg\")");
							((Button) child).setText(" ");
						}
						if (gameLogic.Mines.winLose == 1) {
							bCont.winLose.setVisible(true);
							bCont.winLose.setText("YOU WIN!");
						}
						if (gameLogic.Mines.winLose == 0) {
							bCont.winLose.setVisible(true);
							bCont.winLose.setText("YOU LOSE! Try again...");
						}
						gameLogic.Mines.winLose = -1;
					}
				}
			});
		}
		gameStage.show(); // Show stage }
	}
}