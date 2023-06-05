package com.CodeRoot.pojo;

public class Hirer {

    private String hirer_id;
    private String hirer_name ;
    private String hirer_birth ;
    private String hirer_sex;

    public Hirer() {
    }

    public Hirer(String hirer_id, String hirer_name, String hirer_birth, String hirer_sex) {
        this.hirer_id = hirer_id;
        this.hirer_name = hirer_name;
        this.hirer_birth = hirer_birth;
        this.hirer_sex = hirer_sex;
    }

    public String getHirer_id() {
        return hirer_id;
    }

    public void setHirer_id(String hirer_id) {
        this.hirer_id = hirer_id;
    }

    public String getHirer_name() {
        return hirer_name;
    }

    public void setHirer_name(String hirer_name) {
        this.hirer_name = hirer_name;
    }

    public String getHirer_birth() {
        return hirer_birth;
    }

    public void setHirer_birth(String hirer_birth) {
        this.hirer_birth = hirer_birth;
    }

    public String getHirer_sex() {
        return hirer_sex;
    }

    public void setHirer_sex(String hirer_sex) {
        this.hirer_sex = hirer_sex;
    }

    @Override
    public String toString() {
        return "Hirer{" +
                "hirer_id='" + hirer_id + '\'' +
                ", hirer_name='" + hirer_name + '\'' +
                ", hirer_birth='" + hirer_birth + '\'' +
                ", hirer_sex='" + hirer_sex + '\'' +
                '}';
    }
}
