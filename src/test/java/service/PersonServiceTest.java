package service;

import model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonServiceTest {

    @Test
    @DisplayName("Add to list 2 persons from searched city - function return 2")
    void shouldCountTwoPeopleFromCity() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String city = "Opole";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getHowManyPeopleLiveInTheCity(personList, city)).isEqualTo(2);
    }

    @Test
    @DisplayName("Add to table 2 persons from searched city - function return 2")
    void shouldCountTwoPeopleFromCityInTable() {
        PersonService personService = new PersonService();
        Person p4 = new Person("Mariusz", "Smagaj", 50, "Opole");
        Person p5 = new Person("Monika", "Oplaska", 18, "Opole");
        Person p6 = new Person("Dorota", "Psota", 25, "Elbląg");
        Person[] personTab = {p4, p5, p6};
        String city = "Opole";


        assertThat(personService.getHowManyPeopleLiveInTheCity(personTab, city)).isEqualTo(2);
    }

    @Test
    @DisplayName("Person from searched city not included in List - function return 0")
    void shouldNotFindAnyPersonFromCity() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String city = "Sopot";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getHowManyPeopleLiveInTheCity(personList, city)).isEqualTo(0);
    }

    @Test
    @DisplayName("Find persons from given city - return List of people from given city")
    void shouldReturnListOfPeopleFromGivenCity() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String city = "Opole";
        String expectedListToString = "[Mariusz Smagaj 50 Opole, Monika Oplaska 18 Opole]";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListOfPeopleFromTheCity(personList, city).toString())
                .isEqualTo(expectedListToString);
    }

    @Test
    @DisplayName("People from given city are not in the List - function return empty list")
    void shouldReturnEmptyListOfPeopleFromGivenCity() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String city = "Kraków";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListOfPeopleFromTheCity(personList, city)).isEmpty();
    }

    @Test
    @DisplayName("Find persons from given city - returned List is not empty")
    void shouldSayThatListOfPeopleFromGivenCityIsNotEmpty() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String city = "Elbląg";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListOfPeopleFromTheCity(personList, city)).isNotEmpty();
    }

    @Test
    @DisplayName("List of cities where people live is not empty")
    void shouldSayReturnedListIsNotEmpty() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListCityAppearingInTheList(personList)).isNotEmpty();
    }

    @Test
    @DisplayName("Person List contain duplicated City " +
            "- confirm that returned list contain only one instance of the City")
    void shouldSayThatReturnedListOfCitiesContainsOnlyOnceCityWhichIsDuplicatedInPersonList() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListCityAppearingInTheList(personList)).containsOnlyOnce("Opole");
    }

    @Test
    @DisplayName("People from searched List live from two cities - function return list of two elements ")
    void shouldSayReturnedListContainTwoElements() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListCityAppearingInTheList(personList)).hasSize(2);
    }

    @Test
    @DisplayName("Returned list contains expected cities without duplicate")
    void shouldSayReturnedListOfCitiesToStringIsEqualToExpectedList() {
        PersonService personService = new PersonService();
        List<Person> personList = new ArrayList<>();
        String expectedListToString = "[Opole, Elbląg]";
        personList.add(new Person("Mariusz", "Smagaj", 50, "Opole"));
        personList.add(new Person("Monika", "Oplaska", 18, "Opole"));
        personList.add(new Person("Dorota", "Psota", 25, "Elbląg"));

        assertThat(personService.getListCityAppearingInTheList(personList).toString()).isEqualTo(expectedListToString);
    }

    @Test
    @DisplayName("Saved file contain persons data and they counter")
    void shouldSayThatSavedFileContainCounterOfPeopleAndPersonsData() throws IOException {
        PersonService personService = new PersonService();
        Person[] personTab = {
                new Person("Jan", "Kowalski", 30, "Warszawa"),
                new Person("Mariusz", "Smagaj", 50, "Opole")};
        personService.savePeopleToFile(personTab, "tempSave.txt");
        Path fileName = Path.of("tempSave.txt");
        String searchedString = "2\r\nJan;Kowalski;30;Warszawa\r\nMariusz;Smagaj;50;Opole\r\n";
        String actual = Files.readString(fileName);

        assertThat(actual).isEqualTo(searchedString);
    }

    @Test
    @DisplayName("Saved file is not empty")
    void shouldSayThatSavedFileIsNotEmpty() throws IOException {
        PersonService personService = new PersonService();
        Person[] personTab = {
                new Person("Jan", "Kowalski", 30, "Warszawa"),
                new Person("Mariusz", "Smagaj", 50, "Opole")};
        personService.savePeopleToFile(personTab, "tempSave.txt");
        Path fileName = Path.of("tempSave.txt");
        String actual = Files.readString(fileName);

        assertThat(actual).isNotEmpty();
    }

    @Test
    @DisplayName("Read data from file - returned Tab is not empty")
    void shouldSayThatTabFromReadFileIsNotEmpty() {
        PersonService personService = new PersonService();

        assertThat(personService.readFromFile("tempLoad.txt")).isNotEmpty();
    }

    @Test
    @DisplayName("Read data from file - returned Tab is not empty")
    void shouldSayThatDataInTabFromReadFileIsEqualToExpectedTab() {
        PersonService personService = new PersonService();
        Person[] personTab = {
                new Person("Jan", "Kowalski", 30, "Warszawa"),
                new Person("Mariusz", "Smagaj", 50, "Opole")};

        assertThat(Arrays.stream(personService.readFromFile("tempLoad.txt")).toList().toString())
                .isEqualTo(Arrays.stream(personTab).toList().toString());
    }
}