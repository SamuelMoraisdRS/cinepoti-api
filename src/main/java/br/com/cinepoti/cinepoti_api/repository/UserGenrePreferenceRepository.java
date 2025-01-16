package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.UserGenrePreference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserGenrePreferenceRepository extends JpaRepository<UserGenrePreference, Long> {
  @Query("SELECT u FROM UserGenrePreference u WHERE u.user.id = :userID")
  List<UserGenrePreference> findByUserId(@Param("userID") Long userId);

}
