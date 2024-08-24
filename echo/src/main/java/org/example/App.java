package org.example;
import java.io.IOException;
import org.example.echo.EchoServer;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Starting Echo Server");
    EchoServer server = new EchoServer(3000);
    server.start();
    server.blockUntilTerminated();
  }
}
