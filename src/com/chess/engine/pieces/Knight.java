package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Knight extends Piece{

    //Possible Moves, before taking anything else into account (board size, occupied by own piece...)
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};

    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    //Collection is the root interface
    public Collection<Move> calculateLegalMoves(Board board) {


        //List is a specialized collection with each element having a position
        final List<Move> legalMoves = new ArrayList<>();

        //We go through each candidate legal move and apply the move to see if it is valid
        for(final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){ //To be continued...
                
                //If the Original piece is in the first column, we can start the computation by excluding some invalid moves
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue; //Next for iteration
                }
                
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                //If there is no piece on the tile
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                //If there is a piece of a different color
                } else{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    //If the Alliance of the piece we want to move is not the same as the one we want to move to
                    //Enemy piece -> It is a valid movement
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    //Exceptions from the X column. If the offset is one of these listed and the Knight is in the X column,
    // we can omit the Color computation of the destination's piece. These exceptions happen in columns {1,2,7,8}
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17)|| (candidateOffset == -10)|| (candidateOffset == 6)|| (candidateOffset == 15));
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10)|| (candidateOffset == 6));
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == 10)|| (candidateOffset == -6));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 17)|| (candidateOffset == 10)|| (candidateOffset == -6)|| (candidateOffset == -15));
    }
}
