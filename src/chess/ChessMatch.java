package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rei;
import chess.pieces.Torre;
//partida de xadrez . Cora��o do projeto. Onde v�o ter as regras do sistema
public class ChessMatch { 
	
	private int turn;
	private Color currentPlayer;
	private Board board; //partida de xadrez TEM que ter 1 tabuleiro
	private boolean check; //come�a como false
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	
	public ChessMatch() { //quando � criado a partida, o construtor cria o tabuleiro e chama o setup
		 
		board = new Board(8 , 8); //dimens�o do tabuleiro de xadrez
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	//imprimir as posi��es poss�veis atrav�s de uma posi��o de origem
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}	
		
	//m�todo para retirar a pe�a da posi��o de origem e coloca-la na posi��o de destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { //source origem , target destino
		
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source); //m�todo para validar uma posi��o
		validadeTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target); 
		
		if (testCheck(currentPlayer)) {
			
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check.");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if (testCheckMate(opponent(currentPlayer))) {
			
			checkMate = true;
		}
		else {
			
			nextTurn(); //trocar o turno
		}
		
		return (ChessPiece)capturedPiece; //downcasting pq a pe�a capturada era do tipo Piece
	}
	
	private Piece makeMove(Position source, Position target) {
		
		ChessPiece p = (ChessPiece)board.removePiece(source); //retirou a pe�ada posi��o de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//m�todo para desfazer o movimento (evitar que o jogador se coloque em xeque)
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		
		ChessPiece p =  (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	 
	private void validateSourcePosition(Position position) {
		
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { //exce��o para caso o jogardo esteja tentando mover uma pe�a advers�ria
			throw new ChessException("The chosen piece is not yours.");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the choesen piece");
		}
				
	}
	
	private void validadeTargetPosition(Position source, Position target) {
		
		if(!board.piece(source).possibleMove(target)) { //se para pe�a de origem a posi��o de destino nao � um movimento possivel, entao nao posso mexer para la
			
			throw new ChessException("The chosen piece can't move to target position.");
		}	
	}
	
	//m�todo para alternar oos jogadores
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; //tern�rio, se o jogador atual for igual a Color.WHITE ent�o agora ele vai ser o Color.BLACK , caso contr�rio ele vai ser o Color.WHITE 
	}
	
	private Color opponent (Color color) {
		
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//m�todo para varrer as pe�as do jogo localizando o rei daquela cor
	private ChessPiece king(Color color) {
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		for (Piece p : list) {
			
			if (p instanceof Rei) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board.");
	}
	
	//m�todo para varrer todas as pe�as advers�rias e testar se alguma delas cai na posi��o do Rei
	private boolean testCheck(Color color) {
		
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				
				return true;
			}
		}
		return false;
	}
	
	
	//m�todo para o checkMate
	private boolean testCheckMate(Color color) {
		
		if(!testCheck(color)) {
			
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); 
		
		for (Piece p : list) {
			
			boolean[][] mat = p.possibleMoves();
				
			for(int i = 0 ; i < board.getRows() ; i++) {
				for(int j = 0; j < board.getColumns() ; j++) {
					
					if (mat[i][j]) {
						
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i , j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						
						if (!testCheck) {
							return false;
						}
					}
				}
			}			
		}		
		return true;
	}
	
			
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece); 
		
	}
	
	//m�todo respons�vel por INICIAR a partida de xadrez colocando as pe�as no tabuleiro
	private void initialSetup() {
		
		placeNewPiece('h', 7, new Torre(board, Color.WHITE));
        placeNewPiece('d', 1, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Rei(board, Color.WHITE));

        placeNewPiece('b', 8, new Torre(board, Color.BLACK));        
        placeNewPiece('a', 8, new Rei(board, Color.BLACK));
		
	}
}
