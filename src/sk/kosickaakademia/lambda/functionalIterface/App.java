package sk.kosickaakademia.lambda.functionalIterface;

public class App {
    public static void main(String[] args) {
        Calc calc = (a,b) -> a*b;
        int c = calc.operation(7,8);
        System.out.println(c);
        calc = Math::max;
        c = calc.operation(7,8);
        System.out.println(c);


        print(6,5,(a,b) -> a*b); // lambda passed as argument
        printEurWithTax(29.42,(a) -> a*1.22);
    }

    private static void print(int a, int b, Calc calc){
        System.out.println(calc.operation(a,b));
    }

    private static void printEurWithTax(double value, Tax tax){
        System.out.println((double)Math.round(tax.calcTax(value)*100)/100 + "€");
    }
}
