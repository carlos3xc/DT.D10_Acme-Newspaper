/* ProfileController.java
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.ActorService;
import services.AdministratorService;
import services.UserService;
import domain.Actor;
import domain.Administrator;
import domain.Article;
import domain.User;
import forms.ActorForm;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdministratorService administratorService;

	// Info ---------------------------------------------------------------		

	@RequestMapping("/info")
	public ModelAndView info(@RequestParam(required=false) final Integer actorId) {
		ModelAndView result;
		String auth = "";
		String userOk = "";
		Actor aux = null, a=null;
		Date now = new Date();
		User user;
		Administrator administrator;
		result = new ModelAndView("profile/info");
		
		if(actorId != null)
			a = actorService.findOne(actorId);
		else
			a = actorService.findByPrincipal();

		if(LoginService.getPrincipal2() != null){
			aux = actorService.findByUserAccount(LoginService.getPrincipal());
			if(aux.equals(a)){
			  userOk = "OK";
			}
		}		
		
		for(Authority at: a.getUserAccount().getAuthorities()){
			auth = at.getAuthority();
			break;
		}
		if(auth.equals("USER")){
			user = userService.findOne(a.getId());
			Collection<Article> articles = new ArrayList<Article>();				
			for(Article art: user.getArticles()){
				if(art.getPublicationDate().before(now))
					articles.add(art);
			}
			result.addObject("actor", user);
			result.addObject("articles",articles);
			result.addObject("OK",userOk);
		}else if(auth.equals("ADMIN")){
			administrator = administratorService.findOne(a.getId());
			result.addObject("OK",userOk);
			result.addObject("actor",administrator);
		}
		
		result.addObject("requestURI", "profile/info.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int actorId) {
		ModelAndView result = null;
		User user;
		Administrator administrator;
		String auth ="";
		Actor a = actorService.findByUserAccount(LoginService.getPrincipal());
		for(Authority at: a.getUserAccount().getAuthorities()){
			auth = at.getAuthority();
			break;
		}
		
		if(auth.equals("USER")){
			user = userService.findOne(a.getId());
			result = this.createEditModelAndView(user);
		}else if(auth.equals("ADMIN")){
			administrator = administratorService.findOne(a.getId());
			result = this.createEditModelAndView(administrator);
		}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveUser")
	public ModelAndView saveUser(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		User actor;
		
		actor = userService.reconstruct(actorForm, binding);
		if (binding.hasErrors()) {
			System.out.println("Algo ha fallao: \n" + binding.getFieldErrors());
			result = this.createEditModelAndView(actor);
		} else
			try {
				userService.save(actor);
				result = new ModelAndView("redirect:info.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;
		result = this.createEditModelAndView(actor, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final Actor actor, final String message) {
		ModelAndView result;
		Authority auth = null;

		result = new ModelAndView("profile/edit");
		result.addObject("actor", actor);
		for(Authority a : actor.getUserAccount().getAuthorities()){
			auth = a;
			break;
		}

		result.addObject("authority", auth.getAuthority());
		result.addObject("message", message);

		return result;
	}
	
}