package de.trust_nickol.batch_processing_jdbc_fragment;

import java.util.Iterator;

import javax.sql.DataSource;

import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.data.util.Streamable;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

/**
 * JDBC fragment which implements the {@link InsertAll} interface using batch inserts through
 * {@link org.springframework.jdbc.core.simple.SimpleJdbcInsert#SimpleJdbcInsert(javax.sql.DataSource)}.
 *
 * @param <T>
 * 		type of entity to be saved
 * @author anickol
 */
public class InsertAllImpl<T> implements InsertAll<T> {
	private final DataSource dataSource;
	private final RelationalMappingContext context;
	private final IdentifierProcessing identifierProcessing = IdentifierProcessing.NONE;

	public InsertAllImpl(DataSource dataSource, RelationalMappingContext context) {
		this.dataSource = dataSource;
		this.context = context;
	}

	@Transactional
	@Override
	public int[] insertAll(Iterable<T> entities) {
		if (entities == null) {
			return null;
		}

		Iterator<T> iterator = entities.iterator();
		if (!iterator.hasNext()) {
			return new int[0];
		}

		BeanPropertySqlParameterSource[] batch = Streamable.of(entities).stream() //
				.map(BeanPropertySqlParameterSource::new) //
				.toArray(BeanPropertySqlParameterSource[]::new);

		SimpleJdbcInsert simpleJdbcInsert = createSimpleJdbcInsert(iterator.next().getClass());

		return simpleJdbcInsert.executeBatch(batch);
	}

	private SimpleJdbcInsert createSimpleJdbcInsert(Class<?> type) {
		RelationalPersistentEntity<?> entity = context.getRequiredPersistentEntity(type);
		String tableName = entity.getTableName().toSql(identifierProcessing);
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(tableName);
		if (entity.hasIdProperty()) {
			simpleJdbcInsert = simpleJdbcInsert.usingGeneratedKeyColumns(entity.getIdColumn().toSql(identifierProcessing));
		}
		return simpleJdbcInsert;
	}
}