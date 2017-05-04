package pl.sternik.mb.linklinks.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.entities.LanguageVersion;
import pl.sternik.mb.linklinks.entities.PlatfromVersion;
import pl.sternik.mb.linklinks.entities.Status;


@Service
@Qualifier("lista")
public class GamesRepositoryJ8Impl implements GamesRepository {

    private List<Game> games = new ArrayList<Game>() {
        {
            add(Game.produceGame(1L, "game1", 100L, "First game", new Date(), LanguageVersion.POLISH, PlatfromVersion.PS3, Status.NEW));
            add(Game.produceGame(2L, "game2", 110L, "Second game", new Date(), LanguageVersion.ENGLISH, PlatfromVersion.PS3, Status.TOSELL));
            add(Game.produceGame(3L, "game3", 120L, "Third game", new Date(), LanguageVersion.GERMAN, PlatfromVersion.PS3, Status.DUPLICATE));
            add(Game.produceGame(4L, "game4", 130L, "Forth game", new Date(), LanguageVersion.RUSSIAN, PlatfromVersion.PS3, Status.TOSELL));
            add(Game.produceGame(5L, "game5", 140L, "Fifth game", new Date(), LanguageVersion.ENGLISH, PlatfromVersion.PS3, Status.NEW));
            add(Game.produceGame(6L, "game6", 150L, "Sixth game", new Date(), LanguageVersion.POLISH, PlatfromVersion.PS3, Status.NEW));
        }
    };

    @Override
    public List<Game> findAll() {
        return this.games;
    }

    @Override
    public Game readById(Long id) throws NoSuchGameException {
        return this.games.stream().filter(p -> Objects.equals(p.getCatalogNumber(), id)).findFirst()
                .orElseThrow(NoSuchGameException::new);
    }

    @Override
    public Game create(Game game) {
        if (!games.isEmpty()) {
            game.setCatalogNumber(
                    this.games.stream().mapToLong(p -> p.getCatalogNumber()).max().getAsLong() + 1);
        } else {
            game.setCatalogNumber(1L);
        }
        this.games.add(game);
        return game;
    }

    @Override
    public Game update(Game game) throws NoSuchGameException {
        for (int i = 0; i < this.games.size(); i++) {
            if (Objects.equals(this.games.get(i).getCatalogNumber(), game.getCatalogNumber())) {
                this.games.set(i, game);
                return game;
            }
        }
        throw new NoSuchGameException("There is no such game: " + game.getCatalogNumber());
    }

    @Override
    public void deleteById(Long id) throws NoSuchGameException {
        for (int i = 0; i < this.games.size(); i++) {
            if (Objects.equals(this.games.get(i).getCatalogNumber(), id)) {
                this.games.remove(i);
            }
        }
        throw new NoSuchGameException("There is no such game: " + id);
    }

}
