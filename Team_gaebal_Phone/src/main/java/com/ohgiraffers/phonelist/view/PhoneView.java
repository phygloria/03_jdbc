package com.ohgiraffers.phonelist.view;

import com.ohgiraffers.phonelist.dto.PhoneDTO;
import com.ohgiraffers.phonelist.service.PhoneService;

import java.util.ArrayList;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class PhoneView {
    private static boolean state = true;
    private static PhoneService phoneService = new PhoneService();

    public static void run() {
        while (state) {
            System.out.println("화면 번호를 입력해주세요 : ");
            System.out.println("1. 연락처 전체조회");
            System.out.println("2. 연락처 이름으로 조회하기 ");
            System.out.println("3. 연락처 새로 등록하기");
            System.out.println("4. 연락처 수정하기");
            System.out.println("5. 연락처 삭제하기");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine());

            switch (index) {
                case 1:
                    phoneViewAll();
                    break;
                case 2:
                    phoneFindByName();
                    break;
                case 3:
                    phoneInsert();
                    break;
                case 4:
                    phoneUpdate();
                    break;
            }
            System.out.print("종료를 하시겠습니까? (yes Or no) 오타x 소문자만 : ");
            String result = sc.nextLine();

            if (result.equals("yes")) {
                state = false;
                sc.close();
            }

        }

    }

    public static void phoneViewAll() {
        System.out.println("전체조회");

        try {
            ArrayList phones = phoneService.phoneViewAll();
            System.out.println(phones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void phoneFindByName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 이름을 입력하세요 : ");
        String userName = sc.nextLine();
        PhoneDTO phone = null;

        try {
            phone = phoneService.phoneFindByName(userName);
            System.out.println(phone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    public static void phoneFindByNo(){
//        Scanner sc = new Scanner(System.in);
//        System.out.print("조회할 번호를 입력하세요 : ");
//        int userNo = sc.nextInt();
//        PhoneDTO phone = null;
//
//        try {
//            phone = phoneService.phoneFindByNo(userNo);
//            System.out.println(phone);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }




    public static void phoneInsert(){
        Scanner sc = new Scanner(System.in);
        PhoneDTO phone = new PhoneDTO();

        System.out.println("등록할 전화번호의 정보를 입력해주세요 ");
        System.out.print("전화번호의 이름를 입력해주세요 : ");
        phone.setUserName(sc.nextLine());
        System.out.print("전화번호 주인의 주소를 입력해주세요 : ");
        phone.setUserAddress(sc.nextLine());
        System.out.print("전화번호의 이름을 입력해주세요 예) (핸드폰, 집, 직장) : ");
        phone.setCallName(sc.nextLine());
        System.out.print("전화번호을 입력해주세요 : ");
        phone.setCallNumber(sc.nextLine());
        System.out.print("전화번호가 대표번호인지 y 나 n 으로만 입력해주세요 : ");
        phone.setMainCall(sc.nextLine());

        try {
            String result = phoneService.phoneInsert(phone);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sc.nextLine();
    }







    public static void phoneUpdate() {

        System.out.println("변경할 정보의 이름을 입력하세요");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        PhoneDTO phone = phoneService.phoneFindByName(userName);

        if (phone == null) {
            System.out.println("변경할 정보가 존재하지 않습니다.");
            return;
        }
        System.out.println(phone);
        System.out.println("어떤 정보를 수정하시겠습니까? 1.이름/2.전화번호/3.주소 : (숫자만)");
        int value = sc.nextInt();
        sc.nextLine();

        switch (value) {
            case 1:
                System.out.println("변경할 이름을 입력해주세요");
                phone.setUserName(sc.nextLine());
                break;
            case 2:
                System.out.println("변경할 전화번호를 입력해주세요");
                phone.setCallNumber(sc.nextLine());
                break;
            case 3:
                System.out.println("변경할 주소를 입력해주세요");
                phone.setUserAddress(sc.nextLine());
                break;
            default:

                System.out.println("입력 오류");
                break;
        }

        try {
            PhoneDTO modifyPhone = phoneService.phoneModify(phone);
//            System.out.println(modifyPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
