package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.echo.EchoClient;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3000).usePlaintext()
        .build();
    EchoClient client = new EchoClient(channel);
    for (int i = 0; i < 5; i++) {
      System.out.println(client.echo(String.valueOf(i)));
    }

    channel.shutdownNow();
  }
}
