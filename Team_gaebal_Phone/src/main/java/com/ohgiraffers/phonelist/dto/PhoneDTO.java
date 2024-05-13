package com.ohgiraffers.phonelist.dto;

public class PhoneDTO {

    private int userNo;
    private String userName;
    private String userAddress;
    private String callName;
    private String callNumber;
    private String mainCall;

    public PhoneDTO() {
    }

    public PhoneDTO(int userNo, String userName, String userAddress, String callName, String callNumber) {
        this.userNo = userNo;
        this.userName = userName;
        this.userAddress = userAddress;
        this.callName = callName;
        this.callNumber = callNumber;

    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getMainCall() {
        return mainCall;
    }

    public void setMainCall(String mainCall) {
        this.mainCall = mainCall;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", callName='" + callName + '\'' +
                ", callNumber='" + callNumber + '\'' +
                ", mainCall='" + mainCall + '\'' +
                '}' + "\n";
    }
}
