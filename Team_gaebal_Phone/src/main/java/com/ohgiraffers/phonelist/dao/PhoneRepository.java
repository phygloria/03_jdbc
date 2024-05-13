package com.ohgiraffers.phonelist.dao;

import com.ohgiraffers.phonelist.dto.PhoneDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class PhoneRepository {

    private Properties pros = new Properties();
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;

    public PhoneRepository() {
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/phonelist/mapper/team_gaebal-query.xml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList phoneViewAll(){
        ArrayList arrayList = new ArrayList();

        String query = pros.getProperty("phoneViewAll");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()){
                PhoneDTO phone = new PhoneDTO();
                phone.setUserNo(rset.getInt("user_no"));
                phone.setUserName(rset.getString("user_name"));
                phone.setUserAddress(rset.getString("user_address"));
                phone.setCallName(rset.getString("call_name"));
                phone.setCallNumber(rset.getString("call_number"));
                phone.setMainCall(rset.getString("main_call"));
                arrayList.add(phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(rset);
            close(pstmt);
            close(con);
        }

        return arrayList;
    }



    public PhoneDTO phoneFindByName(String userName){
        String query = pros.getProperty("phoneFindByName");
        con = getConnection();
        PhoneDTO phone = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userName);
            rset = pstmt.executeQuery();

            if (rset.next()){
                phone = new PhoneDTO();
                phone.setUserNo(rset.getInt("USER_NO"));
                phone.setUserName(rset.getString("USER_NAME"));
                phone.setUserAddress(rset.getString("USER_ADDRESS"));
                phone.setCallName(rset.getString("CALL_NAME"));
                phone.setCallNumber(rset.getString("CALL_NUMBER"));
                phone.setMainCall(rset.getString("MAIN_CALL"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return phone;
    }


    public int phoneInsert(PhoneDTO phone) {
        String query = pros.getProperty("phoneInsert");
        String query2 = pros.getProperty("numInsert");
        con = getConnection();
        int result = 0;
        int result1 = 0;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,phone.getUserName());
            pstmt.setString(2,phone.getUserAddress());

            result = pstmt.executeUpdate();

            pstmt = con.prepareStatement(query2);
            pstmt.setString(1,phone.getCallName());
            pstmt.setString(2,phone.getCallNumber());
            pstmt.setString(3,phone.getMainCall());
            result1 = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result + result1;
    }






    public int phoneModify(PhoneDTO phone) {
        // 쿼리불러오기
        String query1 = pros.getProperty("phoneModify1");
        con = getConnection();
        int result1 = 0;
        try {
            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, phone.getUserName());
            pstmt.setString(2, phone.getUserAddress());
            pstmt.setInt(3, phone.getUserNo());

            result1 = pstmt.executeUpdate();



        String query2 = pros.getProperty("phoneModify2");
        con = getConnection();
        int result2 = 0;

            pstmt = con.prepareStatement(query2);
            pstmt.setString(1, phone.getCallNumber());
            pstmt.setInt(2, phone.getUserNo());

            result2 = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return 0;
    }

}
