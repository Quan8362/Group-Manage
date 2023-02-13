package vti.com.vn.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vti.com.vn.dto.AccountDTO;
import vti.com.vn.entity.Account;
import vti.com.vn.service.IUserService;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {
        String username = principal.getName();
        Account account = userService.getAccountByUsername(username);
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

}