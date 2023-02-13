package vti.com.vn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GroupDTO {

    private Integer groupId;

    private String groupName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private Integer countMember;

    private String fullName;
}
