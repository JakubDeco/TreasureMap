package sk.kosickaakademia.lambda.sorting;

public class Person {
    private String fName;
    private String lName;
    private char gender;
    private int age;

    public Person(String fName, String lName, char gender, int age) {
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.age = age;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void print() {
        System.out.println(fName + " " + lName + " " + gender + " " + age);
    }
}
