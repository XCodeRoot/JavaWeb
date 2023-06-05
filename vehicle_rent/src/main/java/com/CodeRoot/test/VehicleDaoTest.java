package com.CodeRoot.test;

import com.CodeRoot.dao.VehicleDao;
import com.CodeRoot.dao.impl.VehicleDaoImpl;
import com.CodeRoot.pojo.Vehicle;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VehicleDaoTest {

    VehicleDao vehicleDao=new VehicleDaoImpl();

    @Test
    public void addVehicle() {
        vehicleDao.addVehicle(new Vehicle(
                "C998","五座","宝马5系","未租",
                new BigDecimal(55555),"赣E 55555","img_Path","K1"));
    }

    @Test
    public void deleteVehicleById() {
    }

    @Test
    public void updateVehicle() {
    }

    @Test
    public void queryVehicleById() {
    }

    @Test
    public void queryVehicles() {
        for (Vehicle vehicle : vehicleDao.queryVehicles()) {
            System.out.println(vehicle);
        }
    }
}