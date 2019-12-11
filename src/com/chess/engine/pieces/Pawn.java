package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {8,16};

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES) {

            final int candidateDestinationCoordinate = this.piecePosition + (getPieceAlliance().getDirection() * currentCandidateOffset

            //If its not a valid Tile
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            //If the tile is not occupied and the Candidate movement is 8, add a legal MOVE
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //TODO more work to do here! Like Pawn Promotion
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate))

            //For initial positions
            } else if (currentCandidateOffset == 16 && this.isFirstMove &&
                      (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                      (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {

                final int inTheWayCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                //If the piece in between the pawn and its destination 2 squares further are occupied
                if(!board.getTile(inTheWayCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }

            }

            }
        }


        return legalMoves;
    }
}
