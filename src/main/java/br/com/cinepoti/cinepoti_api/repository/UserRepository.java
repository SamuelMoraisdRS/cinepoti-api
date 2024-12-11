package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID>{
}
