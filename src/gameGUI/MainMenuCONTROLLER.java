package gameGUI;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class MainMenuCONTROLLER {

	@FXML
	private Button NewGame;

	@FXML
	private Button QuitGame;

	@FXML
	private Button Extras;

	@FXML
	private Button ExtraExtras;

	@FXML
	void ExtraExtras(ActionEvent event) throws IOException {

		Image backgroundColor = new Image("file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/Secret.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ExtraExtrasFXML.fxml"));
		AnchorPane root = loader.load();
//		root.setStyle("-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/Secret.jpg\")");
		Scene scene = new Scene(root);
		Stage extrasStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		extrasStage.setTitle("Any guesses?");

//		extrasStage.setFullScreen(true);
		root.setBackground(new Background(backgroundImage));

		/*
		 * extrasStage.setMinHeight(700); extrasStage.setMinWidth(500);
		 * extrasStage.setMaxHeight(700); extrasStage.setMaxWidth(500);
		 */
		extrasStage.setScene(scene);
		extrasStage.show();
	}

	@FXML
	void Extras(ActionEvent event) {
		Extras.setOnAction(e -> {
			boolean check = MainFX.music();
			if (check)
				Extras.setText("ON");
			else
				Extras.setText("OFF");
		});
	}

	@FXML
	void NewGame(ActionEvent event) throws IOException {

		Image backgroundColor = new Image("file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/backgroundImage.png");
		BackgroundSize backgroundSize = new BackgroundSize(1280, 853, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewGameFXML.fxml"));
		AnchorPane root = loader.load();
//		root.setStyle("-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/backgroundImage.png\")");
		Scene scene = new Scene(root);
		Stage GameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

//		GameStage.setFullScreen(true);
		root.setBackground(new Background(backgroundImage));

//		GameStage.setMinWidth(1000); // Won't be allowed to make width/height smaller
//		GameStage.setMinHeight(500);
//		GameStage.setMaxWidth(2000);
//		GameStage.setMaxHeight(1000);
		GameStage.setTitle("BOOM Beach");
		GameStage.setScene(scene);
		GameStage.show();
	}

	@FXML
	void QuitGame(ActionEvent event) {
		Platform.exit(); // Quit game
	}

}