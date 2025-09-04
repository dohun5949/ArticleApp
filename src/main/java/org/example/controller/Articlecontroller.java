package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Articlecontroller {
    private Scanner sc;
    private int lastid = 0;
    private static List<Article> lst = new ArrayList<>();

    public Articlecontroller(Scanner sc) {
        this.sc = sc;
    }

    public void doWrite() {
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
    }

    public void showList() {
        System.out.println("===== 글 목록 =====");
        System.out.println("번호  /   제목  /   내용/     작성일자");
        for (int i = lst.size() - 1; i >= 0; --i) {
            Article a = lst.get(i);
            System.out.println(a.getId() + " / " + a.getTitle() + " / " + a.getBody() + " / " + a.getDate());
        }
    }

    public void doArticleFind(String cmd) {
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
                return;
            }
        }
        System.out.println("===== 검색 결과 =====");
        System.out.println("번호  /   제목  /   내용/     작성일자");
        for (int i = a.size() - 1; i >= 0; --i) {
            System.out.println(a.get(i).getId() + " / " + a.get(i).getTitle() + " / " + a.get(i).getBody() + " / " + a.get(i).getDate());
        }
    }

    public void showDetail(String cmd) {
        String[] str = cmd.split(" ");
        int num = Integer.parseInt(str[1]);
        Article a = getArticleId(num);
        if (a == null) {
            System.out.println(num + "번 게시글은 없습니다.");
            return;
        }
        System.out.println("번호 : " + a.getId());
        System.out.println("제목 : " + a.getTitle());
        System.out.println("내용 : " + a.getBody());
        System.out.println("등록 일 : " + a.getDate());
        System.out.println("수정 일 : " + a.getNewDate());
    }

    public void doDelete(String cmd) {
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

    }

    public void doModify(String cmd) {
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
