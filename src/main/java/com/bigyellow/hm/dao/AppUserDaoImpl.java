package com.bigyellow.hm.dao;

import com.bigyellow.hm.common.DateUtil;
import com.bigyellow.hm.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository(value = "AppUserDaoImpl")
@Transactional(readOnly = true)
public class AppUserDaoImpl extends BaseJpaDaoSupport<Integer, AppUser>
		implements AppUserDao {

	public static final Logger logger = LoggerFactory
			.getLogger(AppUserDaoImpl.class);

    @Override
    public List<AppUser> getAllUsers() {
        Query query = this.getEm().createNativeQuery(
                "SELECT * FROM REBA_tbl_APPUser", AppUser.class);
        List<AppUser> list = (List<AppUser>) query.getResultList();
        logger.info("AppUser size:"+list.size());
//        for (AppUser user: list){
//            logger.info("****user found****"+user);
//        }
        return list;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AppUser getUserById(int id) {
        Query query = this.getEm().createNativeQuery(
                "SELECT * FROM REBA_tbl_APPUser WHERE fid = :fid",AppUser.class);
        query.setParameter("fid", id);
        AppUser user = (AppUser)query.getSingleResult();

        return user;

    }


}
