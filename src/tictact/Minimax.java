package tictact;

import static tictact.Pos.*;

public class Minimax {

    private static final int WIN_SCORE = 1;
    private static final int LOSS_SCORE = -1;
    private static final int DRAW_SCORE = 0;


    private static int evaluate(Board board, Pos aiSymbol){
        Pos winner = board.calculateWinner();
        if (winner == aiSymbol){
            return WIN_SCORE;
        }
        else if (winner != Pos.BLANK){
            return LOSS_SCORE;
        }
        return DRAW_SCORE;
    }

    public static int minimax(Board board,boolean isMaximizing,Pos aiSymbol,Pos playerSymbol){

        Pos winner = board.calculateWinner();
        if (winner != Pos.BLANK) {
            return evaluate(board, aiSymbol);
        } else if (board.isBoardFull()) {
            return DRAW_SCORE;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setCharAt(row, col, aiSymbol); // Make the move
                        int score = minimax(board, false, aiSymbol, playerSymbol);
                        board.setCharAt(row, col, Pos.BLANK); // Undo move
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setCharAt(row, col, playerSymbol); // Make the move
                        int score = minimax(board ,true, aiSymbol, playerSymbol);
                        board.setCharAt(row, col, Pos.BLANK); // Undo move
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;

    }
    public static int[] getBestMove(Board board, Pos aiSymbol, Pos playerSymbol) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
    
        for (int row = 0; row < board.getWidth(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (!board.isTileMarked(row, col)) {
                    board.setCharAt(row, col, aiSymbol);
                    int score = minimax(board, false, aiSymbol, playerSymbol);
                    board.setCharAt(row, col, Pos.BLANK);
    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row, col};
                    }
                }
            }
        }
        return bestMove;
    }


}
