package cn.pzc.service;

import cn.pzc.dao.IUserInfoDAO;
import cn.pzc.entity.UserInfo;

/**
 * Created by mi on 18-10-10.
 */
public class UserInfoServiceImpl implements IUserInfoService{
    private IUserInfoDAO dao;

    public void add(UserInfo info) {
        dao.add(info);
    }
    public IUserInfoDAO getDao() {
        return dao;
    }
    public void setDao(IUserInfoDAO dao) {
        this.dao = dao;
    }
}