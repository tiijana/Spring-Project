package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Recipe database table.
 * 
 */
@Entity
@NamedQuery(name="Recipe.findAll", query="SELECT r FROM Recipe r")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecipe;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private String name;

	//bi-directional many-to-one association to Picture
	@OneToMany(mappedBy="recipe")
	private List<Picture> pictures;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="Category_idCategory")
	private Category category;

	//bi-directional many-to-many association to Favourite_category
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="exist_in"
		, joinColumns={
			@JoinColumn(name="Recipe_idRecipe")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Favourite_category_idFavourite_category")
			}
		)
	private List<Favourite_category> favouriteCategories;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_idUser")
	private User user;

	//bi-directional many-to-one association to Contain
	@OneToMany(mappedBy="recipe")
	private List<Contain> contains;

	public Recipe() {
	}

	public int getIdRecipe() {
		return this.idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture addPicture(Picture picture) {
		getPictures().add(picture);
		picture.setRecipe(this);

		return picture;
	}

	public Picture removePicture(Picture picture) {
		getPictures().remove(picture);
		picture.setRecipe(null);

		return picture;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Favourite_category> getFavouriteCategories() {
//		if (favouriteCategories == null) {
//			favouriteCategories = new ArrayList<Favourite_category>();
//		}
		return this.favouriteCategories;
	}

	public void setFavouriteCategories(List<Favourite_category> favouriteCategories) {
		this.favouriteCategories = favouriteCategories;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Contain> getContains() {
		return this.contains;
	}

	public void setContains(List<Contain> contains) {
		this.contains = contains;
	}

	public Contain addContain(Contain contain) {
		getContains().add(contain);
		contain.setRecipe(this);

		return contain;
	}

	public Contain removeContain(Contain contain) {
		getContains().remove(contain);
		contain.setRecipe(null);

		return contain;
	}

}