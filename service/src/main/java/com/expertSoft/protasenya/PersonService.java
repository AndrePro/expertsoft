package com.expertSoft.protasenya;

import java.util.ArrayList;
import java.util.List;


public class PersonService {

    PersonDao pd;
    private int noOfRecords;

    public void add(String path) {
        ReaderCsv readerCsv = new ReaderCsv();
        List<Person> persons = readerCsv.readingCsv(path);
        pd = new PersonDao();
        for (int i = 0; i < persons.size(); i++) {
            pd.add(persons.get(i));
        }
    }

    public List<Person> getAll(int offset, int noOfRecords) {
        pd = new PersonDao();
        List<Person> person = new ArrayList<>();
        person = pd.getAll(offset, noOfRecords);
        this.noOfRecords = pd.getNoOfRecords();
        return person;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
