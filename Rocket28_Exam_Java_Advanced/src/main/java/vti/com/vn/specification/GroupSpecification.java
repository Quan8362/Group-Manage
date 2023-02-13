package vti.com.vn.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vti.com.vn.entity.Group;

public class GroupSpecification {

    public static Specification<Group> buildWhere(String search) {

        if (StringUtils.isEmpty(search)) {
            return null;
        }
        search = search.trim();

        CustomSpecification name = new CustomSpecification("groupName",  search);

        return Specification.where(name);
    }
}