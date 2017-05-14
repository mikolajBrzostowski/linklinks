package pl.sternik.mb.linklinks.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.repositories.springdata.GamesRepository;


@Service
@Qualifier("spring-data")
public class CollectionServiceGamesJPAImpl implements CollectionServiceGames {

    @Autowired
    private GamesRepository database;

    @Override
    public List<Game> findAll() {
        List<Game> l = new ArrayList<>();
        for (Game item : database.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Game> findAllToSell() {
        List<Game> l = new ArrayList<>();
        for (Game item : database.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(database.findByCatalogNumber(id));
    }

    @Override
    public Optional<Game> create(Game game) {
        return Optional.of(database.save(game));
    }

    @Override
    public Optional<Game> edit(Game game) {
        return Optional.of(database.save(game));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        database.delete(id.longValue());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Game> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<Game> findAllDuplicates() {
		// TODO Auto-generated method stub
		return null;
	}

}
