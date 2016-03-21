package com.crawler.schema.web.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByName(username);
		if(user!= null){
			String password = user.getPassword();
			
			// Additional security details
			boolean enabled = user.isActive();
			boolean accountNotExpired = user.isActive();
			boolean credentialsNotExpired = user.isActive();
			boolean accountNotLocked = user.isActive();
			
			// Populating the roles
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(Role role : user.getRoles()){
				authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
			}
			
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User(username,password,enabled,accountNotExpired, 
							credentialsNotExpired, accountNotLocked, authorities);
			return securityUser;
		}else {
			throw new UsernameNotFoundException("User cannot be found!!!");
		}
	}

}
