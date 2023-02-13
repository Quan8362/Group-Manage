package vti.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vti.com.vn.entity.Group;

@Repository
public interface IGroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {

}
