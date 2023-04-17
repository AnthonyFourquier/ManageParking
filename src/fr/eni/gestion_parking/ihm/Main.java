package fr.eni.gestion_parking.ihm;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application{
	
	  @Override
	    public void start(Stage primaryStage) throws Exception{
	        Parent root = FXMLLoader.load(getClass().getResource("MyScene.fxml"));
	        primaryStage.setTitle("Gestion Parking Apps");
	        primaryStage.setScene(new Scene(root, 600, 400));
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
		
}
