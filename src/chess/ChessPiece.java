package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece { //pe�as de xadrez
	
	private Color color;
	private int moveCount;
	

	public ChessPiece(Board board, Color color) {
		super(board); //repassa a chamada para o construtor da super classe que � o construtor da classe Piece
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	//m�todo para incrementar movimentos
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		
		return ChessPosition.fromPosition(position);
	}
	
	//implementa��o para verificar se existe uma pe�a advers�ria
	protected boolean isThereOpponentPiece(Position position) {
		
		ChessPiece p = (ChessPiece) getBoard().piece(position); //pegando a pe�a nesta posi��o
		return p != null && p.getColor() != color; //para concluir que a pe�a p � uma pe�a advers�rio, tem que testar se � diferente de nulo p.getCOlor � diferente da cor da minha pe�a		
	}
}
