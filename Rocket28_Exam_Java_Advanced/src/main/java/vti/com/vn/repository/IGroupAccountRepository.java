package vti.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vti.com.vn.entity.Group;
import vti.com.vn.entity.GroupAccount;

@Repository
public interface IGroupAccountRepository extends JpaRepository<GroupAccount, Integer> {

    Integer countByGroup(Group group);

}
