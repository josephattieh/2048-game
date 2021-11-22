package com.josephattieh.project; 

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextPane extends BorderPane {

	Text t ;
	public TextPane(String s, String msg, EventHandler<ActionEvent> e) {
		
		t= new Text(s);
		t.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		setTop(t);
		setAlignment(t, Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.web("bbada0"), null, null)));
		getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Button b = new Button(msg);
		b.setOnAction(e);
		setAlignment(b, Pos.CENTER);
		setCenter(b);
	}
	
}
