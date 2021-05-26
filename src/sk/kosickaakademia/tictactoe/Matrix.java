package sk.kosickaakademia.tictactoe;

import java.util.Scanner;

public class Matrix {
    private final byte[][] matrix;
    private final Referee referee = Referee.getInstance();
    private byte playerTurn;
    //private Logger logger = Logger.getGlobal();

    public Matrix(int height, int width) {
        matrix = new byte[height][width];
        playerTurn = 1;
    }

    //print matrix
    public void printMatrix() {
        System.out.print("    ");
        for (var i = 0; i < matrix.length; i++) {
            if (i < 10) System.out.print(" " + i + " ");
            else System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("    " + "---".repeat(matrix[0].length));
        for (var i = 0; i < matrix.length; i++) {
            if (i < 10) System.out.print(" " + i + "  ");
            else System.out.print(i + "  ");

            for (var j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1)
                    System.out.print(" \u001B[1;35m" + matrix[i][j] + "\u001B[0m ");
                if(matrix[i][j] == 2)
                    System.out.print(" \033[1;32m" + matrix[i][j] + "\u001B[0m ");
                if(matrix[i][j] == 0)
                    System.out.print(" " + matrix[i][j] + " ");

            }
            System.out.println();
        }
    }

    //one turn
    public void askForCell() {
        var scanner = new Scanner(System.in);

        System.out.println("Turn of Player: " + playerTurn);

        int row, column;
        do {
            System.out.print("Row: ");
            row = scanner.hasNextInt() ? scanner.nextInt() : -1 ;
            System.out.print("Column: ");
            column = scanner.hasNextInt() ? scanner.nextInt() : -1 ;
        }while (row < matrix.length && row >= 0 && column < matrix[0].length && column >= 0 && matrix[row][column] != 0);

        matrix[row][column] = playerTurn;

        playerTurn = (byte) (playerTurn == 1 ? 2 : 1);
    }

    public byte[][] getMatrix() {
        return matrix;
    }
}
