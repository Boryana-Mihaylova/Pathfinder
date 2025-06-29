package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryName name);
}
