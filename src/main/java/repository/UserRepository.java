package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	// Find user by email
    Optional<User> findByEmail(String email);

    // Find user by email and role
    Optional<User> findByEmailAndRolesContaining(String email, String role);

    // Custom query for email and password (use hashed passwords)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserForLogin(@Param("email") String email);
	
}
