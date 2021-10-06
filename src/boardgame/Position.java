package boardgame;

public class Position {
	
	private int row;    //linha
	private int column; //coluna
	
	
	public Position(int row, int column) {
		
		this.row = row;
		this.column = column;
	} 

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		
		return row + ", " + column;
	}
	
	
	

}


//Classe da camada de tabuleiro.
//� a classe que representa uma posi��o no tabuleiro.
//Ele vai ter linha e coluna e vai ter um m�todo setValue que recebe
//uma nova linha e nova coluna para ser atribuido para a posi��o.