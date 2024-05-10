package com.ohgiraffers.common;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
// 했는데 안되네? 그럼 바꾸면 됨
public class JDBCTemplate {
    public static Connection getConnection(){
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);
            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void close(Connection con){
        try {
            if(con != null & !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 스테이트먼트는 인터페이스. 인터페이스는 추상메소드 모으려고. 왜냐면 #%$^$ SQL가져다 쓰려고.
    // 스테이트먼트는 직접 넣는거. 프리페어스테이트먼트는 ?(물음표)사용. 이 둘의 차이는 매개변수를 쓰냐안쓰냐의 차이. 행위는 같음.
    // 프리페어스테이트먼트가 매개변수를 전달함.
    public static void close(PreparedStatement pstmt){
        try {
            if(pstmt != null & !pstmt.isClosed()){
                pstmt.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void close(ResultSet rset){
        try {
            if(rset !=null && !rset.isClosed()){
                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
