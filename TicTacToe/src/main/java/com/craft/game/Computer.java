package com.craft.game;

public class Computer implements Player {

    private final Board board = Board.getInstance();

    @Override
    public String getName() {
        return "Computer";
    }

    @Override
    public char getSymbol() {
        return 'X';
    }

    @Override
    public void makeMove() {
        int move = getBestMove(board);
        board.makeMove(move, getSymbol());
        System.out.println("Player "+getName()+" filled the position "+(move+1));
    }

    /**
     * execute rules and find out the best position for the computer player to place its symbol
     * @param board board array with current game state
     * @return position where the computer should place its symbol
     */
    private int getBestMove(Board board) {
        // Rule 1: Check if the computer can win in the next move
        for (int move : board.availableMoves()) {
            board.makeMove(move, getSymbol());
            if (board.checkWinner(getSymbol())) {
                board.revertMoveForComputer(move);
                return move;
            }
            board.revertMoveForComputer(move);
        }

        // Rule 2: Check if the opponent can win in their next move, and block them
        char opponent = getSymbol()=='X'? 'O':'X';
        for (int move : board.availableMoves()) {
            board.makeMove(move, opponent);
            if (board.checkWinner(opponent)) {
                board.revertMoveForComputer(move);
                return move;
            }
            board.revertMoveForComputer(move);
        }

        // Rule 3: Take the center if available
        if (board.makeMove(4, '-')) return 4;

        // Rule 4: Take any available corner
        int[] corners = {0, 2, 6, 8};
        for (int corner : corners) {
            if (board.makeMove(corner, '-')) return corner;
        }

        // Rule 5: Take any remaining available move
        return board.availableMoves().get(0);
    }

}
