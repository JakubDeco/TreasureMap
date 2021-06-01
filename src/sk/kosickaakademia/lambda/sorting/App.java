package sk.kosickaakademia.lambda.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Adam","Kolar",'m',38));
        people.add(new Person("Petra","Huskova",'f',67));
        people.add(new Person("Tomas","Kolar",'m',15));
        people.add(new Person("Tatiana","Huskova",'f',41));
        people.add(new Person("Gabriel","Bolad",'m',29));
        people.add(new Person("Ruzena","Juhasz",'f',87));
        people.add(new Person("David","Weis",'m',26));
        people.add(new Person("Martina","Aternis",'f',17));
        people.add(new Person("Karol","Janosik",'m',47));
        people.add(new Person("Petra","Halasova",'f',9));

        // vypis foreach A. Filan
        System.out.println(" ----- vypis foreach A. Filan");
        people.forEach((person -> {
            System.out.println(person.getfName().charAt(0) +". "+ person.getlName());
        }));

        // vypis podla gender
        System.out.println(" ----- vypis podla gender");
        people.forEach(person -> {
            if (person.getGender() == 'f')
                person.print();
        });

        // filter by gender
        List<Person> females = people.stream().filter(person -> person.getGender() == 'f').collect(Collectors.toList());
        females.forEach(Person::print);

        // sort by age
        System.out.println(" ----- sort by age");
        people.sort(Comparator.comparingInt(Person::getAge));
        //people.sort((a,b) -> a.getAge() - b.getAge());
        people.forEach(Person::print);

        // sort by fName
        System.out.println(" ----- sort by fName");
        people.sort(Comparator.comparing(Person::getfName));
        //people.sort((a,b) -> a.getfName().compareTo(b.getfName()));
        people.forEach(Person::print);

        // sort by lName then fName
        System.out.println(" ----- sort by lName then fName");
        /*people.sort((a,b) -> {
            var compareResult = a.getlName().compareTo(b.getlName());
            return compareResult == 0
                    ? a.getfName().compareTo(b.getfName())
                    : compareResult;
        });*/
        people.sort(Comparator.comparing(Person::getlName).thenComparing(Person::getfName)); // comparator chain
        people.forEach(Person::print);

        // printing people with their hashCodes
        people.forEach(person -> {
            person.print();
            System.out.println(person.getHashCode());
        });
    }
}
