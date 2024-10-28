package hr.fer.tel.rassus.HttpClient.repo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySensorRepo {
    private static final String CSV_FILE_NAME = "readings[6].csv";
    private List<SensorReading> sensorReadings;

    public MySensorRepo() throws IOException, CsvException {
        this.sensorReadings = new ArrayList<>();
        readFromCsv();
    }


    private static Double parseDoubleOrNull(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            var repo = new MySensorRepo();
            repo.sensorReadings.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readFromCsv() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(CSV_FILE_NAME));
        List<String[]> records = reader.readAll();
        Iterator<String[]> iterator = records.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            String[] record = iterator.next();
            SensorReading reading = new SensorReading();
            reading.setTemperature(parseDoubleOrNull(record[0]));
            reading.setPressure(parseDoubleOrNull(record[1]));
            reading.setHumidity(parseDoubleOrNull(record[2]));
            reading.setCo(parseDoubleOrNull(record[3]));
            reading.setSo2(parseDoubleOrNull(record[4]));
            this.sensorReadings.add(reading);
        }
        reader.close();
    }

    public SensorReading getReading(int id) {
        return sensorReadings.get(id);
    }
}
