package boardgame;

public class Board { //Classe Tabuleiro
	
	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pe�as (todo tabuleiro tem 1 ou mais pe�as)
	
	public Board(int rows, int columns) {
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //minha matriz de pe�as � instanciada com Pieces na quantidade de linhas e colunas informadas
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//m�todo para retornar uma pe�a dada a uma linha / coluna.
	public Piece piece(int row, int column) {
		
		return pieces[row][column]; //retorna a matriz pieces na linha [row] e na coluna [column]
	}
	
	//m�todo para retornar a pe�a pela posi��o
	public Piece piece(Position position) {
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//m�todo que recebe uma pe�a e uma posi��o e � respons�vel por colocar uma pe�a nessa posi��o do tabuleiro.
	public void placePiece(Piece piece, Position position) {
		
		pieces[position.getRow()][position.getColumn()] = piece; //vai na matriz de pe�as do tabuleiro na linha (position.getRow) e na coluna (position.getColumn) e vai atribuir a essa posi��o da minha matriz de pe�as a pe�a que veio como argumento. Pegamos a matriz na posi��o dada e atribuimos a ela a pe�a que eu informei.
		piece.position = position; //dizendo que a pe�a nao esta mais nula e sim na posi��o (position)
	}
}
