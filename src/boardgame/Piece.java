package boardgame;

public class Piece { //Classe pe�a
	
	protected Position position; //protected pq esse tipo de posi��o nao � posi��o do xadrez. Ela � uma posi��o simples de matiz. Eu n�o quero que essa posi��o seja visivel na camada de xadrez.
	private Board board;
	
	public Piece(Board board) {
		
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	
	
}
