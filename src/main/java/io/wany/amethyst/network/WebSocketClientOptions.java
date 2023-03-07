package io.wany.amethyst.network;

import java.util.HashMap;

public class WebSocketClientOptions {

  public boolean AUTO_RECONNECT;
  public HashMap<String, String> HEADERS;

  public WebSocketClientOptions() {
    this.AUTO_RECONNECT = false;
    this.HEADERS = new HashMap<>();
  }

}
