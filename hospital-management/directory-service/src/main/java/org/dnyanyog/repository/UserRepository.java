package org.dnyanyog.repository;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

  List<Users> findByMobileNumber(String mobileNumber);

  Optional<Users> findByMobileNumberAndPassword(String mobileNumber, String password);

  Optional<Users> findByUserId(Long userId);
}
