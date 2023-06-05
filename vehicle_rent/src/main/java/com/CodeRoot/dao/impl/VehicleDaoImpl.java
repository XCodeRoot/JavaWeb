package com.CodeRoot.dao.impl;

import com.CodeRoot.dao.VehicleDao;
import com.CodeRoot.pojo.Vehicle;

import java.awt.print.Book;
import java.util.List;


public  class VehicleDaoImpl extends BaseDao implements VehicleDao {

    @Override
    public int addVehicle(Vehicle vehicle) {
        String sql="insert into vehicle(vehicle_id,vehicle_type,vehicle_model,vehicle_state,vehicle_rent," +
                "vehicle_number,vehicle_img,shop_id)" +
                "values(?,?,?,?,?,?,?,?);";
        return update(sql,
                vehicle.getVehicle_id(),vehicle.getVehicle_type(),vehicle.getVehicle_model(),vehicle.getVehicle_state(),
                vehicle.getVehicle_rent(), vehicle.getVehicle_number(),vehicle.getVehicle_img(),vehicle.getShop_id());
    }

    @Override
    public int deleteVehicleById(String id) {
        String sql="delete from vehicle where vehicle_id=?;";
        return update(sql,id);
    }

    @Override
    public int updateVehicle(Vehicle vehicle) {
        String sql="update vehicle set vehicle_type=?,vehicle_model=?,vehicle_state=?,vehicle_rent=?," +
                "vehicle_number=?,vehicle_img=?,shop_id=? where vehicle_id=?;";
        return update(sql,vehicle.getVehicle_type(),vehicle.getVehicle_model(),vehicle.getVehicle_state(),vehicle.getVehicle_rent(),
                vehicle.getVehicle_number(),vehicle.getVehicle_img(),vehicle.getShop_id(),vehicle.getVehicle_id());
    }

    @Override
    public Vehicle queryVehicleById(String id) {
        String sql="select vehicle_id,vehicle_type,vehicle_model,vehicle_state,vehicle_rent," +
                " vehicle_number,vehicle_img,shop_id from vehicle where vehicle_id=?";
        return queryForOne(Vehicle.class,sql,id);
    }

    @Override
    public List<Vehicle> queryVehicles() {
        String sql="select vehicle_id,vehicle_type,vehicle_model,vehicle_state,vehicle_rent," +
                "vehicle_number,vehicle_img,shop_id from vehicle;";
        return queryForList(Vehicle.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from vehicle  ;";
        Number count= (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Vehicle> queryForPageItems(int begin, int pageSize) {
        String sql="select  vehicle_id,vehicle_type,vehicle_model,vehicle_state,vehicle_rent," +
                "vehicle_number,vehicle_img,shop_id from vehicle limit ?,?;";
        return queryForList(Vehicle.class,sql,begin,pageSize);
    }
}
