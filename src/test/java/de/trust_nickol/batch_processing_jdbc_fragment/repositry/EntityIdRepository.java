package de.trust_nickol.batch_processing_jdbc_fragment.repositry;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.trust_nickol.batch_processing_jdbc_fragment.InsertAll;
import de.trust_nickol.batch_processing_jdbc_fragment.model.EntityId;

/**
 * Repository for tests implementing InsertAll fragment.
 *
 * @author anickol
 */
public interface EntityIdRepository extends CrudRepository<EntityId, Long>, InsertAll<EntityId> {
	Optional<EntityId> findByField(String field);
}
