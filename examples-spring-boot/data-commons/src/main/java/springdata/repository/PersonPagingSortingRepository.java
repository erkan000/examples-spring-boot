package springdata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import springdata.pojo.Person;

public interface PersonPagingSortingRepository extends PagingAndSortingRepository<Person, Integer> {    

	Page<Person> findByName(String name, Pageable pageRequest);

}
