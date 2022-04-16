package de.anickol.batch_processing_jdbc_fragment;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import de.anickol.batch_processing_jdbc_fragment.model.Entity;
import de.anickol.batch_processing_jdbc_fragment.repositry.EntityRepository;

/**
 * Tests various basic features of the {@code InsertAll}-fragment.
 *
 * @author anickol
 */
@DataJdbcTest
public class InsertAllTest {
	@Autowired
	private EntityRepository repository;

	@Test
	public void testNull() {
		Assertions.assertNull(repository.insertAll(null));
	}

	@Test
	public void testEmptyList() {
		assertArrayEquals(repository.insertAll(Collections.emptyList()), new int[0]);
	}

	@Test
	public void testFindByField() {
		Entity entity = createEntity();
		repository.insertAll(Collections.singletonList(entity));
		Assertions.assertTrue(repository.findByField(entity.getField()).isPresent());
	}

	private Entity createEntity() {
		Entity entity = new Entity();
		entity.setId(1L);
		String data = UUID.randomUUID().toString();
		entity.setField(data);
		return entity;
	}
}
