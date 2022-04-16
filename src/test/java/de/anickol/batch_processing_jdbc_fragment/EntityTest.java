package de.anickol.batch_processing_jdbc_fragment;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.dao.DuplicateKeyException;

import de.anickol.batch_processing_jdbc_fragment.model.Entity;
import de.anickol.batch_processing_jdbc_fragment.repositry.EntityRepository;

/**
 * Tests for the EntityRepository.
 *
 * @author anickol
 */
@DataJdbcTest
public class EntityTest {
	@Autowired
	private EntityRepository repository;

	@Test
	public void testDuplicateKeyExcepation() {
		long id = 1L;
		Entity entity1 = createEntity(id);
		Entity entity2 = createEntity(id);
		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			repository.insertAll(Arrays.asList(entity1, entity2));
		});
	}

	@Test
	public void testInsertList() {
		repository.deleteAll();

		List<Entity> list = LongStream.range(0, 4).mapToObj(this::createEntity).collect(Collectors.toList());

		assertArrayEquals(repository.insertAll(list), new int[] { 1, 1, 1, 1 });
		assertEquals(4, repository.count());

		List<Entity> result = new ArrayList<>();
		repository.findAll().forEach(result::add);
		result.sort(comparing(Entity::getId));
		assertEquals(result, list);

		list = LongStream.range(4, 10).mapToObj(this::createEntity).collect(Collectors.toList());
		repository.insertAll(list);
		assertEquals(10, repository.count());
	}

	private Entity createEntity(long i) {
		Entity entity = new Entity();
		entity.setId(i);
		String data = UUID.randomUUID().toString();
		entity.setField(data);
		return entity;
	}
}
