package vti.com.vn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vti.com.vn.entity.Account;
import vti.com.vn.repository.IUserRepository;
import vti.com.vn.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Could not find account" + username);
        }


        return new User(username, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole()));
    }

    @Override
    public Account getAccountByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
