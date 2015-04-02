
package com.bigyellow.hm.dao;

import java.io.Serializable;
import java.util.List;


/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-9-2
 */
public interface BaseDao<K extends Serializable, E> {

	E add(E entity) throws GenericDaoException;

	E saveOrUpdate(E entity) throws GenericDaoException;
	
	void delete(E entity) throws GenericDaoException;
	
	void delete(K id) throws GenericDaoException;
	
	void delete(List<K> ids) throws GenericDaoException;

	E findById(K id) throws GenericDaoException;
	
	Object findById(K id , Class clazz) throws GenericDaoException;

}
