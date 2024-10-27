package tictact;

import java.util.*;

import task9.game;

import java.io.*;
import static tictact.Pos.*; // static is important

public class Board {

    private final Pos[][] board;
    private Pos winningChar;
    private final int boardWidth = 3;
    private boolean crossTurn, gameOver;
    private int avaliableMoves = boardWidth * boardWidth;

    public Board(){
        board = new Pos[boardWidth][boardWidth];
        crossTurn = true; //cross starts first
        gameOver = false;
        winningChar = BLANK;
        initialiseBoard();
    }

    private void initialiseBoard(){
        for (int row = 0; row < boardWidth;row++){
            for(int col = 0; col < boardWidth; col++){
                board[row][col] = BLANK;
            }
        }
    }

    public boolean placeChar(int row, int col){
        if(row < 0 || row >= boardWidth || col < 0 || col >= boardWidth || isTileMarked(row, col) || gameOver){
            return false;
        }
        avaliableMoves--;
        board[row][col] = crossTurn ? X : O; //? is if else
        togglePlayer();
        checkWin(row,col);
        return true;

    }

    private void checkWin(int row,int col){
        int rowSum = 0;
        //check row
        for(int c = 0;c<boardWidth;c++){
            rowSum += getCharAt(row,c).getChar();
        }
        if(calcWinner(rowSum)!= BLANK){
            System.out.println(winningChar + " wins on row " + row);
            return;
        } 
        rowSum = 0;
        //check col
        for(int r = 0; r<boardWidth; r++){
            rowSum += getCharAt(r,col).getChar();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningChar + " wins on column " + col);
            return;
        }
        //check diagonals
        rowSum = 0;
        //check col
        for(int i = 0; i<boardWidth; i++){
            rowSum += getCharAt(i,i).getChar();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningChar + " wins on the top-left to "
                    + "bottom-right diagonal");
            return;
        }
        rowSum = 0;
        int indexMax = boardWidth - 1;
        //check col
        for(int j = 0; j<boardWidth; j++){
            rowSum += getCharAt(j,indexMax - j).getChar();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningChar + " wins on the top-right to "
                    + "bottom-left diagonal");
            return;
        }
        if (!anyMovesAvailable()) {
            gameOver = true;
            System.out.println("Tie!");
        }



    }

    private Pos calcWinner(int rowSum){
        int Xwin = X.getChar() * boardWidth;
        int Owin = O.getChar() * boardWidth;
        if (rowSum == Xwin) {
            gameOver = true;
            winningChar = X;
            return X;
        } else if (rowSum == Owin) {
            gameOver = true;
            winningChar = O;
            return O;
        }
        return BLANK;
    }
    private void togglePlayer() {
        crossTurn = !crossTurn;
    }
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean anyMovesAvailable() {
        return avaliableMoves > 0;
    }
    public Pos getCharAt(int row, int column) {
        return board[row][column];
    }
    public boolean isTileMarked(int row, int column) {
        return board[row][column].checkBlank();
    }
    public void setCharAt(int row, int column, Pos newChar) {
        board[row][column] = newChar;
    }
    //@Override
    // public String toString() {
    //     StringBuilder strBldr = new StringBuilder();
    //     for (Pos[] row : board) {
    //         for (Pos tile : row) {
    //             strBldr.append(tile).append(' ');
    //         }
    //         strBldr.append("\n");
    //     }
    //     return strBldr.toString();
    // }
    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                strBldr.append(board[row][col]);
                // Add a | separator between columns except the last column
                if (col < board[row].length - 1) {
                    strBldr.append(" | ");
                }
            }
            strBldr.append("\n");
            
            // Add a row separator between rows except the last row
            if (row < board.length - 1) {
                strBldr.append("- + - + - \n");
            }
        }
        
        return strBldr.toString();
    }
    


    public boolean isCrossTurn(){
        return crossTurn;
    }
    public int getWidth(){
        return boardWidth;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    public Pos getWinningChar(){
        return winningChar;
    }
    public void setCrossTurn(boolean isPlayerStarting) {
        crossTurn = isPlayerStarting;
    }

    public Pos calculateWinner() {
        // Check rows
        for (int row = 0; row < boardWidth; row++) {
            if (board[row][0] != Pos.BLANK && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return board[row][0];
            }
        }
        // Check columns
        for (int col = 0; col < boardWidth; col++) {
            if (board[0][col] != Pos.BLANK && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return board[0][col];
            }
        }
        // Check diagonals
        if (board[0][0] != Pos.BLANK && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != Pos.BLANK && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        // No winner
        return Pos.BLANK;
    }
    public boolean isBoardFull() {
        for (int row = 0; row < boardWidth; row++) {
            for (int col = 0; col < boardWidth; col++) {
                if (board[row][col] == Pos.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
