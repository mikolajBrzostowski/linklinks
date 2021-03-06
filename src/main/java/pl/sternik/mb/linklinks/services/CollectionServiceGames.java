package pl.sternik.mb.linklinks.services;

import java.util.List;
import java.util.Optional;
import pl.sternik.mb.linklinks.entities.Game;


public interface CollectionServiceGames {
    List<Game> findAll();

    List<Game> findAllToSell();

    Optional<Game> findById(Long id);

    Optional<Game> create(Game game);

    Optional<Game> edit(Game game);

    Optional<Boolean> deleteById(Long id);

    List<Game> findLatest3();
    
    List<Game> findAllDuplicates();
}