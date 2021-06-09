package sk.kosickaakademia.cypher;

public class App {
    public static String cypher(String str, int mask){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.append((char) (str.charAt(i) ^ mask));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(cypher("CyCe`ubTufu|_`ub",16));
    }
}
