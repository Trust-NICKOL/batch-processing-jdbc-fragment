package de.trust_nickol.batch_processing_jdbc_fragment;

/**
 * Interface for adding the {@code insertAll} repository fragment to a jdbc repository.
 * This fragment allows the insertion of several objects using batch operations.
 *
 * @param <T>
 * 		type of entity to be saved
 * @author anickol
 */
public interface InsertAll<T> {
	/**
	 * Inserts all entities. Works only for entities not having one-to-one or one-to-many references.
	 *
	 * @param entities
	 * 		entities to be inserted
	 * @return array of number of rows affected
	 */
	int[] insertAll(Iterable<T> entities);
}
