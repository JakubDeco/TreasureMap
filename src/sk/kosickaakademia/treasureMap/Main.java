package sk.kosickaakademia.treasureMap;

public class Main {
    public static void main(String[] args) {
        try {
            int[] rowCodes = new int[]{3001,212,84,1172,0,4095,780,500,0,707,516,700};
            Map map = new Map(12,12,rowCodes);
        }
        catch (InvalidInputException e){
            e.printStackTrace();
        }
    }
}
