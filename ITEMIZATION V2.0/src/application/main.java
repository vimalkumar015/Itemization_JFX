package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class main extends Application {
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		//this.primaryStage = primaryStage;
		setPrimaryStage(primaryStage);
		Parent root = FXMLLoader.load(getClass().getResource("loginview.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
	private void setPrimaryStage(Stage stage)
	{
		main.primaryStage = stage;
	}
	
	static public Stage getPrimaryStage() {
		return main.primaryStage;
	}
	
	
}
