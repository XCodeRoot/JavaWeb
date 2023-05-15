package com.atguigu.pojo.Servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    /**
     *
     *  用来处理上传的数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //先判断上传的数据是否是多段数据  (只有是多段数据,才是文件上传的)
        if(ServletFileUpload.isMultipartContent(req)){//多段数据 返回 true

            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            //解析上传的数据,得到每一个表单项FileItem
            try {
                List<FileItem> list=servletFileUpload.parseRequest(req);

                //循环判断每一个表单项是普通类型还是文件
                for (FileItem fileItem:list) {

                    if(fileItem.isFormField()){
                        //如果是普通表单项
                        System.out.println("表单项的name属性值: "+fileItem.getFieldName()  );
                        //参数 UTF-8 解决乱码问题
                        System.out.println("表单项的value属性值: "+fileItem.getString("UTF-8")  );
                    }else{
                        //如果文件
                        System.out.println("表单项的name属性值: "+fileItem.getFieldName()  );

                        System.out.println("上传的文件名: "+fileItem.getName()  );
                        fileItem.write(new File("e:\\"+fileItem.getName()));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
