package de.anickol.batch_processing_jdbc_fragment.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * Simple entity with Id annotation.
 *
 * @author anickol
 */
@Data
public class EntityId {
	@Id
	private Long id;
	private String field;
}
