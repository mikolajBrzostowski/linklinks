package pl.sternik.mb.linklinks.repositories;

import java.util.List;
import pl.sternik.mb.linklinks.entities.Game;


public interface GamesRepository {
    Game create(Game game) throws GameAlreadyExistsException;
    Game readById(Long id) throws NoSuchGameException;
    Game update(Game game) throws NoSuchGameException;
    void deleteById(Long id) throws NoSuchGameException;
    List<Game> findAll();
}