package sk.kosickaakademia.lambda.sorting;

public class Person {
    private final String fName;
    private final String lName;
    private final char gender;
    private final int age;

    private static final char[] samohlasky = {'a','e','i','o','u'};

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
    
    public int getHashCode(){
        var hashCode = 0;

        for (int i = lName.length()-1; i >= 0; i--) {
            for (char temp :
                    samohlasky) {
                if (lName.charAt(i) == temp && hashCode == 0) {
                    hashCode = 1;
                    break;
                }
                if (lName.charAt(i) == temp) {
                    hashCode = (hashCode << 1) | 1;
                }
            }
        }

        return hashCode;
    }
}
