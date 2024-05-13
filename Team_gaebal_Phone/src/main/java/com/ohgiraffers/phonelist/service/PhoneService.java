package com.ohgiraffers.phonelist.service;

import com.ohgiraffers.phonelist.dao.PhoneRepository;
import com.ohgiraffers.phonelist.dto.PhoneDTO;

import java.util.ArrayList;

public class PhoneService {

    private static PhoneRepository phoneRepository;

    public PhoneService() {
        this.phoneRepository = new PhoneRepository();
    }

    // service
    // 비즈니스의 도메인과 관련된 역할을 수행한다.
    // 데이터베이스와 연결된 작업을 수행한다.
    public ArrayList phoneViewAll() throws Exception {
        ArrayList phones = phoneRepository.phoneViewAll();

        if(phones == null){
            throw new Exception("정보 조회실패");
        }

        return phones;
    }





    public static PhoneDTO phoneFindByName(String userName) {
        PhoneDTO findPhone = phoneRepository.phoneFindByName(userName);
        if(findPhone != null){
            return findPhone;
        }else{

            return null;
        }
    }


    public PhoneDTO phoneModify(PhoneDTO phone) throws Exception {
        PhoneDTO modifyPhone = phoneRepository.phoneFindByName(phone.getUserName());

        int result = phoneRepository.phoneModify(phone);
        if(result < 0){
            throw new Exception("변경실패");
        }
        System.out.println("등록 성공");

        return modifyPhone;

    }


}
