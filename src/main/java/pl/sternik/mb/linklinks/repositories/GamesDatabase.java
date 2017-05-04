package pl.sternik.mb.linklinks.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.entities.LanguageVersion;
import pl.sternik.mb.linklinks.entities.PlatfromVersion;
import pl.sternik.mb.linklinks.entities.Status;


@Repository
@Qualifier("tablica")
public class GamesDatabase implements GamesRepository {

    private Game[] baza;

    public GamesDatabase() {
        baza = new Game[15];
        Game m = new Game();
        m.setCatalogNumber(0L);
        m.setMarketValue(new Long("100"));
        m.setDescription("Nowa gra");
        m.setDateOfAcquisition(new Date());
        m.setLanguageVersion(LanguageVersion.POLISH);
        m.setStatus(Status.NEW);
        m.setPlatformVersion(PlatfromVersion.PS4);   
        baza[0] = m;
        
        m = new Game();
        m.setCatalogNumber(2L);
        m.setMarketValue(new Long("110"));
        m.setDescription("Używana w bardzo dobrym stanie");
        m.setDateOfAcquisition(new Date());
        m.setLanguageVersion(LanguageVersion.ENGLISH);
        m.setStatus(Status.TOSELL);
        m.setPlatformVersion(PlatfromVersion.XBOXONE);   
        baza[2] = m;

    }

    public GamesDatabase(int rozmiarBazy) {
        baza = new Game[rozmiarBazy];
    }

    @Override
    public Game create(Game game) throws GameAlreadyExistsException {
        if (game.getCatalogNumber() != null && baza[game.getCatalogNumber().intValue()] != null) {
            if (game.getCatalogNumber().equals(baza[game.getCatalogNumber().intValue()].getCatalogNumber())) {
                throw new GameAlreadyExistsException("Game with this catalog number already exist.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = game;
                game.setCatalogNumber((long) i);
                return game;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchGameException {
        int catalogNumber = id.intValue();
        if (!checkCorectnessOfCatalogNumber(catalogNumber)) {
            throw new NoSuchGameException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[catalogNumber] = null;
    }

    @Override
    public Game update(Game game) throws NoSuchGameException {
        int catalogNumber = game.getCatalogNumber().intValue();
        if (!checkCorectnessOfCatalogNumber(catalogNumber)) {
            throw new NoSuchGameException("Nie poprawny numer katologowy");
        }

        Game g = baza[game.getCatalogNumber().intValue()];
        if (g == null) {
            throw new NoSuchGameException("Brak takiej monety.");
        } else {
            baza[game.getCatalogNumber().intValue()] = game;
        }
        return game;
    }

    @Override
    public Game readById(Long catalogNumber) throws NoSuchGameException {
        int id = catalogNumber.intValue();
        if (!checkCorectnessOfCatalogNumber(id) || isUnoccupied(id)) {
            throw new NoSuchGameException();
        }
        return baza[id];
    }

    private boolean isUnoccupied(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Game> findAll() {
        List<Game> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean checkCorectnessOfCatalogNumber(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
