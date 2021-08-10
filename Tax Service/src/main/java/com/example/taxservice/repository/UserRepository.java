package com.example.taxservice.repository;

import com.example.taxservice.entity.User;
import com.example.taxservice.entity.enums.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NonNull String username);
    Optional<User> findByEmail(@NonNull String email);
    Optional<User> findById(Long id);

    List<User> findByRole(Role role);
}
