package hr.fer.tel.rassus.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.Getter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RPCServer {
    private static final Logger logger = Logger.getLogger(RPCServer.class.getName());

    private final ReadingExchangeService service;
    @Getter
    private int port;
    private Server server;

    public RPCServer(ReadingExchangeService service, int port) {
        this.service = service;
        this.port = port;
    }

    public void start() throws IOException {
        // Register the service
        server = ServerBuilder.forPort(port)
                .addService(service)
                .build()
                .start();
        this.port = server.getPort();
        logger.info("Server started on " + port);

        //  Clean shutdown of server in case of JVM shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down");
            try {
                RPCServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("Server shut down");
        }));
    }

    /**
     * Stops the server.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread
     *
     * @throws InterruptedException the interrupted exception
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


}

