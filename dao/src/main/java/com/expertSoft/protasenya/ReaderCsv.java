package com.expertSoft.protasenya;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderCsv {

    public List<Person> readingCsv(String pathToFile) {

        List<Person> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            String str = br.readLine();
            while (str != null) {
                String[] inputData = str.split(",");
                Person person = new Person(inputData[0], inputData[1], inputData[2],
                        inputData[3], inputData[4]);
                list.add(person);
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return list;
    }
}
