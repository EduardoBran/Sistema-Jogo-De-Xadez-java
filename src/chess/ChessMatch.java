package chess;

import boardgame.Board;
import chess.pieces.Rei;
import chess.pieces.Torre;
//partida de xadrez . Cora��o do projeto. Onde v�o ter as regras do sistema
public class ChessMatch { 
	
	private Board board; //partida de xadrez TEM que ter 1 tabuleiro
	
	
	public ChessMatch() { //quando � criado a partida, o construtor cria o tabuleiro e chama o setup
		 
		board = new Board(8 , 8); //dimens�o do tabuleiro de xadrez
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){ //Retornar uma matriz de pe�as de xadrez correspondente a essa partida.
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i = 0 ; i < board.getRows() ; i++) { //percorrer a matriz de pe�as do tabuleiro (board) e pra cada pe�a do meu tabuleiro, fa�o um downcasting para ChessPiece
			
			for(int j = 0 ; j < board.getColumns() ; j++) {
				
				mat[i][j] = (ChessPiece) board.piece(i,j); //(ChessPiece) downcasting para ChessPiece
			}
		}
		return mat; //retorna a matriz de pe�a da minha partida de xadrez
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
	
	//m�todo respons�vel por INICIAR a partida de xadrez colocando as pe�as no tabuleiro
	private void initialSetup() {
		
		placeNewPiece('c', 1, new Torre(board, Color.WHITE));
        placeNewPiece('c', 2, new Torre(board, Color.WHITE));
        placeNewPiece('d', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Torre(board, Color.WHITE));
        placeNewPiece('d', 1, new Rei(board, Color.WHITE));

        placeNewPiece('c', 7, new Torre(board, Color.BLACK));
        placeNewPiece('c', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 8, new Rei(board, Color.BLACK));
		
	}
}
