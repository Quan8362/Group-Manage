package vti.com.vn.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vti.com.vn.entity.Account;

public interface IUserService extends UserDetailsService {

    Account getAccountByUsername(String username);
}
