package com.florianingerl.javafx.test1;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
    private Board board;
    private Button[][] buttons;
    private Label lblRemainingMoves;

    int size = 10;
    int width = 7; //7 Spalten
    int height = 9; //9 Reihen


    @Override
    public void start(Stage stage) throws IOException {

        //    New one = new New ();

        board = new Board(124411235, width, height);
        buttons = new Button[height][width];

        GridPane root = new GridPane();
        root.setPadding(new Insets(60, 10, 20, 10));


        //AnchorPane bottomRow = new AnchorPane();

        lblRemainingMoves = new Label();

        //AnchorPane.setBottomAnchor(lblRemainingMoves, 0.0);
        lblRemainingMoves.setText("You've got more moves.");
        //bottomRow.getChildren().add(lblRemainingMoves);
        //lblRemainingMoves.setLayoutX(400);
        //lblRemainingMoves.setLayoutY(10);
        // lblRemainingMoves.setPrefSize(100,100);
        //lblRemainingMoves.setAlignment(Pos.BOTTOM_LEFT);


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                buttons[i /*Reihe*/][j /*Spalte*/] = new Button();
                buttons[i][j].setPrefSize(50, 50);

                buttons[i][j].setOnMousePressed(new ButtonHandler(i, j));

                root.setRowIndex(buttons[i][j], i);
                root.setColumnIndex(buttons[i][j], j);
                root.getChildren().add(buttons[i][j]);


            }

        }

        updateGui();

        root.setConstraints(lblRemainingMoves, height, 0);
        root.getChildren().add(lblRemainingMoves);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Them Ships!");
        stage.setScene(scene);

        stage.show();
    }

    private void updateGui() {

		String s = "Remaining moves: " + board.getRemainingMoves();
        lblRemainingMoves.setText(s);

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
        }



        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                if( board.getStringAt(r,c) == "X"){
                    //buttons[r][c].setImage();
                }

                buttons[r][c].setText(board.getStringAt(r, c));
            }
        }


        if( board.isGameWon() ){
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

   /* private void winnerMethod() {
        Stage winnerStage = new Stage();
        if (board.winner(board.ship)) {
            Group root2 = new Group();
            Scene scn = new Scene(root2);

            winnerStage.setScene(scn);
            winnerStage.show();

        }
    }

    private void looserMethod() {
        Stage looserStage = new Stage();
        if (board.looser(board.remainingMoves)) {
            Group root3 = new Group();
            Scene scn2 = new Scene(root3);

            looserStage.setScene(scn2);
            looserStage.show();

        }
    }*/

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

            }
            if (event.isSecondaryButtonDown()) {
                board.playerWantsToMarkOrUnmarkCell(i, j);
                updateGui();

            }

        }
    }


    public static void main(String[] args) {
        launch();
    }
}