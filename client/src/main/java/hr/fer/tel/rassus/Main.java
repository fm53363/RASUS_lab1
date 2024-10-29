package hr.fer.tel.rassus;

import hr.fer.tel.rassus.HttpClient.MyHttpClient;
import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import hr.fer.tel.rassus.HttpClient.factory.SensorFactory;
import hr.fer.tel.rassus.HttpClient.repo.MySensorRepo;
import hr.fer.tel.rassus.examples.SimpleUnaryRPCServer;
import hr.fer.tel.rassus.examples.UppercaseService;

public class Main {

    private static final String SERVER_URL = "http://localhost:8090/sensors/";
    private static final MyHttpClient CLIENT = new MyHttpClient(SERVER_URL);

    private static Sensor CLOSEST_SENSOR = null;
    private static Sensor CURRENT_SENSOR;
    private static long CURRENT_SENSOR_ID;

    public static void main(String[] args) {
        try {
            MySensorRepo repo = MySensorRepo.getInstance();  // read from csv

            // start rpc server
            var RPCserver = new SimpleUnaryRPCServer(new UppercaseService(), 0);
            RPCserver.start();
            var port = RPCserver.getPort();


            // registrate sensor
            CURRENT_SENSOR = SensorFactory.createRandomSensor(port);
            CURRENT_SENSOR_ID = CLIENT.register(CURRENT_SENSOR);

            CLOSEST_SENSOR = CLIENT.findClosestSensor(CURRENT_SENSOR_ID);

            long startTime = System.currentTimeMillis();
            while (true) {
                long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Seconds elapsed: " + elapsedSeconds);
                // napravi ocitanje
                System.out.println("Current reading: " + repo.getReading((int) elapsedSeconds % 100));
                //TODO send grpc message to closest sensor
                if (CLOSEST_SENSOR != null) {
                    CLOSEST_SENSOR = CLIENT.findClosestSensor(CURRENT_SENSOR_ID);
                }


                //TODO calibrate readings
                //TODO SEND calibrated data to sensor
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
