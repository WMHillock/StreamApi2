import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> teenStream = persons.stream();
        long count = teenStream.filter(w -> w.getAge() < 18).count();

        Stream<Person> recruitsStream = persons.stream();
        List<String> surnames = recruitsStream
                .filter(w -> w.getAge() >= 18 && w.getAge() <= 27 && w.getSex() == Sex.MAN)
                .map(Person::getFamily).collect(Collectors.toList());

        Stream<Person> qualifiedEmployeesStream = persons.stream();
        List<Person> workers = qualifiedEmployeesStream
                .filter(w -> w.getAge() >= 18 && w.getAge() <= (w.getSex() != Sex.MAN ? 60 : 65))
                .filter(w -> w.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

    }
}