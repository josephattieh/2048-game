package com.josephattieh.project;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameScene {
Scene scene;
	public GameScene(Stage stage) {
		GameCells t = new GameCells(stage);
		

		StackPane sp = new StackPane();
		
		sp.setBackground(new Background(new BackgroundFill(Color.rgb(250,248,239), null, null)));
		sp.getChildren().add(t);
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		scene = new Scene(sp, bounds.getWidth(), bounds.getHeight());
		t.requestFocus();
		
		scene.setOnSwipeDown(e->{ t.moveValuesUD(false);});
		scene.setOnSwipeUp(e->{ t.moveValuesUD(true);});
		scene.setOnSwipeRight(e->{ t.moveValuesLR(false);});
		scene.setOnSwipeLeft(e->{ t.moveValuesLR(true);});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @SuppressWarnings("incomplete-switch")
			@Override
            public void handle(KeyEvent event) {
              
                    switch (event.getCode()) {
                        case LEFT:
                        	 t.moveValuesLR(true);
                            break;
                        case RIGHT:
                        	t.moveValuesLR(false);
                            break;
                        case DOWN:
                        	t.moveValuesUD(false);
                            break;
                        case UP:
                        	t.moveValuesUD(true);
                            break;
                    }
                }
            
        });
		
	}
	public Scene getScene() {
		return scene;
	}
}
