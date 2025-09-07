package org.example.ArticleManager;

import org.example.controller.Articlecontroller;
import org.example.controller.Membercontroller;

import java.util.Scanner;

public class App {

    public void run() {
        Scanner sc = new Scanner(System.in);
        Membercontroller membercontroller = new Membercontroller(sc);
        Articlecontroller articlecontroller = new Articlecontroller(sc);
        while (true) {
            System.out.print("명령어 : ");
            String cmd = sc.nextLine().trim();
            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            } else if (cmd.equals("member join")) {
                membercontroller.doJoin();
            } else if (cmd.equals("member list")) {
                membercontroller.doList();
            } else if (cmd.equals("write")) {
                articlecontroller.doWrite();
            } else if (cmd.equals("list")) {
                articlecontroller.showList();
            } else if (cmd.startsWith("article find")) {
                articlecontroller.doArticleFind(cmd);
            } else if (cmd.startsWith("detail")) {
                articlecontroller.showDetail(cmd);

            } else if (cmd.startsWith("delete")) {
                articlecontroller.doDelete(cmd);
            } else if (cmd.startsWith("modify")) {
                articlecontroller.doModify(cmd);
            }else if(cmd.equals("longin")){
                membercontroller.login();
            }else if(cmd.equals("logout")){
                membercontroller.logout();
            }else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }
        System.out.println("프로그램 종료");
        sc.close();
    }

}
