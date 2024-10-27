package tictact;

import java.util.*;
import java.io.*;
import java.util.random.*;

public class App {
    private static final Random random = new Random();

    //Can choose X O 2-player AI 

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("1 for 2player or 2 for vs AI");
        
        Console cons = System.console();
        int mode = getValidInput(cons,1,2);

        System.out.println("Choose X or O");

        Pos playerSymbol = (cons.readLine().equalsIgnoreCase("X")) ? Pos.X : Pos.O;
        Pos aiSymbol = (playerSymbol == Pos.X) ? Pos.O : Pos.X;

        System.out.println("Who should start first? (1 for You, 2 for AI):");
        int startChoice = getValidInput(cons, 1, 2);
        boolean playerStarts = startChoice == 1;//if player it will be true
        boolean crossTurn = playerStarts;
        if (playerSymbol == Pos.O) {
            crossTurn = !crossTurn;
        }


        Board board = new Board();
        board.setCrossTurn(crossTurn);

        boolean playAI = (mode == 2);

        while (!board.isGameOver() && board.anyMovesAvailable()) {
            System.out.println(board);

            if (board.isCrossTurn() == (playerSymbol == Pos.X)) {
                playerMove(cons, board, playerSymbol);
            } else if (playAI) {
                aiMove(board, aiSymbol);
            } else {
                playerMove(cons, board, aiSymbol); // For two-player mode
            }
        }
        System.out.println(board);
        if (board.getWinningChar() != Pos.BLANK) {
            System.out.println(board.getWinningChar() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }

    }
    private static void playerMove(Console cons, Board board, Pos symbol) {
        System.out.print("Enter a number between 0 and 2 for row: \n");
        int row = getValidInput(cons, 0, 2);
        System.out.print("Enter a number between 0 and 2 for column: \n");
        int col = getValidInput(cons, 0, 2);

        while (!board.placeChar(row, col)) {
            System.out.println("Invalid move. Try again:");
            row = getValidInput(cons, 0, 2);
            col = getValidInput(cons, 0, 2);
        }
    }
    private static void aiMove(Board board, Pos aiSymbol) {

        System.out.println("AI's turn as " + aiSymbol);
        int[] bestMove = Minimax.getBestMove(board, aiSymbol, aiSymbol == Pos.X ? Pos.O : Pos.X);
        board.placeChar(bestMove[0], bestMove[1]);
        System.out.println("AI placed at (" + bestMove[0] + ", " + bestMove[1] + ")");

        //Random AI
        // System.out.println("AI's turn as " + aiSymbol);
        // int row, col;
        // do {
        //     row = random.nextInt(3);
        //     col = random.nextInt(3);
        // } while (!board.placeChar(row, col));  //do-while loop
        // System.out.println("AI placed at (" + row + ", " + col + ")");
    }

    private static int getValidInput(Console cons, int min, int max) {
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(cons.readLine("Enter a number between " + min + " and " + max + ": "));
                if (input >= min && input <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return input;
    }



    //         System.out.println("Welcome to Tic Tac Toe");
    //         System.out.println("You will play against the AI. You always start first.");

    //         Console cons = System.console();
    //         Pos playerSymbol = Pos.X;
    //         Pos aiSymbol =  Pos.O;

    //         Board board = new Board();
    //         board.setCrossTurn(true); // Player always starts first

    //         while (!board.isGameOver() && board.anyMovesAvailable()) {
    //             System.out.println(board);

    //             // Player's turn
    //             playerMove(cons, board, playerSymbol);
    //             if (board.isGameOver() || !board.anyMovesAvailable()) break;

    //             // AI's turn
    //             aiMove(board, aiSymbol);
    //         }

    //         System.out.println(board);
    //         if (board.getWinningChar() != Pos.BLANK) {
    //             System.out.println(board.getWinningChar() + " wins!");
    //         } else {
    //             System.out.println("It's a tie!");
    //         }
    //     }

    //     private static void playerMove(Console cons, Board board, Pos symbol) {
    //         //can use 
    //         // int row = -1, col = -1;
    //         // while (true) {
    //         //     String input = cons.readLine();
    //         //     String[] parts = input.split(" "); // Split the input by space
                
    //         //     if (parts.length == 2) {
    //         //         try {
    //         //             row = Integer.parseInt(parts[0]); // First part is row
    //         //             col = Integer.parseInt(parts[1]); // Second part is column
                        
    //         //             if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
    //         //                 if (board.placeChar(row, col)) {
    //         //                     break; // Valid move, exit the loop

    //         //for index
    //         // int position = -1;
    //         // while (true) {
    //         //     String input = cons.readLine();
                
    //         //     try {
    //         //         position = Integer.parseInt(input); // Read the input as a single integer
                    
    //         //         if (position >= 0 && position <= 8) {
    //         //             int row = position / 3; // Calculate row from position
    //         //             int col = position % 3; // Calculate column from position
                        
    //         //             if (board.placeChar(row, col)) {
    //         //                 break; // Valid move, exit the loop

    //         System.out.println(symbol + "'s turn. Enter row and column (0, 1, or 2):");
    //         int row = getValidInput(cons, 0, 2);
    //         int col = getValidInput(cons, 0, 2);

    //         while (!board.placeChar(row, col)) {
    //             System.out.println("Invalid move. Try again:");
    //             row = getValidInput(cons, 0, 2);
    //             col = getValidInput(cons, 0, 2);
    //         }
    //     }

    //     private static void aiMove(Board board, Pos aiSymbol) {
    //         System.out.println("AI's turn as " + aiSymbol);
    //         int row, col;
    //         do {
    //             row = random.nextInt(3);
    //             col = random.nextInt(3);
    //         } while (!board.placeChar(row, col));
    //         System.out.println("AI placed at (" + row + ", " + col + ")");
    //     }

    //     private static int getValidInput(Console cons, int min, int max) {
    //         int input = -1;
    //         while (true) {
    //             try {
    //                 input = Integer.parseInt(cons.readLine("Enter a number between " + min + " and " + max + ": "));
    //                 if (input >= min && input <= max) {
    //                     break;
    //                 }
    //             } catch (NumberFormatException e) {
    //                 System.out.println("Invalid input. Please enter a valid number.");
    //             }
    //         }
    //         return input;
    //     }
    // }

    
}
