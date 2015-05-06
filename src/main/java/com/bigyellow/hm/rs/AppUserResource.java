package com.bigyellow.hm.rs;

import com.bigyellow.hm.common.CommonUtil;
import com.bigyellow.hm.common.Constants;
import com.bigyellow.hm.common.DateUtil;
import com.bigyellow.hm.common.ValidateResult;
import com.bigyellow.hm.dao.AppUserDao;
import com.bigyellow.hm.dao.DrinkRecordDao;
import com.bigyellow.hm.dao.GenericDaoException;
import com.bigyellow.hm.entity.AppUser;
import com.bigyellow.hm.entity.DrinkRecord;
import com.bigyellow.hm.entity.HistoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-10-22
 */

@Path("/appuser/")
public class AppUserResource {

	public static final Logger logger = LoggerFactory
			.getLogger(AppUserResource.class);
	public static final int PAGE_SIZE = 15;

	@Autowired
    AppUserDao dao;

	@Autowired(required = true)
	private MessageSource messageSource;

	public AppUserResource() {

	}


    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<AppUser> getAppUsers() {
        logger.debug("getAppUsers now**** ");
        List<AppUser> result = this.dao.getAllUsers();
        return result;
    }

    @Path("/users/{fid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AppUser getAppUserById(@PathParam("fid") String fid) {
        logger.debug("getAppUser now**** "+fid);
//        AppUser result = this.dao.getUserById(Integer.valueOf(fid));
        AppUser result = this.dao.getUserById2(Integer.valueOf(fid));

        this.dao.updateCheckRecord();
        return result;
    }


    @Path("/user/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ValidateResult addAppUser(@QueryParam("fuser") String fuser, @QueryParam("fphone") String fphone) {
        logger.info("addAppUser for fuser: " + fuser + ", fphone : "+ fphone );
        ValidateResult result = new ValidateResult();
        try {
            AppUser user = new AppUser();
            user.setFid(this.getAppUsers().size()+1);
            user.setFuser(fuser);
            user.setFphone(fphone);
            logger.info("save to database : " + user.toString());
            this.dao.add(user);
            result.setObj(user);
        } catch (GenericDaoException e) {
            logger.error(e.getErrorMsg() + " : " + e.getMessage());
            result.resolveErrorMsg(e.getErrorMsg());
        }

        return result;
    }
	

}
