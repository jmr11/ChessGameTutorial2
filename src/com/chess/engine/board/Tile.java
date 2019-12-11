package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static com.chess.engine.board.BoardUtils.NUM_TILES;

public abstract class Tile {

    //Once this is set inside of the constructor (coordinate)
    //it cannot be modified. Only at construction time.
    protected final int tileCoordinate; //Tile number

    //Creation of every empty Tile in advance, before putting the pieces
    //To access the empty tiles emptyTile.get(0)
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0;i<BoardUtils.NUM_TILES;i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        //Guava immutable map
        return ImmutableMap.copyOf(emptyTileMap);
    }

    //Only method to create a Tile in this class
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    /*
    When you construct a new instance of Tile
    it will assign a Tile Coordinate
    */
    private Tile(int tileCoordinate){ //Private constructor
        this.tileCoordinate = tileCoordinate;
    }

    /*
    Methods
    */
    //Whether or not a given Tile is Occupied
    public abstract boolean isTileOccupied();
    //Get the Piece off of a given Tile
    public abstract Piece getPiece();

    //Subclasses that will represent an empty or occupied Tile
    public static final class EmptyTile extends Tile {

        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        //To achieve polymorphism. Allows the child class
        //to modify the implementation of the same method
        @Override
        public boolean isTileOccupied() {
            return false; //When Tile is not occupied
        }

        @Override
        public Piece getPiece() {
            return null; //When there is not piece to return
        }
    }
    //When there is a piece to find on the Tile, definition
    public static final class OccupiedTile extends Tile{

        //It can't be mutated (class private).
        //Can't be referenced outside of getPiece()
        private final Piece pieceOnTile;

        //The constructor for an occupied Tile takes the
        // coordinates and the Piece
        OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile; //Piece passed by the constructor
        }

        //For these overrides we return
        //Occupied=true and the Piece on the Tile
        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }

}
