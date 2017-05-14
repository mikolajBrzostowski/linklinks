package pl.sternik.mb.linklinks.repositories.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.repositories.GameAlreadyExistsException;
import pl.sternik.mb.linklinks.repositories.NoSuchGameException;

@Repository
public interface GamesRepository extends JpaRepository<Game, Long> {
	public Game findByCatalogNumber(Long id);
	
}
