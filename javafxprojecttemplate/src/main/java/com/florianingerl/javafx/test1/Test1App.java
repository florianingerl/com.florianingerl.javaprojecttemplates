package com.florianingerl.javafx.test1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test1App  extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Test1App");
		
		VBox vBox = new VBox();
		vBox.setSpacing(5);
		
		Button btn = new Button("Exit");
		btn.setOnAction(e-> Platform.exit() );
		
		vBox.getChildren().add(btn);
		
		primaryStage.setScene(new Scene(vBox, 300, 300) );
		
		primaryStage.show();
	}

}
