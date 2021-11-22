package com.josephattieh.project;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class M extends Application{

	public static void main(String[] args) {
		launch(args); 
		// TODO Auto-generated method stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Scene scene = new GameScene(primaryStage).getScene();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("2048 Game");
		primaryStage.show();
	
	}
	
}
