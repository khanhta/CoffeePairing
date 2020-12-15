package app.repository;

import app.model.Pair;
import org.springframework.data.repository.CrudRepository;

public interface PairRepository extends CrudRepository<Pair, Long> {
}
