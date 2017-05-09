package pl.sternik.mb.linklinks.web.controlers;


import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.mb.linklinks.entities.Game;
import pl.sternik.mb.linklinks.entities.LanguageVersion;
import pl.sternik.mb.linklinks.entities.PlatfromVersion;
import pl.sternik.mb.linklinks.entities.Status;
import pl.sternik.mb.linklinks.services.CollectionServiceGames;
import pl.sternik.mb.linklinks.services.NotificationService;

@Controller
public class GamesController {
	
	@Autowired
	private CollectionServiceGames collectionService;
	
	@Autowired
    private NotificationService notifyService;
	
	@ModelAttribute("statusAll")
	public List<Status> populateStatusy() {
		return Arrays.asList(Status.ALL);		
	}
	
	@ModelAttribute("languageVersionAll")
	public List<LanguageVersion> populateLanguages() {
		return Arrays.asList(LanguageVersion.ALL);		
	}
	
	@ModelAttribute("platformVersionAll")
	public List<PlatfromVersion> populatePlatforms() {
		return Arrays.asList(PlatfromVersion.ALL);		
	}
	
	@RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Game> result;
        result = collectionService.findById(id);
        if (result.isPresent()) {                                                
        	Game game = result.get();
            model.addAttribute("game", game);
            return "game";
        } else {
            notifyService.addErrorMessage("Cannot find game #" + id);
            model.clear();
            return "redirect:/games";
        }
    }
	 
	 
	 @RequestMapping(value = "/games/{id}/json", produces = "application/json", method = RequestMethod.GET)
	    @ResponseBody
	    public ResponseEntity<Game> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
	        Optional<Game> result;
	        result = collectionService.findById(id);
	        if (result.isPresent()) {
	        	Game game = result.get();
	            return new ResponseEntity<Game>(game, HttpStatus.OK);
	        } else {
	            notifyService.addErrorMessage("Cannot find game #" + id);
	            model.clear();
	            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 
	    @RequestMapping(value = "/games", params = { "save" }, method = RequestMethod.POST)
	    public String saveGame(Game game, BindingResult bindingResult, ModelMap model) {

	        if (bindingResult.hasErrors()) {
	            notifyService.addErrorMessage("Please fill the form correctly!");
	            model.addAttribute("MyMessages", notifyService.getNotificationMessages());
	            return "game";
	        }
	        Optional<Game> result = collectionService.edit(game);
	        if (result.isPresent())
	            notifyService.addInfoMessage("Save succeded");
	        else
	            notifyService.addErrorMessage("Save failed");
	        model.clear();
	        return "redirect:/games";
	    }	    
	    

	    @RequestMapping(value = "/games", params = { "create" }, method = RequestMethod.POST)
	    public String createGame(Game game, BindingResult bindingResult, ModelMap model) {
	        if (bindingResult.hasErrors()) {
	            notifyService.addErrorMessage("Please fill the form correctly!");
	            model.addAttribute("MyMessages", notifyService.getNotificationMessages());
	            return "game";
	        }
	        collectionService.create(game);
	        model.clear();
	        notifyService.addInfoMessage("Save succeded");
	        return "redirect:/games";
	    }
	    

	    @RequestMapping(value = "/games", params = { "remove" }, method = RequestMethod.POST)
	    public String removeRow(final Game game, final BindingResult bindingResult, final HttpServletRequest req) {
	        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
	        Optional<Boolean> result = collectionService.deleteById(rowId.longValue());
	        return "redirect:/games";
	    }
	    
	    
	    @RequestMapping(value = "/games", params = { "buy" }, method = RequestMethod.POST)
	    public String buyGame(final Game game, final BindingResult bindingResult, final HttpServletRequest req) {
	        final Integer rowId = Integer.valueOf(req.getParameter("buy"));
	        Optional<Boolean> result = collectionService.deleteById(rowId.longValue());
	        notifyService.addInfoMessage("Withdrawing money from your account");
	        return "redirect:/games";
	    }


	    @RequestMapping(value = "/games/create", method = RequestMethod.GET)
	    public String showMainPages(final Game game) {
	        game.setDateOfAcquisition(Calendar.getInstance().getTime());
	        return "game";
	    }
	    
	    
	    
}
