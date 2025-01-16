package br.com.cinepoti.cinepoti_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "CP_USER_GENRE_PREFERENCE")
public class UserGenrePreference {
    
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public Long getGenreId(){
      return this.genre.getId();
    }

    public void setUser(User user){
      this.user = user;
    }

    public void setGenre(Genre genre){
      this.genre = genre;
    }
}
