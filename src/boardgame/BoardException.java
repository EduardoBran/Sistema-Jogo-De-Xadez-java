package boardgame;

public class BoardException extends RuntimeException { //Runtime para que seja uma exce��o opcional de ser tratada

	private static final long serialVersionUID = 1L; 
	
	public BoardException(String msg) {
		
		super(msg);
	}

}
