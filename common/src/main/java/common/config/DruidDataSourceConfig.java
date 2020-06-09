package common.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public abstract class DruidDataSourceConfig {

	public abstract DataSource getDataSource();

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "druid.datasource")
	public DataSource druidDataSource() {
		return this.getDataSource();
	}
}
