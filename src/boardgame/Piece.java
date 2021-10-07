package boardgame;

public class Piece { //Classe pe�a
	
	protected Position position; //protected pq esse tipo de posi��o nao � posi��o do xadrez. Ela � uma posi��o simples de matiz. Eu n�o quero que essa posi��o seja visivel na camada de xadrez.
	private Board board;
	
	public Piece(Board board) {
		
		this.board = board;
		position = null;   //posicao de uma pe�a rec�m criada vai ser inicialmente nula pois n�o foi colocada ainda.
	}

	protected Board getBoard() { //somente classes dentro do mesmo pacote e sublclasses podem acessar o tabuleiro de uma pe�a.
		return board;
		
	}
}
