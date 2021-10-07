package application;

import chess.ChessPiece;

public class UI { //UserInterface
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i = 0 ; i < pieces.length ; i++) {
			
			System.out.print((8 - i) + " ");
			
			for (int j = 0 ; j < pieces.length ; j++) {
				
				printPiece(pieces[i][j]);				
			}
			System.out.println();
		}		
		System.out.println("  a b c d e f g h");
	}
	
	//metodo auxiliar para imprimir uma pe�a
	private static void printPiece(ChessPiece piece) { //imprimi 1 pe�a
		
		if (piece == null) { //se a pe�a for igual a nulo, nao tem pe�a no tabuleiro
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
