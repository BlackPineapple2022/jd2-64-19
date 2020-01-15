package by.academy.it.travelcompany;

import by.academy.it.travelcompany.entity.Person;

public class Film {

    private String filmName;
    private Integer year;
    private Person producer;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Person getProducer() {
        return producer;
    }

    public void setProducer(Person producer) {
        this.producer = producer;
    }

    public Film(String filmName, Integer year, Person producer) {
        this.filmName = filmName;
        this.year = year;
        this.producer = producer;
    }

    public Film() {
    }

}
