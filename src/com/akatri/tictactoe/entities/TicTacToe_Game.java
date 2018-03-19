package com.akatri.tictactoe.entities;

import java.util.Scanner;

public class TicTacToe_Game {
	private static String player;
	private static GameBoard board;
	private static String gameState;

	public static void main(String... args) {
		launchGame();
	}

	private static void launchGame() {
		board = new GameBoard();

		do {
			loadGameBoard();

			do {
				playerMove(player);
				updateGameBoard();
				updateGameState();
				player = (player == Player.CROSS_PLAYER) ? Player.NOUGHT_PLAYER : Player.CROSS_PLAYER;

			} while (gameState == GameState.PLAYING);
			System.out.println("Want to play again ? :Y/N ");
			@SuppressWarnings("resource")
			String result = new Scanner(System.in).next();
			if (result.equalsIgnoreCase("N")) {
				System.exit(0);
			}
		} while (true);
	}

	private static void updateGameState() {
		if (board.hasWon(player)) {

			if (player == Player.CROSS_PLAYER) {
				gameState = GameState.CROSS_WON;
				printWinnerMessage(Player.CROSS_PLAYER);
			} else {
				gameState = GameState.NOUGHT_WON;
				printWinnerMessage(Player.NOUGHT_PLAYER);
			}
		} else if (board.isDraw()) {
			printDrawMessage();
			gameState = GameState.DRAW;
		}

	}

	private static void printDrawMessage() {
		System.out.println("\nGAME STATUS : DRAW!! \n Please Try Again..");

	}

	private static void printWinnerMessage(String player) {

		System.out.println("\nCONGRATS!! " + player + " ,You Won!");

	}

	private static void updateGameBoard() {
		board.printBoard();

	}

	private static void playerMove(String player) {
		boolean isValid = false;
		do {
			if (player == Player.CROSS_PLAYER) {
				System.out.println("\nPLayer_X turn,please enter row & column :");
			} else {
				System.out.println("\nPLayer_O turn,please enter row & column :");
			}
			@SuppressWarnings("resource")
			int row = new Scanner(System.in).nextInt() - 1;
			@SuppressWarnings("resource")
			int col = new Scanner(System.in).nextInt() - 1;
			if (validInput(row, col)) {
				board.setCurrentRow(row);
				board.setCurrentCol(col);
				board.setCellContent(player);
				isValid = true;

			} else {
				System.out.println("\nPlease Enter Valid Input");
			}

		} while (!isValid);

	}

	private static boolean validInput(int row, int col) {
		if (row >= 0 && row < GameBoard.ROWS && col >= 0 && col < GameBoard.COLUMNS
				&& board.getCellContent(row, col) == CellType.EMPTY) {
			return true;
		}
		return false;
	}

	private static void loadGameBoard() {
		board.initBoard();
		player = getPlayerDecision();
		gameState = GameState.PLAYING;
		updateGameBoard();

	}

	private static String getPlayerDecision() {

		return Math.random() < 0.5 ? Player.CROSS_PLAYER : Player.NOUGHT_PLAYER;

	}
}
