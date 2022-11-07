package examples.springboot;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbeddableApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(EmbeddableApplication.class, args);
	}

	@Autowired
	EntityManagerFactory emf;

	@Override
	public void run(String... args) throws Exception {
		try {
			EntityManager em = emf.createEntityManager();
			nativeQuery(em, "SHOW TABLES");
			nativeQuery(em, "SHOW COLUMNS from INBOX");
			nativeQuery(em, "SHOW COLUMNS from INVOICE");
		} finally {
			//	          emf.close();
		} 
	}

	public static void nativeQuery(EntityManager em, String s) {
		System.out.printf("'%s'%n", s);
		Query query = em.createNativeQuery(s);
		List list = query.getResultList();
		for (Object o : list) {
			if (o instanceof Object[]) {
				System.out.println(Arrays.toString((Object[]) o));
			} else {
				System.out.println(o);
			}
		}
	}

}
