package hr.fer.tel.rassus;

import hr.fer.tel.rassus.HttpClient.RetrofitService;
import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import hr.fer.tel.rassus.HttpClient.factory.SensorFactory;
import hr.fer.tel.rassus.HttpClient.repo.MySensorRepo;
import hr.fer.tel.rassus.examples.SimpleUnaryRPCServer;
import hr.fer.tel.rassus.examples.UppercaseService;

public class Main {

    private static final String SERVER_URL = "http://localhost:8090/sensors/";

    private static Sensor CURRENT_SENSOR;
    private static Sensor CLOSEST_SENSOR;

    private static long CURRENT_SENSOR_ID;


    public static void main(String[] args) {

        RetrofitService service = new RetrofitService(SERVER_URL);

        try {
            // start rpc server
            var RPCserver = new SimpleUnaryRPCServer(new UppercaseService(), 0);
            RPCserver.start();
            var port = RPCserver.getPort();

            // registrate sensor
            Main.CURRENT_SENSOR = SensorFactory.createRandomSensor(port);
            System.err.println("port je " + port);
            Main.CURRENT_SENSOR_ID = service.register(CURRENT_SENSOR);
            System.err.println(Main.CURRENT_SENSOR_ID);

            // read from csv
            MySensorRepo repo = new MySensorRepo();


            long startTime = System.currentTimeMillis();
            while (true) {
                long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Seconds elapsed: " + elapsedSeconds);

                // napravi ocitanje
                System.out.println("Current reading: " + repo.getReading((int) elapsedSeconds % 100));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Process interrupted");
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }


    }


}
