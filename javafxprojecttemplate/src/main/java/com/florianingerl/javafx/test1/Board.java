package com.florianingerl.javafx.test1;

import java.util.Random;

public class Board {
    private int width;
    private int height;

    private String[][] board;
    private int remainingMoves = 15;

    private int[] positionSpaceShips = new int[4];
    private int ship = 0;

    public Board(long seed, int width, int height) {
        this.width = width;
        this.height = height;
        this.remainingMoves = remainingMoves;

        board = new String[height /*Reihen*/][width /*Spalten*/];

        for (int row = 0; row < height; ++row) {
            for (int column = 0; column < width; ++column) {
                board[row][column] = "";
            }

        }

        Random rnd = new Random(seed);

        for (int i = 0; i < 4; i++) {
            int z = rnd.nextInt(width * height);
            positionSpaceShips[i] = z;
            System.out.println(z);
        }

    }

    public void playerWantsToMarkOrUnmarkCell(int row, int column) {
        if (board[row][column].equals("m") ) {
            board[row][column] = "";
        } else {
            board[row][column] = "m";
        }

    }

    public void playerGuessedCell(int row, int column) {
        if (isShipAt(row, column)) {
            board[row][column] = "X";
            ship++;
            remainingMoves++;
        } else {
            remainingMoves--;
            int ships = 0;
            for (int z = 1; column + z < width; ++z) {
                if (isShipAt(row, column + z)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; column - z >= 0; ++z) {
                if (isShipAt(row, column - z)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row + z < height; ++z) {
                if (isShipAt(row + z, column)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row - z >= 0; ++z) {
                if (isShipAt(row - z, column)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row + z < width && column + z < height; ++z) {
                if (isShipAt(row + z, column + z)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row - z >= 0 && column - z >= 0; ++z) {
                if (isShipAt(row - z, column - z)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row - z >= 0 && column + z < height; ++z) {
                if (isShipAt(row - z, column + z)) {
                    ships++;
                    break;
                }
            }
            for (int z = 1; row + z < width && column - z >= 0; ++z) {
                if (isShipAt(row + z, column - z)) {
                    ships++;
                    break;
                }
            }
            board[row][column] = "" + ships;

            if (ships == 0) {
                markAllCellsFrom(row, column);
            }

        }
    }

    private void markAllCellsFrom(int row, int column) {
        for (int z = 1; column + z < width; ++z) {
            board[row][column + z] = "m";
            //System.out.println(board[row][column +z]);
        }
        for (int z = 1; column - z >= 0; ++z) {
            board[row][column - z] = "m";
            //System.out.println(board[row][column - z]);

        }
        for (int z = 1; row + z < height; ++z) {
            board[row + z][column] = "m";
            System.out.println(board[row + z][column]);

        }
        for (int z = 1; row - z >= 0; ++z) {
            board[row - z][column] = "m";
        }
        for (int z = 1; row + z < height && column + z < width; ++z) {
            board[row + z][column + z] = "m";
        }
        for (int z = 1; row - z >= 0 && column - z >= 0; ++z) {
            board[row - z][column - z] = "m";
        }
        for (int z = 1; row - z >= 0 && column + z < width; ++z) {
            board[row - z][column + z] = "m";

        }
        for (int z = 1; row + z < height && column - z >= 0; ++z) {
            board[row + z][column - z] = "m";
        }
    }

    private boolean isShipAt(int row, int column) {
        for (int i = 0; i < 4; i++) {
            if (getRow(positionSpaceShips[i]) == row && getColumn(positionSpaceShips[i]) == column) {
                return true;
            }
        }
        return false;
    }

    public int getRow(int pos) {
        return pos / this.width;

    }


    public int getColumn(int pos) {
        return pos % this.width;
    }

    public String getStringAt(int row, int column) {
        return board[row][column];
    }

    public boolean isGameLost() {

        if (remainingMoves <= 0) {
            System.out.println("That's it buddy, Game Over.");
            return true;
        }
        return false;
    }

    public boolean isGameWon() {

        if (ship >= 4) {
            System.out.println("You are now a god.");
            return true;
        }
        return false;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }

}
