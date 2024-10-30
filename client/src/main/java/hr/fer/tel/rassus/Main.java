package hr.fer.tel.rassus;

import hr.fer.tel.rassus.HttpClient.MyHttpClient;
import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import hr.fer.tel.rassus.HttpClient.repo.MySensorRepo;
import hr.fer.tel.rassus.HttpClient.repo.SensorFactory;
import hr.fer.tel.rassus.HttpClient.repo.SensorReading;
import hr.fer.tel.rassus.grpc.RPCClient;
import hr.fer.tel.rassus.grpc.RPCServer;
import hr.fer.tel.rassus.grpc.ReadingExchangeService;

import java.util.logging.Logger;


public class Main {

    private static final String SERVER_URL = "http://localhost:8090/sensors/";
    private static final MyHttpClient httpClient = new MyHttpClient(SERVER_URL);
    private static final MySensorRepo SENSOR_REPO = MySensorRepo.getInstance();

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static RPCClient rpcClient;
    private static RPCServer rpcServer;


    public static void main(String[] args) {
        try {
            // start rpc server
            rpcServer = new RPCServer(new ReadingExchangeService(), 0);
            rpcServer.start();
            int port = rpcServer.getPort();

            // registrate sensor
            Sensor currentSensor = SensorFactory.createRandomSensor(port);
            Long currentSensorId = httpClient.register(currentSensor);
            Sensor closestSensor = null;

            long startTime = System.currentTimeMillis();
            while (true) {
                long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;

                // napravi ocitanje
                int currentReadingId = (int) elapsedSeconds % 100;
                SensorReading currentReading = SENSOR_REPO.getReading(currentReadingId);
                logger.info("Current reading: " + currentReading);

                if (closestSensor == null) {
                    closestSensor = httpClient.findClosestSensor(currentSensorId);
                }

                SensorReading neighbourReading = null;
                if (closestSensor != null) {
                    rpcClient = new RPCClient(closestSensor.getIp(), closestSensor.getPort());
                    neighbourReading = rpcClient.requestReading(currentReadingId + 1);
                }

                // calibrate readings
                SensorReading calibratedReading = currentReading;
                if (neighbourReading != null) {
                    calibratedReading = currentReading.calibrate(neighbourReading);
                }

                //TODO SEND calibrated data to server
                try {
                    Thread.sleep(20000);
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
