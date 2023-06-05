package com.CodeRoot.dao;

import com.CodeRoot.pojo.Hirer;
import com.CodeRoot.pojo.Vehicle;

import java.util.List;

public interface HirerDao {

    public int addHirer(Hirer hirer);

    public int deleteHirerById(String id);

    public int updateHirer(Hirer hirer);

    public Hirer queryHirerById(String id);

    public List<Hirer> queryHirers();

    public Integer queryForPageTotalCount();

    public List<Hirer> queryForPageItems(int begin, int pageSize);


}
