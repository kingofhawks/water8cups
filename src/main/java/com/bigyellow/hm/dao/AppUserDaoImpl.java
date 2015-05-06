package com.bigyellow.hm.dao;

import com.bigyellow.hm.entity.AppUser;
import com.bigyellow.hm.entity.CheckRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
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

    @Override
    public AppUser getUserById2(int id) {
        StoredProcedureQuery storedProcedure = this.getEm().createStoredProcedureQuery("APP_PROC_CheckRecordQuery", CheckRecord.class);
// set parameters
        storedProcedure.registerStoredProcedureParameter("FTYPE", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("databaseName", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("AppUser", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("Status", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("Billtype", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("Billno", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("BeginDate", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("EndDate", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("FrecordID", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("IsDetail", Integer.class, ParameterMode.IN);
        storedProcedure.setParameter("FTYPE", "1");
        storedProcedure.setParameter("databaseName","");
        storedProcedure.setParameter("AppUser", "张一");
        storedProcedure.setParameter("Status",0);
        storedProcedure.setParameter("Billtype", "");
        storedProcedure.setParameter("Billno","");
        storedProcedure.setParameter("BeginDate", "");
        storedProcedure.setParameter("EndDate","");
        storedProcedure.setParameter("FrecordID", 0);
        storedProcedure.setParameter("IsDetail",0);
// execute SP
        storedProcedure.execute();
// get result


//        String BillName = (String)storedProcedure.getOutputParameterValue("BillName");
        List resultList = storedProcedure.getResultList();
        System.out.println(resultList.size());
        for (Object result: resultList){
            System.out.print("result***********************"+result);
        }
//        Object result = storedProcedure.getFirstResult();
//        System.out.println("result***********************"+result);
        return null;
    }

    @Override
    public void updateCheckRecord() {
        //To change body of implemented methods use File | Settings | File Templates.
        StoredProcedureQuery storedProcedure = this.getEm().createStoredProcedureQuery("APP_PROC_CheckRecordUpdate");
// set parameters
        storedProcedure.registerStoredProcedureParameter("FTYPE", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("AppUser", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("FrecordID", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("CheckLevle", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("Checkstatus", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("CheckNote", String.class, ParameterMode.IN);
//        storedProcedure.registerStoredProcedureParameter("MSG", String.class, ParameterMode.OUT);

        storedProcedure.setParameter("FTYPE", "");
        storedProcedure.setParameter("AppUser", "张一");
        storedProcedure.setParameter("FrecordID",2);
        storedProcedure.setParameter("CheckLevle", 1);
        storedProcedure.setParameter("Checkstatus","同意");
        storedProcedure.setParameter("CheckNote", "");


// execute SP
        storedProcedure.execute();
//        String msg = (String)storedProcedure.getOutputParameterValue("MSG");
        String msg = (String)storedProcedure.getSingleResult();
        System.out.println(msg);
    }
}
