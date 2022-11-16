package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
