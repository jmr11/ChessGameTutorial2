package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

//Look up Collection vs List
import java.util.Collection;
public abstract class Piece {

    //Every piece has a piece position
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;



    //Constructor
    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO more work here
        this.isFirstMove = false;
    }

    //Getter Method for Piece Alliance (color)
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    //Method for calculating legal moves
    //Returns a collection of legal moves
    //All Pieces are going to Override this Method and have its different behaviour
    //It's unspecified whoever calls this method
    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
