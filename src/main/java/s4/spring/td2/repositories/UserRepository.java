package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>
{
    List<User> findByLastname(String lastname);
    List<User> findByEmail(String email);
}
