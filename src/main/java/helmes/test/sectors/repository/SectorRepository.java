package helmes.test.sectors.repository;

import helmes.test.sectors.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Integer> {
}
