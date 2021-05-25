package sk.kosickaakademia.tictactoe;

public class Referee {
    private static Referee instance;

    private Referee(){}

    private byte findWinner(byte[][] matrix){
        if (matrix == null || !isValidMatrix(matrix)) return 0;

        byte winner;

        // check rows
        winner = checkRows(matrix);
        if (winner != 0) return winner;

        // check columns
        winner = checkColumns(matrix);
        if (winner != 0) return winner;

        // check descDiagonal
        winner = checkDescDiagonal(matrix);
        if (winner != 0) return winner;

        // finally check ascDiagonal
        return checkAscDiagonal(matrix);
    }

    private byte checkDescDiagonal(byte[][] matrix) {
        byte product;

        for (int i = 0; i < matrix.length - 4; i++) {

            for (int j = 0; j < matrix[0].length - 4; j++) {

                if (matrix[i][j] != 0){
                    product = (byte) (matrix[i][j] * matrix[i+1][j+1]
                            * matrix[i+2][j+2] * matrix[i+3][j+3] * matrix[i+4][j+4]);
                    if (product == 1 || product == 32){
                        return (byte) (product == 1 ?  1 : 2);
                    }
                }

            }

        }

        return 0;
    }

    private byte checkAscDiagonal(byte[][] matrix) {
        byte product;

        for (int i = 0; i < matrix.length - 4; i++) {

            for (int j = 4; j < matrix[0].length; j++) {

                if (matrix[i][j] != 0){
                    product = (byte) (matrix[i][j] * matrix[i+1][j-1]
                            * matrix[i+2][j-2] * matrix[i+3][j-3] * matrix[i+4][j-4]);
                    if (product == 1 || product == 32){
                        return (byte) (product == 1 ?  1 : 2);
                    }
                }

            }

        }

        return 0;
    }

    private byte checkColumns(byte[][] matrix) {
        byte product;

        for (int j = 0; j < matrix[0].length; j++) {

            for (int i = 0; i < matrix.length - 4; i++) {

                if (matrix[i][j] != 0){
                    product = (byte) (matrix[i][j] * matrix[i+1][j] * matrix[i+2][j] * matrix[i+3][j] * matrix[i+4][j]);
                    if (product == 1 || product == 32){
                        return (byte) (product == 1 ?  1 : 2);
                    }
                }

            }

        }

        return 0;
    }

    private byte checkRows(byte[][] matrix) {
        byte product;
        for (byte[] row : matrix) {

            for (int j = 0; j < row.length - 4; j++) {

                if (row[j] != 0){
                    product = (byte) (row[j] * row[j+1] * row[j+2] * row[j+3] * row[j+4]);

                    if (product == 1 || product == 32){
                        return (byte) (product == 1 ?  1 : 2);
                    }
                }

            }

        }

        return 0;
    }

    private boolean isValidMatrix(byte[][] matrix) {
        // check min size 10x10
        if (matrix.length < 10 || matrix[0].length < 10) return false;

        // check for uneven row lengths
        for (byte[] row : matrix) {
            if (row.length != matrix[0].length) return false;
        }

        // check whether cell contain only [0,1,2]
        for (byte[] row : matrix) {
            for (byte cell : row) {
                if (cell < 0 || cell > 2) return false;
            }
        }

        return true;
    }

    public static Referee getInstance(){
        if(instance == null){
            instance = new Referee();
        }

        return instance;
    }

    public static void main(String[] args) {
        Referee referee = Referee.getInstance();
        byte[][] matrix = new byte[][]{
                {0, 1, 1, 1, 0, 1, 1, 0, 1, 1,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,2,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 2, 2, 2,0,0,0,0,0},
                {0, 1, 1, 1, 0, 2, 2, 0, 2, 2,0,0,0,0,0}
        };
        //System.out.println(referee.checkRows(matrix));
        //System.out.println(referee.checkColumns(matrix));
        //System.out.println(referee.checkDescDiagonal(matrix));
        //System.out.println(referee.checkAscDiagonal(matrix));

        System.out.println(referee.findWinner(matrix));
    }
}
