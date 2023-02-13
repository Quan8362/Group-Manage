package vti.com.vn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`group`")
@Setter
@Getter
public class Group  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "group_name", length = 50, nullable = false, unique = true)
    private String groupName;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date CreateDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Account account;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<GroupAccount> groupAccounts;
}
