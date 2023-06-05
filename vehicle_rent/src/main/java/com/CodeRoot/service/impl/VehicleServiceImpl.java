package com.CodeRoot.service.impl;

import com.CodeRoot.dao.VehicleDao;
import com.CodeRoot.dao.impl.VehicleDaoImpl;
import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;
import com.CodeRoot.service.VehicleService;

import java.awt.print.Book;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    VehicleDao vehicleDao=new VehicleDaoImpl();

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    @Override
    public void deleteVehicleById(String id) {
        vehicleDao.deleteVehicleById(id);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public Vehicle queryVehicleById(String id) {
        return vehicleDao.queryVehicleById(id);
    }

    @Override
    public List<Vehicle> queryVehicles() {
        return vehicleDao.queryVehicles();
    }

    @Override
    public Page<Vehicle> page(int pageNo, int pageSize) {
        Page<Vehicle> page = new Page<Vehicle>();


        //设置每页数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = vehicleDao.queryForPageTotalCount();

        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);


        //设置当前页码
        page.setPageNo(pageNo);// 这个set方法 因为放了 限制范围的if语句 , 所以必须放在 设置总页码之前


        //求当前页的 起始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Vehicle> items = vehicleDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);


        return page;
    }


}
