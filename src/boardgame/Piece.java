package boardgame;

public abstract class Piece { //Classe pe�a
	
	protected Position position; //protected pq esse tipo de posi��o nao � posi��o do xadrez. Ela � uma posi��o simples de matiz. Eu n�o quero que essa posi��o seja visivel na camada de xadrez.
	private Board board; //toda pe�a tem 1 tabuleiro
	
	
	public Piece(Board board) {
		
		this.board = board;
		position = null;   //posicao de uma pe�a rec�m criada vai ser inicialmente nula pois n�o foi colocada ainda.
	}

	protected Board getBoard() { //somente classes dentro do mesmo pacote e sublclasses podem acessar o tabuleiro de uma pe�a.
		return board;		
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//verificando se existe pelo meno 1 movimento poss�vel para essa pe�a
	public boolean isThereAnyPossibleMove() {
		
		boolean[][] mat = possibleMoves();
		
		for(int i = 0 ; i < mat.length ; i++) {
			for(int j = 0 ; j < mat.length ; j++) {
				
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
