package com.CodeRoot.dao.impl;

import com.CodeRoot.dao.HirerDao;
import com.CodeRoot.pojo.Hirer;
import com.CodeRoot.pojo.Vehicle;

import java.util.List;

public class HirerDaoImpl extends BaseDao implements HirerDao {



    @Override
    public int addHirer(Hirer hirer) {
        String sql="insert into Hirer(hirer_id,hirer_name,hirer_birth,hirer_sex)" +
                "values(?,?,?,?);";
        return update(sql,hirer.getHirer_id(),hirer.getHirer_name(),hirer.getHirer_birth(),hirer.getHirer_sex());
    }

    @Override
    public int deleteHirerById(String id) {
        String sql="delete from Hirer where hirer_id=?;";
        return update(sql,id);
    }

    @Override
    public int updateHirer(Hirer hirer) {
        String sql="update Hirer set hirer_name=?,hirer_birth=?,hirer_sex=? where hirer_id=?;";
        return update(sql,hirer.getHirer_name(),hirer.getHirer_birth(),hirer.getHirer_sex(),hirer.getHirer_id());
    }

    @Override
    public Hirer queryHirerById(String id) {
        String sql="select hirer_id,hirer_name,hirer_birth,hirer_sex from Hirer where hirer_id=? ;";
        return queryForOne(Hirer.class,sql,id);
    }

    @Override
    public List<Hirer> queryHirers() {
        String sql="select hirer_id,hirer_name,hirer_birth,hirer_sex from Hirer  ;";
        return queryForList(Hirer.class,sql);

    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from vehicle  ;";
        Number count= (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Hirer> queryForPageItems(int begin, int pageSize) {
        String sql="select hirer_id,hirer_name,hirer_birth,hirer_sex from Hirer  limit ?,?;";
        return queryForList(Hirer.class,sql,begin,pageSize);
    }
}
