package com.CodeRoot.dao;

import com.CodeRoot.pojo.Vehicle;

import java.awt.print.Book;
import java.util.List;

public interface VehicleDao {


    public int addVehicle(Vehicle vehicle);

    public int deleteVehicleById(String id);

    public int updateVehicle(Vehicle vehicle);

    public Vehicle queryVehicleById(String id);

    public List<Vehicle> queryVehicles();

    public Integer queryForPageTotalCount();

    public List<Vehicle> queryForPageItems(int begin, int pageSize);

}
