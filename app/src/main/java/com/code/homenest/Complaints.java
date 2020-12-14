package com.code.homenest;

public class Complaints {
    private String Phone;
    private String FlatNo;
    private String Needs;
    private String Status;

    public Complaints() {
    }

    public Complaints(String phone, String flatNo, String needs, String status) {
        Phone = phone;
        FlatNo = flatNo;
        Needs = needs;
        Status = status;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFlatNo() {
        return FlatNo;
    }

    public void setFlatNo(String flatNo) {
        FlatNo = flatNo;
    }

    public String getNeeds() {
        return Needs;
    }

    public void setNeeds(String needs) {
        Needs = needs;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
