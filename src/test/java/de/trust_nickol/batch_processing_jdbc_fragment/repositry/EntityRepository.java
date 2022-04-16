package de.trust_nickol.batch_processing_jdbc_fragment.repositry;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.trust_nickol.batch_processing_jdbc_fragment.InsertAll;
import de.trust_nickol.batch_processing_jdbc_fragment.model.Entity;

/**
 * Repository for tests implementing InsertAll fragment.
 *
 * @author anickol
 */
public interface EntityRepository extends CrudRepository<Entity, Long>, InsertAll<Entity> {
	Optional<Entity> findByField(String field);
}
