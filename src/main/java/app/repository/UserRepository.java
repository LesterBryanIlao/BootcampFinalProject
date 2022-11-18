package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT  u.id, u.userName, u.firstName, u.lastName, u.passwordHash FROM User u  WHERE u.email=:email AND u.passwordHash=:passwordHash")
	public User getViaEmailAndPassword(String email, String passwordHash);
}
