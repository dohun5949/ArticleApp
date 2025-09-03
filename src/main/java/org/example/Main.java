package org.example;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Article> lst = new ArrayList<>();
    private static List<Member> memberList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lastid = 0;
        int memberId = 0;
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
                System.out.println("=== 회원 가입 ===");
                memberId++;
                String loginId;
                while(true){
                    System.out.print("아이디 : ");
                    loginId = sc.nextLine().trim();
                    boolean flag = false;
                    if(!isJoinable(loginId)){
                        System.out.println(loginId + "는 사용할 수 없는 아이디입니다.");
                        continue;
                    }
                    break;
                }
                String loginPw;
                while(true) {
                    System.out.print("비밀 번호 : ");
                    loginPw = sc.nextLine().trim();
                    System.out.print("비밀 번호 확인 : ");
                    String rePw = sc.nextLine().trim();
                    if(!loginPw.equals(rePw)){
                        System.out.println("비밀번호를 확인하세요");
                        continue;
                    }
                    break;
                }
                System.out.print("이름 : ");
                String name = sc.nextLine().trim();
                String Date = Util.getDate();
                String UpdateDAte = Util.getDate();

                Member m = new Member(memberId, loginId, loginPw, name, Date, UpdateDAte);
                memberList.add(m);
                System.out.println(memberId + "번 회원이 등록되었습니다.");
            }else if(cmd.equals("member list")){
                System.out.println("=== 회원 목록 ===");
                for(Member m : memberList){
                    System.out.println(m.toString());
                }
            }
            else if (cmd.equals("write")) {
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();
                String date = Util.getDate();
                String newDate = Util.getDate();
                lastid++;
                Article article = new Article(lastid, title, body, date, newDate);
                lst.add(article);
                System.out.println(lastid + "번 글이 생성되었습니다.");
            } else if (cmd.equals("list")) {
                System.out.println("===== 글 목록 =====");
                System.out.println("번호  /   제목  /   내용/     작성일자");
                for (int i = lst.size() - 1; i >= 0; --i) {
                    Article a = lst.get(i);
                    System.out.println(a.getId() + " / " + a.getTitle() + " / " + a.getBody() + " / " + a.getDate());
                }
            } else if (cmd.startsWith("article find")) {
                String keyword = cmd.substring("article find".length()).trim();

                List<Article> a = lst;
                if (keyword.length() > 0) {
                    a = new ArrayList<>();
                    System.out.println("검색어 : " + keyword);
                    for (Article article : lst) {
                        if (article.getTitle().contains(keyword)) {
                            a.add(article);
                        }
                    }
                    if (a.size() == 0) {
                        System.out.println("검색 결과 없음");
                        continue;
                    }
                }
                System.out.println("===== 검색 결과 =====");
                System.out.println("번호  /   제목  /   내용/     작성일자");
                for (int i = a.size() - 1; i >= 0; --i) {
                    System.out.println(a.get(i).getId() + " / " + a.get(i).getTitle() + " / " + a.get(i).getBody() + " / " + a.get(i).getDate());
                }

            } else if (cmd.startsWith("detail")) {
                String[] str = cmd.split(" ");
                int num = Integer.parseInt(str[1]);
                Article a = getArticleId(num);
                if (a == null) {
                    System.out.println(num + "번 게시글은 없습니다.");
                    continue;
                }
                System.out.println("번호 : " + a.getId());
                System.out.println("제목 : " + a.getTitle());
                System.out.println("내용 : " + a.getBody());
                System.out.println("등록 일 : " + a.getDate());
                System.out.println("수정 일 : " + a.getNewDate());

            } else if (cmd.startsWith("delete")) {
                String[] str = cmd.split(" ");
                int num;
                try {
                    num = Integer.parseInt(str[1]);
                } catch (NumberFormatException e) {
                    System.out.println("error");
                    return;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("error");
                    return;
                }
                Article a = getArticleId(num);

                int idx = -1;
                if (a == null) {
                    System.out.println(num + "번 게시글은 없습니다.");
                    return;
                }
                lst.remove(a);
                System.out.println(num + "번 게시글이 삭제되었습니다.");

            } else if (cmd.startsWith("modify")) {
                String[] str = cmd.split(" ");
                int num = Integer.parseInt(str[1]);
                Article a = getArticleId(num);
                if (a == null) {
                    System.out.println(num + "번 게시글은 없습니다.");
                    return;
                }
                System.out.println("기존 제목 : " + a.getTitle());
                System.out.println("기존 내용 : " + a.getBody());
                System.out.print("새 제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String body = sc.nextLine().trim();

                a.setTitle(title);
                a.setBody(body);
                System.out.println(num + "번 게시글이 수정되었습니다.");

            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }
        System.out.println("프로그램 종료");
        sc.close();
    }

    private static boolean isJoinable(String loginId) {
        for(Member m : memberList){
            if(m.getLoginId().equals(loginId)){
                return false;
            }
        }
        return true;
    }

    private static Article getArticleId(int num) {
        for (Article article : lst) {
            if (article.getId() == num) {
                return article;
            }
        }
        return null;
    }
}

