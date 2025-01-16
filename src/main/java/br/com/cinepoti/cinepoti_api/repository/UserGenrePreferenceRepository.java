package br.com.cinepoti.cinepoti_api.repository;

import br.com.cinepoti.cinepoti_api.model.UserGenrePreference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGenrePreferenceRepository extends JpaRepository<UserGenrePreference, Long> {
  @Query("SELECT * FROM CP_USER_GENRE_PREFERENCE WHERE user_id = :userID")
  List<UserGenrePreference> findByUserId(Long userId);
}
