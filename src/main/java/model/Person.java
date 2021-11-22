package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class Person {
    private String imie;
    private String nazwisko;
    private int wiek;
    private String miasto;

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko + " " + this.wiek + " " + this.miasto;
    }

    public String toStringSaveFormat(){
        return this.imie + ";" + this.nazwisko + ";" + this.wiek + ";" + this.miasto;
    }
}
