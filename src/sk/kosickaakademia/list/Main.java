package sk.kosickaakademia.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(45);
        list.add(9);
        list.add(15);
        list.add(3);
        list.add(-38);
        list.add(22);
        list.add(4);
        list.add(789);

        // 1 sposob - for
        /*for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();*/
        // 2. sposob
        /*System.out.println(list);*/
        // 3. sposob - enhanced for
        /*for (Integer item :
                list) {
            System.out.print(item + " ");
        }
        System.out.println();*/
        // 4. sposob - iterator
        /*Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();*/
        // 5. sposob - lambda
        /*list.forEach((item) -> {
            System.out.print(item + " ");
        });
        System.out.println();*/
        // 6. sposob
        list.forEach(Main::write);

        // vypis a mazanie neparnych cisel
        /*while (list.ite){
            System.out.print(item + " ");
            if ((item&1) == 1) list.remove(item);
        });
        System.out.println();*/
        // vypis usporiadaneho pola
        /*list.sort(null);
        list.forEach(item -> {
            System.out.print(item + " ");
        });
        System.out.println();*/

        // vypis usporiadaneho pola podla ciferneho suctu asc
        /*list.sort((o1, o2) -> {
            int dso1 = digitSum(o1);
            int dso2 = digitSum(o2);
            //return dso1 < dso2 ? -1 : dso1 == dso2 ? 0 : 1;
            //return dso1 - dso2;
            return Integer.compare(dso1, dso2);
        });
        list.forEach(item -> {
            System.out.print(item + " ");
        });


        System.out.println();
        list.forEach(Main::writePow2);*/

        List<String> stringList = new ArrayList<>();
        stringList.add("aaaaaaaaaaaaaa");
        stringList.add("aaaaaaaaaaa");
        stringList.add("aaa");
        stringList.add("a");
        stringList.add("aaaaa");
        stringList.add("aaaa");

        stringList.sort(Main::compareStringLength);
        stringList.forEach(str -> {
            System.out.println(str + " ");
        });
    }

    public static int compareStringLength(String a, String b){
        return a.length() - b.length();
    }

    public static int digitSum(int value){
        int sum = 0;
        while(value != 0){
            sum += value%10;
            value /= 10;
        }

        return sum;
    }

    private static void write(int item){
        System.out.print(item + " ");
    }
    private static void writePow2(int item){
        System.out.print(Math.pow(item,2) + " ");
    }
}
