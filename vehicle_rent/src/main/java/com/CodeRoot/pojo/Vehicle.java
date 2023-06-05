package com.CodeRoot.pojo;

import java.math.BigDecimal;

public class Vehicle {
    private String vehicle_id;
    private String vehicle_type;
    private String vehicle_model;
    private String vehicle_state;
    private BigDecimal vehicle_rent;
    private String vehicle_number;
    private String vehicle_img;
    private String shop_id;

    public Vehicle() {
    }

    public Vehicle(String vehicle_id, String vehicle_type, String vehicle_model, String vehicle_state, BigDecimal vehicle_rent, String vehicle_number, String vehicle_img, String shop_id) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_model = vehicle_model;
        this.vehicle_state = vehicle_state;
        this.vehicle_rent = vehicle_rent;
        this.vehicle_number = vehicle_number;
        this.vehicle_img = vehicle_img;
        this.shop_id = shop_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_state() {
        return vehicle_state;
    }

    public void setVehicle_state(String vehicle_state) {
        this.vehicle_state = vehicle_state;
    }

    public BigDecimal getVehicle_rent() {
        return vehicle_rent;
    }

    public void setVehicle_rent(BigDecimal vehicle_rent) {
        this.vehicle_rent = vehicle_rent;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicle_img() {
        return vehicle_img;
    }

    public void setVehicle_img(String vehicle_img) {
        this.vehicle_img = vehicle_img;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id='" + vehicle_id + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", vehicle_model='" + vehicle_model + '\'' +
                ", vehicle_state='" + vehicle_state + '\'' +
                ", vehicle_rent=" + vehicle_rent +
                ", vehicle_number='" + vehicle_number + '\'' +
                ", vehicle_img='" + vehicle_img + '\'' +
                ", shop_id='" + shop_id + '\'' +
                '}';
    }
}
