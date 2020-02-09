package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.entities.Organization;

import java.util.List;

public interface OrgaRepository extends JpaRepository<Organization, Integer>
{
    List<Organization> findByDomain(String domain);
    List<Organization> findByName(String name);
    List<Organization> findAll();
    Organization findById(int id);

    @Override
    void delete(Organization organization);
}
