package de.trust_nickol.batch_processing_jdbc_fragment.autoconfig;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

import de.trust_nickol.batch_processing_jdbc_fragment.InsertAllImpl;

/**
 * Auto-Configuration for activating the fragment insertAllImpl.
 *
 * @author anickol
 */
public class AutoConfiguration {
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(value = "de.trust_nickol.batch_processing_jdbc_fragment.enabled", matchIfMissing = true, havingValue = "true")
	public <T> InsertAllImpl<T> insertAllImpl(DataSource dataSource, RelationalMappingContext context) {
		return new InsertAllImpl<T>(dataSource, context);
	}
}