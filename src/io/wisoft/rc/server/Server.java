package io.wisoft.rc.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.*;

import static io.wisoft.rc.server.Information.*;

public class Server {
  private static final int BUFFER_SIZE = 1000;
  private static final int PORT = 5001;
  private static ServerSocketChannel serverSocketChannel;
  private static ExecutorService executorService = Executors.newFixedThreadPool
      (Runtime.getRuntime().availableProcessors());
  private static Charset charset = Charset.forName("UTF-8");

  public Server() {
    try {
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(true);
      serverSocketChannel.bind(new InetSocketAddress(PORT));
    } catch (IOException e) {
      System.out.println(e);
    }
    accept();
  }

  private void accept() {
    Runnable runnable = this::threadAccept;
    executorService.execute(new Thread(runnable));
  }

  private void threadAccept() {
    try {
      //noinspection InfiniteLoopStatement
      while (true) {
        SocketChannel socketChannel = serverSocketChannel.accept();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
        System.out.println("[연결 수락]" + inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        if (socketChannel.read(byteBuffer) == -1) {
          throw new IOException();
        }
        byteBuffer.flip();
        String registerCode  = charset.decode(byteBuffer).toString();
        clientSet(registerCode, socketChannel);
        listen(socketChannel);
      }
    } catch (IOException io) {
      System.out.println("- IO 에러 발생 : " + io + " -");
    } catch (NullPointerException n) {
      n.printStackTrace();
    }
  }

  private void listen(final SocketChannel socketChannel) {
    Runnable runnable = () -> {
      try {
        //noinspection InfiniteLoopStatement
        while (true) {
          ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
          if (socketChannel.read(byteBuffer) == -1) {
            throw new IOException();
          }
          byteBuffer.flip();
          String receiveData = charset.decode(byteBuffer).toString();
          System.out.println(receiveData);
          // TODO SERVICE 객체 넘기기
        }
      } catch (IOException e) {
        Thread.currentThread().interrupt();
      } catch (NullPointerException n) {
        n.printStackTrace();
      }
    };
    executorService.execute(new Thread(runnable));
  }

  public static boolean isRunning() {
    return serverSocketChannel.isOpen();
  }

}
