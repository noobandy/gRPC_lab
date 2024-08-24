package org.example.echo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import org.example.echo.EchoGrpc.EchoImplBase;
import org.example.echo.EchoService.Reply;
import org.example.echo.EchoService.Request;

public class EchoServer {

  private final int port;
  private final Server server;

  public EchoServer(int port) {
    this.port = port;
    server = ServerBuilder.forPort(port)
        .addService(new EchoService())
        .build();
  }


  public void start() throws IOException {
    server.start();
    System.out.println("Server started at port: " + port);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      // Use stderr here since the logger may have been reset by its JVM shutdown hook.
      System.err.println("*** shutting down gRPC server since JVM is shutting down");
      EchoServer.this.stop();
      System.err.println("*** server shut down");
    }));
  }

  public void stop() {
    server.shutdown();
    System.out.println("Server stopped");
  }

  public void blockUntilTerminated() throws InterruptedException {
    server.awaitTermination();
  }


  private static class EchoService extends EchoImplBase {

    @Override
    public void echo(Request request, StreamObserver<Reply> responseObserver) {
      responseObserver.onNext(Reply.newBuilder()
          .setMsg(String.format("From Echo Server %s", request.getMsg()))
          .build());
      responseObserver.onCompleted();
    }
  }
}
