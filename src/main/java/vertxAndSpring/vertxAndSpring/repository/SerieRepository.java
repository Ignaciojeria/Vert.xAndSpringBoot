package vertxAndSpring.vertxAndSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vertxAndSpring.vertxAndSpring.entity.Serie;
@Repository
public interface SerieRepository extends JpaRepository<Serie,Long> {

}
