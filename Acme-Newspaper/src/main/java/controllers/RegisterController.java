package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.ActorForm;


@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController{
	
	//SERVICES -------------------------------------------------------------------
	
	@Autowired
	private UserService	userService;
		
	//CONSTRUCTORS ---------------------------------------------------------------
	
	public RegisterController() {
		super();
	}

	// LISTING -----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser(){
		ModelAndView result;
		Collection<User> users;
		String a = "USER";
		
		users = userService.findAll();
		
		result = new ModelAndView("register/listUsers");
		result.addObject("users",users);
		result.addObject("RequestURI", "register/list.do");
		result.addObject("actor", a);
		
		return result;
	}
	
	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		ModelAndView result;
		
		ActorForm userForm;
		userForm = new ActorForm();
		
		result = this.createEditModelAndView(userForm);

		return result;
	}
	
	// Edit -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int userId) {
		
		ModelAndView result;
		
		User user = userService.findOne(userId);
		ActorForm userForm = userService.construct(user);
		result = createEditModelAndView(userForm);

		return result;
	}

	
	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm userForm, final BindingResult binding) {
		ModelAndView result;
		
		User user;
		
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String pass = encoder.encodePassword(userForm.getUserAccount().getPassword(),null);
		userForm.getUserAccount().setPassword(pass);
		
		user = userService.reconstruct(userForm, binding);
		
		if(userForm.getTermsAndConditions() == false){
			result = this.createEditModelAndView(userForm, "register.terms.error");
		}else if(userForm.getAdultContent() == false){
			result = this.createEditModelAndView(userForm, "register.adultContent.error");
		}else{
		
			if (binding.hasErrors()) {
				System.out.println(binding.getFieldErrors());
				result = this.createEditModelAndView(userForm);
			} else
				try {
					userService.save(user);
					String ok = "OK";
					result = new ModelAndView("redirect:/");
					result.addObject("success", ok);
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(userForm, "register.commit.error");
				}
		}
		return result;
	}
	
	
	// Ancillary methods -----------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm user) {
		ModelAndView result;
		result = this.createEditModelAndView(user, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final ActorForm userForm, final String message) {
		ModelAndView result = new ModelAndView();
		
		result = new ModelAndView("register/edit");
		
		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}
	
}