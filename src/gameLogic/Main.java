package gameLogic;

import java.util.Scanner;

import javafx.application.Platform;

public class Main {
	public static void main(String[] args) {
		boolean playing = true, checkLegal = true, checkMove = true, playing2 = true;
		int rows = -1, columns = -1, mines = -1, rows2 = -1, columns2 = -1;
		String yesNo = "";
		Scanner sc = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		while (playing) {
			System.out.println("Welcome to MineSweeper!\nPlease enter the board's size");
			while (checkLegal) {
				System.out.println("Rows:");
				rows = sc.nextInt();
				System.out.println("Columns:");
				columns = sc.nextInt();
				System.out.println("Mines:");
				mines = sc.nextInt();
				if (rows < 2 || columns < 2 || mines < 0)
					System.out.println(
							"Re-enter rows, columns and mines number as rows and columns must be larger than 2 (each)!");
				else
					checkLegal = false;
			}
			checkLegal = true; // In-case user chooses to play again
			Mines m = new Mines(rows, columns, mines);
			System.out.println("The board is ready\n" + m);
			while (playing2) {
				while (checkMove) {
					System.out.println("\nMake your move\nRow coordinate (number between 1 and " + rows + "):");
					rows2 = sc.nextInt();
					System.out.println("Column coordinate (number between 1 and " + columns + "):");
					columns2 = sc.nextInt();
					rows2 -= 1; // To make them correct (between 0 and rows-1)
					columns2 -= 1; // To make them correct (between 0 and columns-1)
					if (rows2 < 0 || columns2 < 0)
						System.out.println("Re-enter row and column numbers as they must be between 1 and " + rows
								+ " for rows and between 1 and " + columns + " for the columns!");
					else
						checkMove = false;
				}
				checkMove = true; // To make moves again
				m.open(rows2, columns2);
				System.out.println("\n\n" + m);
				if (m.isDone())
					playing2 = false;
				else {
					System.out.println(
							"Would you like to put a flag or QM on the slot?\nEnter \"Flag\" (or \"F\"), \"QM\" (or \"Q\") or \"No\" (or \"N\"):");
					yesNo = scString.nextLine();
					if (yesNo.equals("Flag") || yesNo.equals("F") || yesNo.equals("flag") || yesNo.equals("f")) {
						System.out.println("Row coordinate for flag:");
						rows2 = sc.nextInt();
						System.out.println("Column coordinate for flag:");
						columns2 = sc.nextInt();
						rows2 -= 1;
						columns2 -= 1;
						m.toggleFlag(rows2, columns2);
						System.out.println("\n" + m);
					}
					if (yesNo.equals("QM") || yesNo.equals("Q") || yesNo.equals("q") || yesNo.equals("qm")) {
						System.out.println("Row coordinate for QM:");
						rows2 = sc.nextInt();
						System.out.println("Column coordinate for QM:");
						columns2 = sc.nextInt();
						rows2 -= 1;
						columns2 -= 1;
						m.toggleQM(rows2, columns2);
						System.out.println("\n" + m);
					}
					if (yesNo.equals("No") || yesNo.equals("N") || yesNo.equals("no") || yesNo.equals("n"))
						System.out.println("\n\n" + m);
				}
			}
			System.out.println("Would you like to play again?\nEnter \"Yes\" (or \"Y\") or \"No\" (or \"N\"):");
			yesNo = scString.nextLine();
			if (yesNo.equals("No") || yesNo.equals("N") || yesNo.equals("no") || yesNo.equals("n"))
				playing = false;
			playing2 = true; // Play again
		}
		System.out.println("Hope you enjoyed this, boy!\n\n");
		scString.close();
		sc.close();
		Platform.exit();
	}
}