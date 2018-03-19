package com.akatri.tictactoe.entities;

public class GameBoard {
	static final int ROWS = 3;
	static final int COLUMNS = 3;
	private int currentRow;
	private int currentCol;

	private static Cell[][] board;

	GameBoard() {
		board = new Cell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = new Cell(i, j);
			}
		}

	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	void initBoard() {

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j].setCellType(CellType.EMPTY);
			}
		}

	}

	void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j].printCell();
				if (j < COLUMNS - 1) {
					System.out.print("|");
				}

			}
			System.out.println();
			if (i < ROWS - 1) {
				System.out.println("-----------");
			}
		}
	}

	public Cell[][] getCell() {
		return board;
	}

	boolean isDraw() {
		boolean isFilled = true;
		outer: for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j].getCellType().equals(CellType.EMPTY)) {
					isFilled = false;
					break outer;
				}

			}

		}
		return isFilled;
	}

	boolean hasWon(String player) {
		String cellContent = (player == Player.CROSS_PLAYER) ? CellType.CROSS : CellType.NOUGHT;
		return (isValidRow(cellContent) || isValidColumn(cellContent) || isValidDiagonals(cellContent));

	}

	private boolean isValidDiagonals(String cellContent) {
		return (((currentCol == currentRow) && (board[0][0].getCellType() == cellContent)
				&& (board[1][1].getCellType() == cellContent) && (board[2][2].getCellType() == cellContent))
				| ((currentCol+ currentRow==2) && (board[0][2].getCellType() == cellContent)
						&& (board[1][1].getCellType() == cellContent) && (board[2][0].getCellType() == cellContent)));
	}

	private boolean isValidRow(String cellContent) {
		return (board[currentRow][0].getCellType() == cellContent && board[currentRow][1].getCellType() == cellContent
				&& board[currentRow][2].getCellType() == cellContent);

	}

	private boolean isValidColumn(String cellContent) {
		return (board[0][currentCol].getCellType() == cellContent && board[1][currentCol].getCellType() == cellContent
				&& board[2][currentCol].getCellType() == cellContent);
	}

	String getCellContent(int row, int col) {

		return board[row][col].getCellType();

	}

	void setCellContent(String player) {
		if (player == Player.CROSS_PLAYER) {
			board[currentRow][currentCol].setCellType(CellType.CROSS);
		} else {
			board[currentRow][currentCol].setCellType(CellType.NOUGHT);
		}

	}

}
