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
import pl.sternik.mb.linklinks.entities.Status;
import pl.sternik.mb.linklinks.services.KlaserServiceGames;
import pl.sternik.mb.linklinks.services.NotificationService;

@Controller
public class GamesController {
	
	@Autowired
	private KlaserServiceGames klaserService;
	
	@Autowired
    private NotificationService notifyService;
	
	@ModelAttribute("statsyAll")
	public List<Status> populateStatusy() {
		return Arrays.asList(Status.ALL);		
	}
	
	 @GetMapping(value = "/games/{id}")
	    public String view(@PathVariable("id") Long id, final ModelMap model) {
	        Optional<Game> result;
	        result = klaserService.findById(id);
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
	        result = klaserService.findById(id);
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
	    public String saveMoneta(Game game, BindingResult bindingResult, ModelMap model) {

	        if (bindingResult.hasErrors()) {
	            notifyService.addErrorMessage("Please fill the form correctly!");
	            return "game";
	        }
	        Optional<Game> result = klaserService.edit(game);
	        if (result.isPresent())
	            notifyService.addInfoMessage("Zapis udany");
	        else
	            notifyService.addErrorMessage("Zapis NIE udany");
	        model.clear();
	        return "redirect:/monety";
	    }
	    
	    

	    @RequestMapping(value = "/games", params = { "create" }, method = RequestMethod.POST)
	    public String createMoneta(Game game, BindingResult bindingResult, ModelMap model) {
	        if (bindingResult.hasErrors()) {
	            notifyService.addErrorMessage("Please fill the form correctly!");
	            return "moneta";
	        }
	        klaserService.create(game);
	        model.clear();
	        notifyService.addInfoMessage("Zapis nowej udany");
	        return "redirect:/games";
	    }


	    

	    @RequestMapping(value = "/games", params = { "remove" }, method = RequestMethod.POST)
	    public String removeRow(final Game game, final BindingResult bindingResult, final HttpServletRequest req) {
	        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
	        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
	        return "redirect:/games";
	    }


	    @RequestMapping(value = "/games/create", method = RequestMethod.GET)
	    public String showMainPages(final Game game) {
	        // Ustawiamy date nowej monety, na dole strony do dodania
	        game.setDateOfAcquisition(Calendar.getInstance().getTime());
	        return "moneta";
	    }
	    
	    
	    
}
