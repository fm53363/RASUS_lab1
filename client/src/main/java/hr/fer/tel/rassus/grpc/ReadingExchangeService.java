package hr.fer.tel.rassus.grpc;

import hr.fer.tel.rassus.HttpClient.repo.MySensorRepo;
import hr.fer.tel.rassus.HttpClient.repo.SensorReading;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;


public class ReadingExchangeService extends ReadingExchangeGrpc.ReadingExchangeImplBase {

    private static final Logger logger = Logger.getLogger(ReadingExchangeService.class.getName());


    @Override
    public void requestReading(ReadingRequest request, StreamObserver<ReadingResponse> responseObserver) {
        logger.info("Got a new request for reading with id= " + request.getReadingId());
        ReadingResponse response = checkReading(request);
        logger.info("Responding with " + SensorReadingConverter.toSensorReading(response) + "\n");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ReadingResponse checkReading(ReadingRequest request) {
        MySensorRepo repo = MySensorRepo.getInstance();
        SensorReading reading = repo.getReading(request.getReadingId());
        return SensorReadingConverter.toReadingResponse(reading);
    }
}
