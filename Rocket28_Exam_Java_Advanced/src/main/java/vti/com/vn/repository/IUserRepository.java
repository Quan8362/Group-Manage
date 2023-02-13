package vti.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vti.com.vn.entity.Account;

@Repository
public interface IUserRepository extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);

}
