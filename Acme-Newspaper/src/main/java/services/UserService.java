package services;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Article;
import domain.Newspaper;
import domain.User;
import forms.ActorForm;


@Service
@Transactional
public class UserService {

	// Managed repository --------------------------------------------------------------------------------------
	
	@Autowired
	private UserRepository userRepository;
	
	// Supporting service --------------------------------------------------------------------------------------
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private Validator validator;
	
	// Constructor ---------------------------------------------------------------------------------------------
	
	public UserService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------------------------------------------------------
	
	public User create(){
		
		User result = new User();
		
		result.setArticles(new ArrayList<Article>());
		result.setNewspapers(new ArrayList<Newspaper>());
		UserAccount ua = userAccountService.create();
		Authority au = new Authority();
		au.setAuthority("USER");
		ua.getAuthorities().add(au);
		result.setUserAccount(ua);
		
		return result;
	}
	
	public User save(User result){

		return userRepository.save(result);

	}
	
	public void delete(User res){
	
		userRepository.delete(res);
	}
	
	public Collection<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(int id){
		return userRepository.findOne(id);
	}
	
	public User findByPrincipal(){
		UserAccount userAccount = LoginService.getPrincipal();
		return userRepository.findByUserAccount(userAccount);
	}
	
	public User findByUserAccount(UserAccount u){
		User res = userRepository.findByUserAccount(u);
		return res;
	}
	
	//extra methods
	
	public User reconstruct(ActorForm actor, BindingResult binding){
		User result;
		
		if(actor.getId()==0){
			result = this.create();
					
			result.setName(actor.getName());
			result.setSurname(actor.getSurname());
			result.setEmail(actor.getEmail());
			result.setPhoneNumber(actor.getPhoneNumber());
			result.setPostalAddress(actor.getPostalAddress());
			
			result.getUserAccount().setUsername(actor.getUserAccount().getUsername());
			result.getUserAccount().setPassword(actor.getUserAccount().getPassword());
		}else{
			result = userRepository.findOne(actor.getId());
			
			result.setName(actor.getName());
			result.setSurname(actor.getSurname());
			result.setEmail(actor.getEmail());
			result.setPhoneNumber(actor.getPhoneNumber());
			result.setPostalAddress(actor.getPostalAddress());
			
			result.getUserAccount().setUsername(actor.getUserAccount().getUsername());
			result.getUserAccount().setPassword(actor.getUserAccount().getPassword());
		}
		validator.validate(result,binding);
		
		return result;
	}
	
	public ActorForm construct(User user){
		ActorForm result;		
		result = new ActorForm();
		
		result.setEmail(user.getEmail());
		result.setName(user.getName());
		result.setPhoneNumber(user.getPhoneNumber());
		result.setPostalAddress(user.getPostalAddress());
		result.setUserAccount(user.getUserAccount());
		
		return result;
	}
	
	
	
}