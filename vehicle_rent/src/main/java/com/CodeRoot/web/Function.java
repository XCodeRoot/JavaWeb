package com.CodeRoot.web;

import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;
import com.CodeRoot.service.VehicleService;
import com.CodeRoot.service.impl.VehicleServiceImpl;
import com.CodeRoot.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Function extends BaseServlet{

    VehicleService vehicleService=new VehicleServiceImpl();


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数 pageNo,pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize) :page对象
        Page<Vehicle> page= vehicleService.page(pageNo,pageSize);

        page.setUrl("manager/function?action=page");


        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/vehicle_manager.jsp").forward(req,resp);
    }


    /** 获取车辆信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getVehicle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vehicle_id = req.getParameter("vehicle_id");
        Vehicle vehicle = vehicleService.queryVehicleById(vehicle_id);
        req.setAttribute("vehicle",vehicle);
        req.getRequestDispatcher("/pages/manager/vehicle_edit.jsp").forward(req,resp);
    }

    /** 保存 页面里对 车辆信息 的修改
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = WebUtils.copyParamTOBean(req.getParameterMap(), new Vehicle());
        vehicleService.updateVehicle(vehicle);
        resp.sendRedirect(req.getContextPath()+"/manager/function?action=page&pageNo="+req.getParameter("pageNo"));
    }


    /** 添加车辆信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);//这里获取到了pageNo=pageTotal
        pageNo+=1;

        //Vehicle vehicle = WebUtils.copyParamTOBean(req.getParameterMap(), new Vehicle());

        String vehicle_id=req.getParameter("vehicle_id");
        String vehicle_type=req.getParameter("vehicle_type");
        String vehicle_model=req.getParameter("vehicle_model");
        String vehicle_state=req.getParameter("vehicle_state");
        String vehicle_rent=req.getParameter("vehicle_rent");
        String vehicle_number=req.getParameter("vehicle_number");
        String vehicle_img=req.getParameter("vehicle_img");
        String shop_id=req.getParameter("shop_id");
        Vehicle vehicle=new Vehicle(vehicle_id,vehicle_type,vehicle_model,vehicle_state,new BigDecimal(vehicle_rent)
                                    ,vehicle_number,vehicle_img,shop_id );
        //调用 添加
        vehicleService.addVehicle(vehicle);
        resp.sendRedirect(req.getContextPath()+"/manager/function?action=page&pageNo="+pageNo);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vehicle_id = req.getParameter("vehicle_id");
        vehicleService.deleteVehicleById(vehicle_id);
        resp.sendRedirect(req.getContextPath()+"/manager/function?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /** 展示所有 车辆
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<Vehicle> vehicles= vehicleService.queryVehicles();
       req.setAttribute("vehicles",vehicles);
       req.getRequestDispatcher("/pages/manager/vehicle_manager.jsp").forward(req,resp);
    }
}
