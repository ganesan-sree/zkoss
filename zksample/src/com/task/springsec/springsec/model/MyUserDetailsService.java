/**
 * 
 */
package com.task.springsec.springsec.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * A custom UserDetailsService implementation to adapt to extended User class.<br>
 * 
 * If you have your own user credential model and don't want to change it even a bit, 
 * then instead of swapping the UserDetailService of default AuthenticationProvider, 
 * you have to swap the AuthenticationProvider itself, and you'll have to deal with 
 * password hashing and salting by your own.<br>
 * 
 * 
 * @author Ian YT Tsai (zanyking)
 *
 */
public class MyUserDetailsService implements UserDetailsService {

	private static final Map<String, MyUser> USERS = new HashMap<String,MyUser>();
	private static void add(MyUser mu){
		USERS.put(mu.getUsername(), mu);
	}
	static{
		
		add(new MyUser("rod","81dc9bdb52d04dc20036dbd8313ed055", 
				new String[]{"ROLE_USER", "ROLE_EDITOR"} ));//1234
		
		add(new MyUser("dianne","65d15fe9156f9c4bbffd98085992a44e", 
				new String[]{"ROLE_USER", "ROLE_EDITOR"} ));//emu
		
		add(new MyUser("scott","65d15fe9156f9c4bbffd98085992a44e", 
				new String[]{"ROLE_USER"} ));//emu
		
		add(new MyUser("peter","65d15fe9156f9c4bbffd98085992a44e", 
				new String[]{"ROLE_USER"} ));//emu
		
	}
	
	// must return a value or throw UsernameNotFoundException
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		MyUser user = USERS.get(username);
		if(user==null){
			System.out.println(">>> cannot find user: "+username);
			throw new UsernameNotFoundException("cannot found user: "+username);
		}
		return user;
	}


}
