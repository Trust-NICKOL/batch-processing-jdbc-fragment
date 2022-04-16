package de.anickol.batch_processing_jdbc_fragment;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import de.anickol.batch_processing_jdbc_fragment.model.EntityId;
import de.anickol.batch_processing_jdbc_fragment.repositry.EntityIdRepository;

/**
 * Tests for the {@code EntityIdRepository}.
 *
 * @author anickol
 */
@DataJdbcTest
public class EntityIdTest {
	@Autowired
	private EntityIdRepository repository;

	@Test
	public void testNoDuplicateKeyException() {
		EntityId entity1 = createEntity();
		EntityId entity2 = createEntity();

		long id = 1L;
		entity1.setId(id);
		entity2.setId(id);

		repository.insertAll(Arrays.asList(entity1, entity2));
	}

	@Test
	public void testInsertList() {
		List<EntityId> collect = IntStream.range(0, 4).mapToObj(i -> createEntity()).collect(Collectors.toList());
		assertArrayEquals(repository.insertAll(collect), new int[] { 1, 1, 1, 1 });
	}

	private EntityId createEntity() {
		EntityId entity = new EntityId();
		String data = UUID.randomUUID().toString();
		entity.setField(data);
		return entity;
	}
}
