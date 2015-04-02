package com.bigyellow.hm.dao;

import com.bigyellow.hm.entity.AppUser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface AppUserDao extends BaseDao<Serializable, AppUser>{

    /**
     * 返回所有的AppUser对象
     * @return
     */
    List<AppUser> getAllUsers();

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    AppUser getUserById(int id);
	
}
