package sk.kosickaakademia.treasureMap;


import java.io.Serializable;
import java.util.Scanner;

public class Map implements Serializable {
    private int width;
    private int height;
    private boolean found = false;


    private int[][] map;

    public Map(int width,int height,int[] mapCode) throws InvalidInputException {
        if (validInput(width,height,mapCode)){
            this.width = width;
            this.height = height;
            map = new int[width][height];

            decodeMap(decimalToBinaryString(mapCode));
            printMap();
            placeTreasureAndBoat();
            printMap();
            if (findRoute()){
                System.out.println("Route to treasure will take " + routeLength() + "days.");
            }
        } else {
            throw new InvalidInputException("invalid input");
        }
    }

    private int routeLength() {
        int result = 0;
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                if (map[i][j] == -2){
                    //look N
                    i--;
                    if(indexInBound(i,j)){
                        result = Math.max(map[i][j], result);
                    }
                    i++;

                    //look S
                    i++;
                    if(indexInBound(i,j)){
                        result = Math.max(map[i][j], result);
                    }
                    i--;

                    //look W
                    j--;
                    if(indexInBound(i,j)){
                        result = Math.max(map[i][j], result);
                    }
                    j++;

                    //look W
                    j++;
                    if(indexInBound(i,j)){
                        result = Math.max(map[i][j], result);
                    }
                    j--;
                }
            }
        }
        return result;
    }

    private void placeTreasureAndBoat(){
        Scanner scanner = new Scanner(System.in);
        int tR, tC, bR, bC;

        do {
            System.out.println("Treasure location");
            System.out.print(" - row:");
            tR = scanner.nextInt();
            System.out.print(" - column:");
            tC = scanner.nextInt();

            if (tR >= 0 && tR <= height && tC >= 0 && tC <= width) {
                if (map[tR][tC] == 0){
                    map[tR][tC] = -2;
                    break;
                }else
                    System.out.println("---treasure can only be placed at sea");
            }
            System.out.println("--- invalid input");
        } while (scanner.hasNextInt());
        //
        do {
            System.out.println("Boat location");
            System.out.print(" - row:");
            bR = scanner.nextInt();
            System.out.print(" - column:");
            bC = scanner.nextInt();

            if (bR >= 0 && bR <= height && bC >= 0 && bC <= width) {
                if (map[bR][bC] == 0){
                    map[bR][bC] = -1;
                    break;
                }else
                    System.out.println("---treasure can only be placed at sea");
            }
            System.out.println("--- invalid input");
        } while (scanner.hasNextInt());
        //


    }

    private int[][] decodeMap(String[] mapCode){
        if (mapCode == null || mapCode.length == 0)
            return null;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(mapCode[i].charAt(j)));
            }

        }
        return map;
    }

    private String[] decimalToBinaryString(int[] mapCodeDecimal){
        if (mapCodeDecimal == null || mapCodeDecimal.length == 0)
            return null;

        String[] result = new String[mapCodeDecimal.length];
        for (int i = 0; i < result.length; i++) {
            String row = Integer.toBinaryString(mapCodeDecimal[i]);
            result[i] = row.length() == width ? row : "0".repeat(width - row.length()) + row;
            //System.out.println(result[i]);
        }

        return result;
    }

    public void printMap(){
        System.out.println("------------------------------");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 0){
                    System.out.print(" \u001B[36m" + map[i][j] + "\u001B[0m ");
                } else if (map[i][j] == 1){
                    System.out.print(" \033[1;32mG\u001B[0m ");
                } else if (map[i][j] == -1){
                    System.out.print(" \u001B[31mB\u001B[0m ");
                }else if (map[i][j] == -2){
                    System.out.print(" \u001B[1;35mT\u001B[0m ");
                }else if (map[i][j] < 10){
                    System.out.print(" " + map[i][j] + " ");
                }else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // finding route

    private boolean validInput(int width, int height, int[] mapCode){
        if (width >= 2
                && height >= 2
                && mapCode.length == height){
            for (Integer rowCode :
                    mapCode) {
                if (rowCode < 0 || rowCode > Math.pow(2,width)-1){
                    System.out.println(rowCode + " ");
                    System.out.println(Math.pow(2,width)-1);
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public boolean contains(int tile){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == tile) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean indexInBound(int row, int column){
        return row>=0 && row<height && column>=0 && column<width;
    }

    public boolean findRoute(){

        int currentTile = -1;
        int replacement = 2;

        while(!found && contains(currentTile)){
            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {

                    if (map[i][j] == currentTile){
                        //look N
                        i--;
                        if(indexInBound(i,j)){
                            if(map[i][j] == 0){
                                map[i][j] = replacement;
                            }else if (map[i][j] == -2){
                                found = true;
                                return true;
                            }
                        }
                        i++;

                        //look S
                        i++;
                        if(indexInBound(i,j)){
                            if(map[i][j] == 0){
                                map[i][j] = replacement;
                            }else if (map[i][j] == -2){
                                found = true;
                                return true;
                            }
                        }
                        i--;

                        //look W
                        j--;
                        if(indexInBound(i,j)){
                            if(map[i][j] == 0){
                                map[i][j] = replacement;
                            }else if (map[i][j] == -2){
                                found = true;
                                return true;
                            }
                        }
                        j++;

                        //look W
                        j++;
                        if(indexInBound(i,j)){
                            if (map[i][j] == 0) {
                                map[i][j] = replacement;
                            }else if (map[i][j] == -2){
                                found = true;
                                return true;
                            }
                        }
                        j--;
                    }
                }
            }

            currentTile = currentTile == -1 ? 2 : currentTile+1;
            replacement++;
            printMap();
        }

        return false;
    }
    //
}
