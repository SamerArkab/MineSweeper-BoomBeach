package gameGUI;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainFX extends Application {
	static Media h = new Media(
			Paths.get("C:\\Users\\samra\\Documents\\GitHub\\MineSweeper-BoomBeach\\Flavour-Wataboi.mp3").toUri()
					.toString());
	static MediaPlayer mediaPlayer = new MediaPlayer(h);;
	static int onOoff = 0;
	static int check = 0, check2 = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			Image backgroundColor = new Image("file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/menu.jpg");
			BackgroundSize backgroundSize = new BackgroundSize(1280, 720, true, true, true, true);
			BackgroundImage backgroundImage = new BackgroundImage(backgroundColor, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

			FXMLLoader loader = new FXMLLoader(); // Create loading object
			loader.setLocation(getClass().getResource("MainMenuFXML.fxml")); // fxml location
			VBox root = loader.load(); // Load layout
//			root.setStyle("-fx-background-image: url(\"file:///C:/Users/samra/Documents/GitHub/MineSweeper-BoomBeach/Images/menu.jpg\")");
			Scene scene = new Scene(root); // Create scene with chosen layout

//			primaryStage.setFullScreen(true);
			root.setBackground(new Background(backgroundImage));

			primaryStage.setTitle("BOOM Beach"); // Set stage's title

//			primaryStage.setMinWidth(400); // Won't be allowed to make width/height smaller
//			primaryStage.setMinHeight(350);
//			primaryStage.setMaxWidth(600); // It will put constraints on the new scenes!!
//			primaryStage.setMaxHeight(450);
			primaryStage.setScene(scene); // Set scene to stage
			primaryStage.show(); // Show stage
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static boolean music() {
		check = onOoff;
		check2 = check % 2;
		if (check2 == 0) {
			mediaPlayer.play();
			onOoff++;
			return true;
		} else {
			mediaPlayer.stop();
			onOoff++;
			return false;
		}
	}

}