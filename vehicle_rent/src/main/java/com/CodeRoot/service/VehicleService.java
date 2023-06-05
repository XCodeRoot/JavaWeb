package com.CodeRoot.service;

import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;

import java.awt.print.Book;
import java.util.List;

public interface VehicleService {

    public void addVehicle(Vehicle vehicle);

    public void deleteVehicleById(String id);

    public void updateVehicle(Vehicle vehicle);

    public Vehicle queryVehicleById(String id);

    public List<Vehicle> queryVehicles();

    public Page<Vehicle> page(int pageNo, int pageSize);

}
