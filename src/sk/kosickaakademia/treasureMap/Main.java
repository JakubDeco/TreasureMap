package sk.kosickaakademia.treasureMap;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            int[] rowCodes = new int[]{3001,212,84,1172,0,4095,780,500,0,707,516,700};
            Map map = new Map(12,12,rowCodes);
            serialize("src/resources/serializedMap.tm",map);
        }
        catch (InvalidInputException e){
            e.printStackTrace();
        }
        Map mapDeser = deserialize("src/resources/serializedMap.tm");
        mapDeser.printMap();
    }

    public static boolean serialize(String fileName, Map map){
        File serializedMap = new File(fileName);

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedMap));

            oos.writeObject(map);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Map deserialize(String fileName){
        File file = new File(fileName);
        Map map = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            map = (Map) ois.readObject();

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return map;
        }

        return map;
    }
}
