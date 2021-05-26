package sk.kosickaakademia.tictactoe;


public class Main {
    public static void main(String[] args) {
        var matrix = new Matrix(15,15);
        var referee = Referee.getInstance();
        do {
            matrix.printMatrix();
            matrix.askForCell();
        }while (referee.findWinner(matrix.getMatrix()) == 0);
        matrix.printMatrix();
    }
}
