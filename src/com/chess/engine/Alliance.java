package com.chess.engine;

public enum Alliance {
    WHITE {
        @Override
        int getDirection() {
            return -1;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public boolean isWhite() {
            return true;
        }
    },
    BLACK {
        @Override
        int getDirection() {
            return 1;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public boolean isWhite() {
            return false;
        }
    }

    //To differentiate from Black and White Pawns
    //Since they move in the opposite direction
    public abstract int getDirection();
    public abstract boolean isBlack();
    public abstract boolean isWhite();


}
