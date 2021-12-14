package com.florianingerl.javafx.test1;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameApp extends Application {

	
	@FXML private GridPane buttonContainer;

	private Board board;
	private Button[][] buttons;

	
	@FXML private Label lblRemainingMoves;

	int size = 10;
	int width = 7; // 7 Spalten
	int height = 9; // 9 Reihen

	/**
	 *
	 */
	@Override
	public void start(Stage stage) throws IOException {

		// New one = new New ();

		BorderPane root = FXMLLoader.load(getClass().getResource("gui.fxml"));
		

		
		Scene scene = new Scene(root, 600, 500);
		stage.setTitle("Raketen versenken - das beste Spiel der Welt!");
		stage.setScene(scene);

		stage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}

	
}