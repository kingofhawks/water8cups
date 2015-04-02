package com.bigyellow.hm.rsconfig;

import com.bigyellow.hm.rs.AppUserResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.bigyellow.hm.rs.DrinkRecordResource;
import com.bigyellow.hm.rs.EatRecordResource;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-10-22
 */
public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication () {
    	
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(DrinkRecordResource.class);
        register(EatRecordResource.class);
        register(AppUserResource.class);

    }
}
