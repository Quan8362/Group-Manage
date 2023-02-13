package vti.com.vn.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class AccountDTO {
    private Integer accountId;

    private String email;

    private String username;

    private String fullName;

    private String password;

    private String role;

    private LocalDate CreateDate;
}
