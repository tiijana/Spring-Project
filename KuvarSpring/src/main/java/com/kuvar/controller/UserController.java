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
import com.kuvar.repository.IsFriendRepository;
import com.kuvar.repository.MessageRepository;
import com.kuvar.repository.RecipeRepository;
import com.kuvar.repository.RoleRepository;
import com.kuvar.repository.UserRepository;

import model.Favourite_category;
import model.IsFriend;
import model.Message;
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
	public String newUser(HttpServletRequest request, User user) { // dodavanje korisnika
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(rr.findById(2).orElse(null));
		ur.save(user);
		request.getSession().setAttribute("addedUser", user);
		return "/index";
	}
	
	@RequestMapping(value = "getNameOfUser", method = RequestMethod.GET)
	public String getNameOfUser(Principal p, HttpServletRequest request) { // dobavi recepte ulogovanog korisnika za profil
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
		User userWhoAdd = ur.findByUsername(username); // onaj koji salje zahtev za prijateljstvo
		User addedUser = ur.findById(idUser2).get();  // onaj kome se salje zahtev
		addFriendFunc(userWhoAdd, addedUser); // setovanje statusa na pending dok se ne prihvati zahtev
		
		List<User> notFriends = ur.getAllNotFriends(userWhoAdd.getIdUser()); // kad posalje zahtev azuriramo listu onih koji mu nisu prijatelji
		request.getSession().setAttribute("notFriends", notFriends);
		return "/users/profil";
	}
	
	private void addFriendFunc(User userWhoAdd, User addedUser) {
		IsFriend isFriend = new IsFriend();
		isFriend.setUser1(userWhoAdd);
		isFriend.setUser2(addedUser);
		isFriend.setStatus("pending"); // ceka se prihvatanje
		userWhoAdd.getIsFriends1().add(isFriend);
		addedUser.getIsFriends2().add(isFriend);
		ur.saveAndFlush(userWhoAdd);
		ur.saveAndFlush(addedUser);
		ifr.save(isFriend);
	}
	
	@RequestMapping(value = "myFriendRequest", method = RequestMethod.GET)
	public String getMyFriendRequest(Principal p, HttpServletRequest request) { // metod koji vraca zahteve za prijateljstvo ulogovanom korisniku
		String username = p.getName();
		User user2 = ur.findByUsername(username); // trenutni ulogovani
		List<IsFriend> friendRequests = ifr.getAllRequests(user2.getIdUser()); // uzmi zahteve
		request.getSession().setAttribute("friendRequests", friendRequests);
		return "/users/addFriends";
	}
	
	@RequestMapping(value = "acceptRequest", method = RequestMethod.POST)
	public String acceptRequest(Principal p, HttpServletRequest request, Integer idUser1) {
		String username = p.getName(); 
		User user2 = ur.findByUsername(username); // korisnik koji prihvata zahtev
		User user1 = ur.findById(idUser1).get(); // korisnik koji mi je poslao zahtev
		IsFriend isfr = ifr.getIsFriend(user2.getIdUser(), user1.getIdUser()); // nabavimo odgovarajuci isFriend objekat za ova dva user-a
		isfr.setStatus("accepted"); 
		ifr.flush();
		List<User> notFriends = ur.getAllNotFriends(user1.getIdUser()); // azuriranje liste onih koji sada ulogovanom korisniku nisu prijatelji
		request.getSession().setAttribute("notFriends", notFriends);
		return "/users/addFriends";
	}
	
	@RequestMapping(value = "getMyFriends", method = RequestMethod.GET)
	public String getMyFriends(Principal p, HttpServletRequest request) { // lista prijatelja korisnika
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<User> friends = ur.getFriends(user.getIdUser());
		request.getSession().setAttribute("myFriends", friends);
		return "/users/usersFriends";
	}
	
	@RequestMapping(value = "getMyChatUsers", method = RequestMethod.GET)
	public String getMyChatUsers(Principal p, HttpServletRequest request) { // lista prijatelja za chat
		String username = p.getName();
		User user = ur.findByUsername(username);
//		List<User> chatUsers = mr.getChatUsers(user.getIdUser());
//		System.out.println(chatUsers.size());
		List<User> friends = ur.getFriends(user.getIdUser());
		request.getSession().setAttribute("chatUsers", friends);
		return "/chat";
	}
	
	@RequestMapping(value = "showMessages", method = RequestMethod.GET)
	public String showMessages(Principal p, HttpServletRequest request, Integer idUser2) { // dobavi poruke izmedju dva korisnika 
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
	public String getFavouriteCategories(Principal p, HttpServletRequest request) { // omiljene kategorije korisnika
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<Favourite_category> favCategories = fcr.findByUser(user);
		request.getSession().setAttribute("favourites", favCategories);
		return "users/favouriteCategories";
	}
	
	@RequestMapping(value = "addFavouriteCategory", method = RequestMethod.POST)
	public String addFavouriteCategory(Principal p, HttpServletRequest request, String name) { // dodavanje omiljene kategorije
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
	
}
