package application;

import service.PersonService;

public class Application {
    public void run() {
        PersonService personService = new PersonService();
        System.out.println("getHowManyPeopleLiveInTheCity : " + personService.getHowManyPeopleLiveInTheCity(personService.getPersonList(), "Opole"));
        System.out.println("getHowManyPeopleLiveInTheCity : " + personService.getHowManyPeopleLiveInTheCity(personService.getPersonTab(), "Elbląg"));
        System.out.println("getListOfPeopleLiveInTheCity : " + personService.getListOfPeopleFromTheCity(personService.getPersonList(), "Warszawa"));
        System.out.println("getListCityAppearingInTheList : " + personService.getListCityAppearingInTheList(personService.getPersonList()));
        personService.savePeopleToFile(personService.getPersonTab(), "save.txt");
        personService.readFromFile("load.txt");
    }
}
