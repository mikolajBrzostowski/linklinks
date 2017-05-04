package pl.sternik.mb.linklinks.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.entities.Status;
import pl.sternik.mb.linklinks.repositories.GameAlreadyExistsException;
import pl.sternik.mb.linklinks.repositories.GamesRepository;
import pl.sternik.mb.linklinks.repositories.NoSuchGameException;


@Service
@Primary
public class CollectionServiceGameJ8Impl implements CollectionServiceGames {

    @Autowired
    @Qualifier("lista")
    private GamesRepository games;

    @Override
    public List<Game> findAll() {
        return games.findAll();
    }

    @Override
    public List<Game> findLatest3() {
        return games.findAll().stream().sorted((a, b) -> b.getDateOfAcquisition().compareTo(a.getDateOfAcquisition())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Game> findById(Long id) {
        try {
            return Optional.of(games.readById(id));
        } catch (NoSuchGameException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Game> create(Game game) {
        try {
            return Optional.of(games.create(game));
        } catch (GameAlreadyExistsException e) {
            try {
                return Optional.of(games.readById(game.getCatalogNumber()));
            } catch (NoSuchGameException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Game> edit(Game game) {
        try {
            return Optional.of(games.update(game));
        } catch (NoSuchGameException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            games.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchGameException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Game> findAllToSell() {
        return games.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.TOSELL))
                .collect(Collectors.toList());
    }
}
