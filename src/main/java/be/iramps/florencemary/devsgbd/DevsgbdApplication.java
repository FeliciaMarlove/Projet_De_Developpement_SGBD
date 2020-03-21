package be.iramps.florencemary.devsgbd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DevsgbdApplication {
/* Tutoriel Data population with JSON files https://www.baeldung.com/spring-data-jpa-repository-populators */

//	/**
//	 * Bean qui popule la base de données avec les fichiers JSON dans le dossier "resources"
//	 * @return factory
//	 */
//	@Bean
//	public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
//		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
//		factory.setResources(new Resource[]{new ClassPathResource("populate-data.json")});
//		return factory;
//	}

//	/**
//	 * Bean qui permet de gérer la (dé)sérialization des dates au format JSON
//	 * @param builder
//	 * @return
//	 */
//	@Bean
//	@Primary
//	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//		ObjectMapper objectMapper = builder.build();
//		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//		return objectMapper;
//	}

	public static void main(String[] args) {
		SpringApplication.run(DevsgbdApplication.class, args);
	}

}
