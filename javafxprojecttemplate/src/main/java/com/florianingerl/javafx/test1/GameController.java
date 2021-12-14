package com.florianingerl.javafx.test1;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameController {

	@FXML
	private GridPane buttonContainer;

	private Board board;
	private Button[][] buttons;

	@FXML
	private Label lblRemainingMoves;

	int size = 10;
	int width = 7; // 7 Spalten
	int height = 9; // 9 Reihen
	
	public GameController() {
		
	}

	@FXML
	public void initialize() {

		board = new Board(124411235, width, height);
		buttons = new Button[height][width];

		lblRemainingMoves.setText("");
	

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				buttons[i /* Reihe */][j /* Spalte */] = new Button();
				buttons[i][j].setPrefSize(50, 50);

				buttons[i][j].setOnMousePressed(new ButtonHandler(i, j));

				buttonContainer.setRowIndex(buttons[i][j], i);
				buttonContainer.setColumnIndex(buttons[i][j], j);
				buttonContainer.getChildren().add(buttons[i][j]);

			}

		}

		updateGui();

	}

	private void updateGui() {

		String s = "Remaining moves: " + board.getRemainingMoves();
		lblRemainingMoves.setText(s);

		for (int r = 0; r < height; ++r) {
			for (int c = 0; c < width; ++c) {
				if (board.getStringAt(r, c) == "X") {
					// buttons[r][c].setImage();
				}

				buttons[r][c].setText(board.getStringAt(r, c));
			}
		}

	}

	private class ButtonHandler implements EventHandler<MouseEvent> {

		private int i;
		private int j;

		ButtonHandler(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void handle(MouseEvent event) {

			if (event.isPrimaryButtonDown()) {
				board.playerGuessedCell(i, j);
				buttons[i][j].setDisable(true);

				updateGui();

				if (board.isGameLost()) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Game Over");
					alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("That's it Buddy..");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
					Platform.exit();
				} else if (board.isGameWon()) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Game Won");
					alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("That's it Buddy..");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
					Platform.exit();
				}

			}
			if (event.isSecondaryButtonDown()) {
				board.playerWantsToMarkOrUnmarkCell(i, j);
				updateGui();

			}

		}
	}

}