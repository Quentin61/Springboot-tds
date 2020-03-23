package s4.spring.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td5.entities.Script;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Integer> {
    List<Script> findAll();
    Script findById(int id);
    List<Script> findByTitle(String title);

}
