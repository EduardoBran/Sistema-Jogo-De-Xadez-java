package application;

import chess.ChessMatch;


public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		
		//funcao para imprimir pe�as da partida
		UI.printBoard(chessMatch.getPieces());
	}

}


