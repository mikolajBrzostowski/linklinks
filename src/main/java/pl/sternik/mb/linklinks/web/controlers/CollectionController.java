package pl.sternik.mb.linklinks.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.entities.Status;
import pl.sternik.mb.linklinks.services.CollectionServiceGames;
import pl.sternik.mb.linklinks.services.NotificationService;


@Controller
public class CollectionController {

    @Autowired
    // @Qualifier("spring")
    private CollectionServiceGames collectionService;

    @Autowired
    private NotificationService notificationService;

    @ModelAttribute("statusAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("gamesAll")
    public List<Game> populateCoins() {
        return this.collectionService.findAll();
    }

    @ModelAttribute("gamesToSell")
    public List<Game> populateCoinsToSell() {
        return this.collectionService.findAllToSell();
    }
    
    @ModelAttribute("gamesDuplicated")
    public List<Game> populateDuplicatedCoins() {
        return this.collectionService.findAllDuplicates();
    }

    @ModelAttribute("gamesLast3")
    public List<Game> populateLast3Coins() {
        return this.collectionService.findLatest3();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "collection";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "tosell";
    }
    
    @RequestMapping("/duplicates")
    public String showDupicatesPage() {
        return "duplicates";
    }

}
