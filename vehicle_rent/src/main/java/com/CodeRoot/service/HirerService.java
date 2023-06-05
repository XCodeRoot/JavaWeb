package com.CodeRoot.service;

import com.CodeRoot.pojo.Hirer;
import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;

import java.util.List;

public interface HirerService {

    public void addHirer(Hirer hirer);

    public void deleteHirerById(String id);

    public void updateHirer(Hirer hirer);

    public Hirer queryHirerById(String id);

    public List<Hirer> queryHirers();

    public Page<Hirer> page(int pageNo, int pageSize);

}
