package springdata.pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import springdata.pojo.Person;
import springdata.repository.PersonPagingSortingRepository;

public class PagebleService {
	
	@Autowired
	PersonPagingSortingRepository repo;
	
    public Page<Person> findByTransactionRequestSpec(int page) {
        
        Pageable pageWith5elements = PageRequest.of(page, 5);
        Page<Person> allProducts = repo.findAll(pageWith5elements);

        return allProducts;

    }

}
