package chess;

import boardgame.Board;
//partida de xadrez . Cora��o do projeto. Onde v�o ter as regras do sistema
public class ChessMatch { 
	
	private Board board; //partida de xadrez TEM que ter 1 tabuleiro
	
	
	public ChessMatch() {
		 
		board = new Board(8 , 8); //dimens�o do tabuleiro de xadrez
	}
	
	public ChessPiece[][] getPieces(){ //Retornar um matriz de pe�as de xadrez correspondente a essa partida.
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i = 0 ; i < board.getRows() ; i++) { //percorrer a matriz de pe�as do tabuleiro (board) e pra cada pe�a do meu tabuleiro, fa�o um downcasting para ChessPiece
			
			for(int j = 0 ; j < board.getColumns() ; j++) {
				
				mat[i][j] = (ChessPiece) board.piece(i,j); //(ChessPiece) downcasting para ChessPiece
			}
		}
		return mat; //retorna a matriz de pe�a da minha partida de xadrez
	}	
}
