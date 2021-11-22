package com.josephattieh.project;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameCells extends GridPane {

	private static double size ;

	int n = 4;
	boolean win = false;
	boolean lose = false;
	int[][] values;
	Button b;
	Label Score;
	int score = 0;
	Stage stage;
	Spinner<Integer> spinner;

	//constructor needed to initialize the game
	public GameCells(Stage stage) {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		size= Math.min(bounds.getWidth(),  bounds.getHeight()) * 0.7;
		getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		this.stage = stage;
		values = new int[n][n];

		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		setMaxHeight(size);
		setMaxWidth(size);

		setVgap(size / (11 * n - 1));
		setHgap(size / (11 * n - 1));
		setAlignment(Pos.CENTER);

		b = new Button("Reset");
		b.setOnAction(E -> {
			score = 0;
			n = spinner.getValue();

			values = new int[n][n];
			Score.setText("Score: " + score);
			addValue();
			addValue();
			requestFocus();
		});
		b.setMaxWidth(Double.MAX_VALUE);
		add(b, n - 1, 0);

		Score = new Label("Score: " + score);
		Score.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
		add(Score, 0, 0, n - 1, 1);

		spinner = new Spinner<Integer>(4, 10, 1);
		add(spinner, 2, 0, 1, 1);
		setFocused(true);

		paint();
		addValue();
		addValue();

	}

	//method that adds 2 cells to the board
	public void addValue() {
		getChildren().clear();
		add(b, n - 1, 0);
		add(spinner, 2, 0, 1, 1);
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Integer> y = new ArrayList<>();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (values[i][j] == 0) {
					x.add(i);
					y.add(j);
				}
		if (x.size() != 0) {
			int index = (int) (Math.random() * x.size());
			values[x.get(index)][y.get(index)] = (Math.random() < 0.75) ? 2 : 4;
		}
		paint();

	}

	//method that repaints the whole game 
	public void paint() {
		setBackground(new Background(new BackgroundFill(Color.rgb(187, 173, 160), null, null)));
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 5, 5, 5));

		Score = new Label("Score: " + score);
		Score.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));

		add(Score, 0, 0, n - 1, 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				add(new CustomCell(values[i][j], size / (11 * n - 1) * 10), j, i + 1);
			}
		}
	}

	//method that allows the user to perform an UP or DOWN operation on  the array
	void moveValuesUD(boolean b) { // if b is true: moveUp , false: moveDown
		youLost();
		for (int j = 0; j < n; j++) {
			int[] col = new int[n];
			int d = 0;
			for (int i = 0; i < n; i++)
				if (values[i][j] != 0)
					col[d++] = values[i][j];

			int i = (b) ? 0 : n - 1;

			for (int c = 0; c < d; c++)
				if (b) {
					values[i][j] = col[c];
					i++;
				} else {
					values[i][j] = col[d - c - 1];
					i--;
				}
			if (b)
				while (i < n)
					values[i++][j] = 0;
			else
				while (i >= 0)
					values[i--][j] = 0;

		}
		paint();
		if (b)
			mergeUp(); 
		else
			mergeDown();

		addValue();
		requestFocus();

	}

	//method that allows the user to perform a LEFT or RIGHT operation on  the array
	void moveValuesLR(boolean b) { // if b is true: moveLeft , false: moveRight
		youLost();
		for (int i = 0; i < n; i++) {
			int[] col = new int[n];
			int d = 0;
			for (int j = 0; j < n; j++)
				if (values[i][j] != 0)
					col[d++] = values[i][j];

			int j = (b) ? 0 : n - 1;

			for (int c = 0; c < d; c++)
				if (b) {
					values[i][j] = col[c];
					j++;
				} else {
					values[i][j] = col[d - c - 1];
					j--;
				}
			if (b)
				while (j < n)
					values[i][j++] = 0;
			else
				while (j >= 0)
					values[i][j--] = 0;

		}
		paint();
		if (b)
			mergeLeft();
		else
			mergeRight();
		addValue();

		requestFocus();

	}

	//adds up the cells
	public void mergeUp() {
		for (int j = 0; j < n; j++) {
			int[] tR = new int[n];
			int d = 0;
			for (int i = 0; i < n - 1; i++) {
				if (values[i][j] == values[i + 1][j]) {
					tR[d++] = values[i][j] * 2;
					score += values[i][j] * 2;
					if (values[i][j] * 2 == 2048)
						youWon();
					i++;
				} else {

					tR[d++] = values[i][j];
					if (i == n - 2)
						tR[d++] = values[i + 1][j];

				}
			}

			for (int i = 0; i < n; i++)
				values[i][j] = tR[i];

		}
		paint();
	}

	//adds down the cells
	public void mergeDown() {
		for (int j = 0; j < n; j++) {
			int[] tR = new int[n];
			int d = 0;
			for (int i = n - 1; i > 0; i--) {

				if (values[i][j] == values[i - 1][j]) {
					tR[d++] = values[i][j] * 2;
					score += values[i][j] * 2;
					if (values[i][j] * 2 == 2048)
						youWon();
					i--;
				} else {
					tR[d++] = values[i][j];
					if (i == 1)
						tR[d++] = values[i - 1][j];
				}
			}

			for (int i = 0; i < n; i++)
				values[i][j] = tR[n - i - 1];

		}
		paint();
	}
	
	//adds left the cells
	public void mergeLeft() {
		for (int i = 0; i < n; i++) {
			int[] tR = new int[n];
			int d = 0;
			for (int j = 0; j < n - 1; j++) {
				if (values[i][j] == values[i][j + 1]) {
					tR[d++] = values[i][j] * 2;
					score += values[i][j] * 2;
					if (values[i][j] * 2 == 2048)
						youWon();
					j++;
				} else {
					tR[d++] = values[i][j];
					if (j == n - 2)
						tR[d++] = values[i][j + 1];
				}
			}

			for (int j = 0; j < n; j++)
				values[i][j] = tR[j];

		}
		paint();
	}

	//adds right the cells
	public void mergeRight() {

		for (int i = 0; i < n; i++) {
			int[] tR = new int[n];
			int d = 0;
			for (int j = n - 1; j > 0; j--) {

				if (values[i][j] == values[i][j - 1]) {
					tR[d++] = values[i][j] * 2;
					score += values[i][j] * 2;
					if (values[i][j] * 2 == 2048)
						youWon();
					j--;
				} else {
					tR[d++] = values[i][j];
					if (j == 1)
						tR[d++] = values[i][j - 1];
				}
			}

			for (int j = 0; j < n; j++)
				values[i][j] = tR[n - j - 1];

		}
		paint();
	}

	//test whether the user has lost
	public boolean canMove() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++)
				if (values[i][j] == values[i][j + 1])

					return true;
			for (int j = n - 1; j > 0; j--)
				if (values[i][j] == values[i][j - 1])
					return true;
		}
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n - 1; i++)
				if (values[i][j] == values[i + 1][j])
					return true;
			for (int i = n - 1; i > 0; i--)
				if (values[i][j] == values[i - 1][j])
					return true;
		}
		return false;

	}

	public void youLost() {
		if (!canMove()) {

			TextPane tp = new TextPane("You Lost !\n Score: " + score, "Play Again", e -> {
				stage.setScene(new GameScene(stage).getScene());
			});
			StackPane bp = new StackPane();
			bp.getChildren().add(tp);
			getScene().setRoot(bp);

		}

	}

	public void youWon() {

		TextPane tp = new TextPane("You Won !\n Score: " + score, "Play Again", e -> {
			stage.setScene(new GameScene(stage).getScene());
		});
		StackPane bp = new StackPane();
		bp.getChildren().add(tp);
		getScene().setRoot(bp);

	}

}
