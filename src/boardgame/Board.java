package boardgame;

public class Board { //Classe Tabuleiro
	
	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pe�as (todo tabuleiro tem 1 ou mais pe�as)
	
	public Board(int rows, int columns) {
		
		if (rows < 1 || columns < 1) { //verifica��o para ver se o tabuleiro tem pelo menos 1 linha ou 1 coluna
			
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //minha matriz de pe�as � instanciada com Pieces na quantidade de linhas e colunas informadas
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	
	//m�todo para retornar uma pe�a dada a uma linha / coluna.
	public Piece piece(int row, int column) {
		
		if(!positionExists(row, column)) {
			
			throw new BoardException("Position not on the board.");
		}
		return pieces[row][column]; //retorna a matriz pieces na linha [row] e na coluna [column]
	}
	
	//m�todo para retornar a pe�a pela posi��o
	public Piece piece(Position position) {
		
		if(!positionExists(position)) {
			
			throw new BoardException("Position not on the board.");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//m�todo que recebe uma pe�a e uma posi��o e � respons�vel por colocar uma pe�a nessa posi��o do tabuleiro.
	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position)) { //teste para ver se ja existe 1 pe�a nesta posi��o
			
			throw new BoardException("There is already a piece on position " + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece; //vai na matriz de pe�as do tabuleiro na linha (position.getRow) e na coluna (position.getColumn) e vai atribuir a essa posi��o da minha matriz de pe�as a pe�a que veio como argumento. Pegamos a matriz na posi��o dada e atribuimos a ela a pe�a que eu informei.
		piece.position = position; //dizendo que a pe�a nao esta mais nula e sim na posi��o (position)
	}
	
	private boolean positionExists(int row, int column) {
		
		return row >= 0 && row < rows && column >= 0 && column< columns; //condi��o completa para ver se uma posi��o existe		
	}	
	
	public boolean positionExists(Position position) { //teste para ver se uma posi��o existe
		
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) { //teste para ver se tem uma pe�a nesta posi��o
		
			if(!positionExists(position)) {
			
			throw new BoardException("Position not on the board.");
		}
		return piece(position) != null;
	}
}
