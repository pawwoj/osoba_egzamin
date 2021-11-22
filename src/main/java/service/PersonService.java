package service;

import lombok.Getter;
import model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Getter

public class PersonService {

    private final List<Person> personList = new ArrayList<>();
    private final Person p1 = new Person("Jan", "Kowalski", 30, "Warszawa");
    private final Person p2 = new Person("Zbigniew", "Niewiadomski", 12, "Kraków");
    private final Person p3 = new Person("Halina", "Mostowiak", 32, "Warszawa");
    private final Person p4 = new Person("Mariusz", "Smagaj", 50, "Opole");
    private final Person p5 = new Person("Monika", "Oplaska", 18, "Opole");
    private final Person p6 = new Person("Dorota", "Psota", 25, "Elbląg");
    private final Person[] personTab = {p1, p2, p3, p4, p5, p6};

    public PersonService() {
        personList.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
    }

    /*  "- metode ktora jako arg pobiera liste osob i nazwe miasta
           a jako wynik zwraca ilosc osob z tej tablicy ktora mieszka w danym miescie"

           Nie wiedziałem czy zrobić dla listy czy z tablicy to zrobiłem dla obu */
    public int getHowManyPeopleLiveInTheCity(List<Person> personList, String miasto) {
        return Math.toIntExact(personList.stream().filter(person -> person.getMiasto().equals(miasto)).count());
    }

    public int getHowManyPeopleLiveInTheCity(Person[] personTab, String miasto) {
        return Math.toIntExact(Arrays.stream(personTab).filter(person -> person.getMiasto().equals(miasto)).count());
    }

    public List<Person> getListOfPeopleFromTheCity(List<Person> personList, String miasto) {
        return personList.stream().filter(person -> person.getMiasto().equals(miasto)).collect(Collectors.toList());
    }

    public List<String> getListCityAppearingInTheList(List<Person> personList) {
        List<String> listWithDuplicates = personList.stream()
                .map(Person::getMiasto).collect(Collectors.toList());
        return new ArrayList<>(new HashSet<>(listWithDuplicates));
    }

    public void savePeopleToFile(Person[] people, String fileName) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert writer != null;
            writer.write(people.length + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Person person : people) {
            try {
                writer.write(person.toStringSaveFormat() + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person[] readFromFile(String fileName) {
        File file = new File(fileName);
        int lines = 0;
        try {
            lines = (int) Files.lines(Path.of(fileName)).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Person[] personTab = new Person[lines];
        try {
            Scanner fileScanner = new Scanner(file);
            int i = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");
                personTab[i++] = (new Person(
                        splittedArray[0],
                        splittedArray[1],
                        Integer.parseInt(splittedArray[2]),
                        splittedArray[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personTab;
    }
}
