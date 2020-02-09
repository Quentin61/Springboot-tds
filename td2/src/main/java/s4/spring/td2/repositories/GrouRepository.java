package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.entities.Groupe;
import s4.spring.td2.entities.Organization;

import java.util.List;

public interface GrouRepository extends JpaRepository<Groupe,Integer> {
    List<Groupe> findByName(String name);
    List<Groupe> findByEmail(String email);
}
