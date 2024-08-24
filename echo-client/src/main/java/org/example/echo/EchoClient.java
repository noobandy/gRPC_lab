package org.example.echo;

import io.grpc.Channel;
import org.example.echo.EchoGrpc.EchoBlockingStub;
import org.example.echo.EchoService.Request;

public class EchoClient {

  private final EchoBlockingStub echoBlockingStub;

  public EchoClient(Channel channel) {
    echoBlockingStub = EchoGrpc.newBlockingStub(channel);
  }

  public String echo(String msg) {
    return echoBlockingStub.echo(Request.newBuilder().setMsg(msg).build()).getMsg();
  }
}
