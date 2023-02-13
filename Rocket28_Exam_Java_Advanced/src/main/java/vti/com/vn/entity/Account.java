package vti.com.vn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "`account`")
@Setter
@Getter
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "email", length = 50,  nullable = false, unique = true)
    private String email;

    @Column(name = "username", length = 25,  nullable = false, unique = true)
    private String username;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "create_date")
    private LocalDate CreateDate;

    @OneToMany(mappedBy = "account")
    private List<Group> groups;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GroupAccount> groupAccounts;

}
