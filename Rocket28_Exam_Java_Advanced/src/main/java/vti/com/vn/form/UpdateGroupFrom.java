package vti.com.vn.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UpdateGroupFrom {

    private Integer groupId;

    @NotBlank(message = "The name mustn't be null value")
    @Length(min = 6, message = "the name's length is min 6 characters")
    @Length(max = 50, message = "the name's length is max 50 characters")
    private String groupName;
}
