package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.model.Code;

public interface CodeRepository extends CrudRepository<Code, Integer> {
}