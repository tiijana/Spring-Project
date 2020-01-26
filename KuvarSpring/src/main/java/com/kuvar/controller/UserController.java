package com.kuvar.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuvar.repository.ContainRepository;
import com.kuvar.repository.FavouriteCategoryRepository;
import com.kuvar.repository.IngredientRepository;
import com.kuvar.repository.IsFriendRepository;
import com.kuvar.repository.MessageRepository;
import com.kuvar.repository.RecipeRepository;
import com.kuvar.repository.RoleRepository;
import com.kuvar.repository.UserRepository;

import model.Favourite_category;
import model.Ingredient;
import model.IsFriend;
import model.Message;
import model.Picture;
import model.Recipe;
import model.User;

@Controller
@RequestMapping("/controller")
public class UserController {
	@Autowired
	UserRepository ur;
	@Autowired
	RoleRepository rr;
	@Autowired
	RecipeRepository recr;
	@Autowired
	IsFriendRepository ifr;
	@Autowired
	MessageRepository mr;
	@Autowired
	FavouriteCategoryRepository fcr;
	@Autowired
	ContainRepository cr;

	@RequestMapping(value = "addNewUser", method = RequestMethod.POST)
	public String newUser(HttpServletRequest request, User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(rr.findById(2).orElse(null));
		ur.save(user);
		request.getSession().setAttribute("addedUser", user);
		return "index";
	}
	
	@RequestMapping(value = "getNameOfUser", method = RequestMethod.GET)
	public String getNameOfUser(Principal p, HttpServletRequest request) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<Recipe> recipes = recr.findByUser(user);
		if (user != null && recipes != null) {
			request.getSession().setAttribute("usersRecipes", recipes);
			request.getSession().setAttribute("user", user);
		}
		List<User> notFriends = ur.getAllNotFriends(user.getIdUser());
		request.getSession().setAttribute("notFriends", notFriends);
		return "/users/profil";
	}
	
	@RequestMapping(value = "addFriend", method = RequestMethod.GET)
	public String addFriend(Principal p, HttpServletRequest request, Integer idUser2) {
		String username = p.getName();
		User userWhoAdd = ur.findByUsername(username); // onaj koji dodaje
		User addedUser = ur.findById(idUser2).get(); 
		addFriendFunc(userWhoAdd, addedUser);
		
		List<User> notFriends = ur.getAllNotFriends(userWhoAdd.getIdUser());
		request.getSession().setAttribute("notFriends", notFriends);
		return "/users/profil";
	}
	
	private void addFriendFunc(User userWhoAdd, User addedUser) {
		IsFriend isFriend = new IsFriend();
		isFriend.setUser1(userWhoAdd);
		isFriend.setUser2(addedUser);
		isFriend.setStatus("pending"); // ceka se prihvatanje
		ifr.save(isFriend);
	}
	
	@RequestMapping(value = "myFriendRequest", method = RequestMethod.GET)
	public String getMyFriendRequest(Principal p, HttpServletRequest request) {
		String username = p.getName();
		User user2 = ur.findByUsername(username); // trenutni ulogovani
		List<IsFriend> friendRequests = ifr.getAllRequests(user2.getIdUser()); // uzmi zahteve
		request.getSession().setAttribute("friendRequests", friendRequests);
		return "/users/addFriends";
	}
	
	@RequestMapping(value = "acceptRequest", method = RequestMethod.POST)
	public String acceptRequest(Principal p, HttpServletRequest request, Integer idUser1) {
		String username = p.getName(); 
		User user2 = ur.findByUsername(username); // ovo sam ja
		User user1 = ur.findById(idUser1).get(); // ovo je onaj koji mi je poslao zahtev
		IsFriend isfr = ifr.getIsFriend(user2.getIdUser(), user1.getIdUser());
		isfr.setStatus("accepted");
		ifr.flush();
		return "/users/addFriends";
	}
	
	@RequestMapping(value = "getMyFriends", method = RequestMethod.GET)
	public String getMyFriends(Principal p, HttpServletRequest request) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<User> friends = ur.getFriends(user.getIdUser());
		request.getSession().setAttribute("myFriends", friends);
		return "/users/usersFriends";
	}
	
	@RequestMapping(value = "getMyChatUsers", method = RequestMethod.GET)
	public String getMyChatUsers(Principal p, HttpServletRequest request) {
		String username = p.getName();
		User user = ur.findByUsername(username);
//		List<User> chatUsers = mr.getChatUsers(user.getIdUser());
//		System.out.println(chatUsers.size());
		List<User> friends = ur.getFriends(user.getIdUser());
		request.getSession().setAttribute("chatUsers", friends);
		return "/chat";
	}
	
	@RequestMapping(value = "showMessages", method = RequestMethod.GET)
	public String showMessages(Principal p, HttpServletRequest request, Integer idUser2) {
		String username = p.getName();
		User user1 = ur.findByUsername(username);
		User user2 = ur.findById(idUser2).get();
		List<Message> messages = mr.getMessagesWith(user1.getIdUser(), user2.getIdUser());
		request.getSession().setAttribute("messages", messages);
		request.getSession().setAttribute("user2", user2);
		return "/chat";
	}
	
	@RequestMapping(value = "sendMessage", method = RequestMethod.POST)
	public String sendMessage(Principal p, HttpServletRequest request, String content) {
		String username = p.getName();
		User user1 = ur.findByUsername(username);
		User user2 = (User) request.getSession().getAttribute("user2");
		Message msg = new Message();
		msg.setContent(content);
		msg.setUser1(user1);
		msg.setUser2(user2);
		msg.setDate(new Date());
		mr.save(msg);
		List<Message> messages = mr.getMessagesWith(user1.getIdUser(), user2.getIdUser());
		request.getSession().setAttribute("messages", messages);
		return "/chat";
	}
	
	@RequestMapping(value = "getFavouriteCategories", method = RequestMethod.GET)
	public String getFavouriteCategories(Principal p, HttpServletRequest request) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<Favourite_category> favCategories = fcr.findByUser(user);
		request.getSession().setAttribute("favourites", favCategories);
		return "users/favouriteCategories";
	}
	
	@RequestMapping(value = "addFavouriteCategory", method = RequestMethod.POST)
	public String addFavouriteCategory(Principal p, HttpServletRequest request, String name) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		Favourite_category fc = new Favourite_category();
		fc.setName(name);
		fc.setUser(user);
		fcr.save(fc);
		List<Favourite_category> favCategories = fcr.findByUser(user);
		request.getSession().setAttribute("favourites", favCategories);
		return "/users/favouriteCategories";
	}
	
	@RequestMapping(value = "getRecipeContent", method = RequestMethod.GET)
	public String getRecipeContent(HttpServletRequest request, Integer idRec) {
		Recipe recipe = recr.findById(idRec).get();
		List<Ingredient> ingredients = cr.getIngredientsForRecipe(recipe.getIdRecipe());
		List<Picture> pictures = recipe.getPictures();
		User user = recipe.getUser();
		request.getSession().setAttribute("recipeIngredients", ingredients);;
		request.getSession().setAttribute("recipePictures", pictures);
		request.getSession().setAttribute("recipeCon", recipe);
		request.getSession().setAttribute("userInfo", user);
		return "/users/recipeContent";
	}
	
	
	
	
}
