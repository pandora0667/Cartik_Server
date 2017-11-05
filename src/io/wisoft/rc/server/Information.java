package io.wisoft.rc.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class Information {
  static final String SERVER_IP = getServerIP();
  private static final HashMap<String, SocketChannel> clientInfo = new HashMap<>();

  private static String getServerIP() {
    try {
      return  InetAddress.getLocalHost().toString();
    } catch (UnknownHostException u) {
      log("서버 호스트를 알 수 업습니다.");
      return  null;
    }
  }

  static synchronized void clientSet(final String clientID, final SocketChannel socketChannel) {
    if (clientInfo.get(clientID) != socketChannel) {
      clientInfo.remove(clientID);
    }

    log("client : " + clientID + "가 접속했습니다.");
    log("현재 접속자 수 : " + clientInfo.size());
    clientInfo.put(clientID, socketChannel);
  }

  static synchronized void clientRemove(final String clientID, final SocketChannel socketChannel) {
    if (clientInfo.containsValue(socketChannel)) {
      clientInfo.remove(clientID);
    }

    log(clientID + "가 서버접속을 종료했습니다.");
    log("현재 접속자 수 : " + clientInfo.size());
  }

  static synchronized boolean clientIsValue(final SocketChannel socketChannel) {
    return clientInfo.containsValue(socketChannel);
  }

  static void log(final String log) {
    System.out.println("-- " + log + " --");
  }
}
