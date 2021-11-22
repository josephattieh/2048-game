package com.josephattieh.project;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CustomCell extends StackPane{

    Text text;
    Rectangle rectangle;
    int n;
    
	public CustomCell(int n, double size) {
		rectangle = new Rectangle(size,size);
		rectangle.setArcWidth(10.0); 
		rectangle.setArcHeight(10.0);  
		rectangle.setFill(getBackground(n));
	    if(n!=0)
		text = new Text(n+"");
	    else text = new Text("");
		text.setFill(getForeground(n));
		text.setFont(Font.font("Verdana", FontWeight.BOLD, getSize(n)));
		
		this.n = n;
		getChildren().addAll(rectangle,text);
		
	}
	
	public int getSize(int n) {
		if( n < 100) return 32;
		if( n < 1000) return 28 ;
		return 24;
	}
	
	//those colors were retrieved from:
	//https://scrambledeggsontoast.github.io/2014/05/09/writing-2048-elm/
	public Color getBackground(int n) {
        switch (n) {
        	case 0:     return Color.rgb(205, 193, 180, 1.0);
            case 2:		return Color.rgb(238, 228, 218, 1.0); 
            case 4: 	return Color.rgb(237, 224, 200, 1.0); 
            case 8: 	return Color.rgb(242, 177, 121, 1.0);
            case 16: 	return Color.rgb(245, 149, 99, 1.0); 
            case 32: 	return Color.rgb(246, 124, 95, 1.0); 
            case 64:	return Color.rgb(246, 94, 59, 1.0 ); 
            case 128:	return Color.rgb(237, 207, 114, 1.0);
            case 256: 	return Color.rgb(237, 204, 97, 1.0); 
            case 512: 	return Color.rgb(237, 200, 80, 1.0); 
            case 1024: 	return Color.rgb(237, 197, 63, 1.0); 
            case 2048: 	return Color.rgb(237, 194, 46, 1.0); 
        }
        return Color.rgb(238, 228, 218, 0.35);
    }
	public Color getForeground(int n) {
        Color foreground;
        if(n < 16) {
            foreground = Color.rgb(119, 110, 101, 1.0); //0x776e65
        } else {
            foreground = Color.rgb(249, 246, 242, 1.0);    //0xf9f6f2
        }
        return foreground;
    }

	
}
