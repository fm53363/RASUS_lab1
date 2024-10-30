package hr.fer.tel.rassus.grpc;

import hr.fer.tel.rassus.HttpClient.repo.SensorReading;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RPCClient {
    private static final Logger logger = Logger.getLogger(RPCClient.class.getName());


    private final ManagedChannel channel;
    private final ReadingExchangeGrpc.ReadingExchangeBlockingStub readingExchangeBlockingStub;

    public RPCClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.readingExchangeBlockingStub = ReadingExchangeGrpc.newBlockingStub(channel);
    }

    protected void stop() throws InterruptedException {
//    Initiates an orderly shutdown in which preexisting calls continue but new calls are
//    immediately cancelled. Waits for the channel to become terminated, giving up if the timeout
//    is reached.
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public SensorReading requestReading(int id) {
        ReadingRequest request = ReadingRequest.newBuilder().setReadingId(id).build();

        logger.info("Sending request for reading with id: " + request.getReadingId());
        try {

            ReadingResponse response = this.readingExchangeBlockingStub.requestReading(request);
            SensorReading result = SensorReadingConverter.toSensorReading(response);
            logger.info("Received: " + result);
            return result;

        } catch (StatusRuntimeException e) {
            logger.info("RPC failed: " + e.getMessage());
        }
        return new SensorReading();
    }
}
