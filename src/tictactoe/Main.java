package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        board.gameLogic(scanner);
    }

}
