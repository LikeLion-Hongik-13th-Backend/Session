package prac1.mutsa2.Post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prac1.mutsa2.Post.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
