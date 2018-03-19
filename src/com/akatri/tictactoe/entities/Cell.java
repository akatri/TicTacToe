package com.akatri.tictactoe.entities;

public class Cell {
	private String cellType;
	private int row;
	private int col;

	Cell(int row, int col) {
		this.row = row;
		this.col = col;
		setCellType(CellType.EMPTY);

	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

	public String getCellType() {
		return cellType;
	}

	public void printCell() {

		switch (cellType) {
		case CellType.CROSS:
			System.out.print(" X ");
			break;
		case CellType.NOUGHT:
			System.out.print(" O ");
			break;
		case CellType.EMPTY:
			System.out.print("   ");
			break;
		}
	}

}
