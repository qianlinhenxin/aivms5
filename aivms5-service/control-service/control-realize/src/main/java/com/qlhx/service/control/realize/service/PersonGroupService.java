/**
 * 
 */
package com.qlhx.service.control.realize.service;

import java.util.List;

import com.qlhx.service.control.realize.model.PersonGroup;
import com.qlhx.service.control.realize.model.PersonGroupImpl;

/**
 * @author YF20150805
 *
 */
public interface PersonGroupService {
	
	List<PersonGroupImpl> list();
	
	int insertSelective(PersonGroup record);

	PersonGroupImpl selectByIdentifier(String identifier);
}
