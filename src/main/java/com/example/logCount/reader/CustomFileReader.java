package com.example.logCount.reader;

/*
 * @author Shubham Bansal
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class CustomFileReader {
    private static volatile BufferedReader bufferedReader;

    private CustomFileReader() throws FileNotFoundException {
    }

    public static BufferedReader getInstance() throws FileNotFoundException {
        synchronized (CustomFileReader.class) {
            if (!Objects.nonNull(bufferedReader))
                bufferedReader = new BufferedReader(new FileReader(""), 1);
            return bufferedReader;
        }
    }
}
