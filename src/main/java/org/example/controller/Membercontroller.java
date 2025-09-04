package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Membercontroller {
    private Scanner sc;
    private int memberId = 0;
    private static List<Member> memberList = new ArrayList<>();

    public Membercontroller(Scanner sc) {
        this.sc = sc;
    }

    public void doJoin() {
        System.out.println("=== 회원 가입 ===");
        memberId++;
        String loginId;
        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();
            boolean flag = false;
            if (!isJoinable(loginId)) {
                System.out.println(loginId + "는 사용할 수 없는 아이디입니다.");
                continue;
            }
            break;
        }
        String loginPw;
        while (true) {
            System.out.print("비밀 번호 : ");
            loginPw = sc.nextLine().trim();
            System.out.print("비밀 번호 확인 : ");
            String rePw = sc.nextLine().trim();
            if (!loginPw.equals(rePw)) {
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
    }

    public void doList() {
        System.out.println("=== 회원 목록 ===");
        for(Member m : memberList){
            System.out.println(m.toString());
        }
    }
    private static boolean isJoinable(String loginId) {
        for(Member m : memberList){
            if(m.getLoginId().equals(loginId)){
                return false;
            }
        }
        return true;
    }
}

