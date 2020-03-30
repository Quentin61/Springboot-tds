package s4.spring.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s4.spring.td5.entities.Script;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Integer> {
    List<Script> findAll();
    Script findById(int id);
    List<Script> findByTitle(String title);

    @Query("SELECT o FROM Script o where o.title like %?1%")
    List<Script> findByTitleSearch(String title);

    @Query("SELECT o FROM Script o where o.description like %?1%")
    List<Script> findByDescriptionSearch(String description);

    @Query("SELECT o FROM Script o where o.content like %?1%")
    List<Script> findByContentSearch(String content);

    @Query("SELECT o FROM Script o where o.title like %?1% or o.description like %?1% or o.content like %?1%")
    List<Script> findByAllSearch(String something);
}
