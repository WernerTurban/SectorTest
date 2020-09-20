package helmes.test.sectors.repository;

import helmes.test.sectors.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
