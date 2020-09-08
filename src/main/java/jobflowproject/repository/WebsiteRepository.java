package jobflowproject.repository;

import jobflowproject.model.Website;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface WebsiteRepository extends CrudRepository<Website, Integer> {

    //takes the first parameter from method argument!
    //@Query(name="SELECT * FROM website WHERE name=?1 LIMIT 1", nativeQuery = true) //use typical SQL, instead of HSQL (Hybernate SQL)
    @Query(value="SELECT * FROM website WHERE name=?1 LIMIT 1", nativeQuery = true)
    Optional<Website> findWebsite(String name);



}


