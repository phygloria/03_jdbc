package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {
    public static void main(String[] args){
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "gangnam", "gangnam");
            System.out.println("con : " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}





//커넥션은 인터페이스. 인터페이스는 추상메소드들의 모음 //인터페이스는 상속받으면 하나의 타입으로 쓸수있다.