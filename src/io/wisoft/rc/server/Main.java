package io.wisoft.rc.server;

import static io.wisoft.rc.server.Information.*;

public class Main {
  public static void main(String[] args) {

    System.out.println("|----------------------------------------------|");
    System.out.println("|                                              |");
    System.out.println("|                 환영합니다!!                    |");
    System.out.println("|           RC Festival Main Server            |");
    System.out.println("|                                              |");
    System.out.println("|                              Version 1 BETA  |");
    System.out.println("|                                              |");
    System.out.println("|                        - Project by swlee -  |");
    System.out.println("|----------------------------------------------|");

    new Server();
    if (Server.isRunning()) {
      System.out.println("서버가 정상적으로 실행하고 있습니다.");
      System.out.println("IP : " + SERVER_IP);
    } else {
      System.out.println("서버가 정상적으로 실행되지 않았습니다. 다시 시도하세요.");
      System.exit(1);
    }

    /*
    todo GSON을 통한 JSON 처리
    todo NIO 기반으로 동작하는 TCP SERVER
    todo build 패턴 적용 및 final 활용
    todo 람다, 함수형 프로그래밍 지향
    todo pgsql 데이터베이스 활용, DB 서버 연동 작업 필수 (DB와 메인서버를 분리)
    todo 차량번호로 해당 클라이언트 구별 혹은 개인 아이디로 적용 - 등록 요청시 해당하는 key 값이 있으면 등록 거부, 서비스 불가
    */

  }
}
