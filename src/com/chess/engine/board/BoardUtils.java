package com.chess.engine.board;

//Made it public by moving it here. It is useful for all of the pieces
public class BoardUtils {

    //Creates the different columns. They will be an array of 64 bits, with 8 active ones for each one of them
    //They are used to compute the exceptions
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private static boolean[] initColumn(int columnNumber){
        final boolean[] tiles = new boolean[NUM_TILES];
        int tileNumber;
        tileNumber = columnNumber;
        //Defines a starting point for the loop to start
        //Switches to 1s the tiles of that column
        do{
            tiles[tileNumber] = true;
            tileNumber += NUM_TILES_PER_ROW;
        }while(tileNumber <NUM_TILES);

        return tiles;
    }

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate me!");
    }

    //Inbounds or outbounds of the chessboard
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >=0 & coordinate <64;
    }
}
