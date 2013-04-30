/**
 * 
 */
package com.task.springsec.springsec.ui;

import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.GenericInitiator;

import com.task.springsec.SecurityUtil;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
public class AccessDeniedExInit extends GenericInitiator {

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		if(SecurityUtil.isNoneGranted("ROLE_EDITOR")){
			throw new AccessDeniedException("this is a test of AccessDeniedException!");	
		}
	}
}
