package com.CodeRoot.service.impl;

import com.CodeRoot.dao.HirerDao;
import com.CodeRoot.dao.impl.HirerDaoImpl;
import com.CodeRoot.pojo.Hirer;
import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;
import com.CodeRoot.service.HirerService;

import java.util.List;

public class HirerServiceImpl implements HirerService {

    HirerDao hirerDao=new HirerDaoImpl();

    @Override
    public void addHirer(Hirer hirer) {
        hirerDao.addHirer(hirer);
    }

    @Override
    public void deleteHirerById(String id) {
        hirerDao.deleteHirerById(id);
    }

    @Override
    public void updateHirer(Hirer hirer) {
        hirerDao.updateHirer(hirer);
    }

    @Override
    public Hirer queryHirerById(String id) {

        return hirerDao.queryHirerById(id);
    }

    @Override
    public List<Hirer> queryHirers() {
        return hirerDao.queryHirers();
    }

    @Override
    public Page<Hirer> page(int pageNo, int pageSize) {
        Page<Hirer> page = new Page<Hirer>();


        //设置每页数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = hirerDao.queryForPageTotalCount();

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
        List<Hirer> items = hirerDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);


        return page;
    }
}
