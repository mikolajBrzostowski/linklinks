package pl.sternik.mb.linklinks.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.repositories.GameAlreadyExistsException;
import pl.sternik.mb.linklinks.repositories.GamesRepository;
import pl.sternik.mb.linklinks.repositories.NoSuchGameException;


@Service
@Qualifier("tablica")
public class KlaserServiceGamesImpl implements KlaserServiceGames {

    @Autowired
    @Qualifier("tablica")
    private GamesRepository database;

    @Override
    public List<Game> findAll() {
        return database.findAll();
    }

    @Override
    public List<Game> findAllToSell() {
        return database.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        try {
            return Optional.of(database.readById(id));
        } catch (NoSuchGameException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Game> create(Game game) {
        try {
            return Optional.of(database.create(game));
        } catch (GameAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Game> edit(Game game) {
        try {
            return Optional.of(database.update(game));
        } catch (NoSuchGameException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            database.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchGameException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Game> findLatest3() {
        return Collections.emptyList();
    }

}
