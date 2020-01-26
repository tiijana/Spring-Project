package com.kuvar.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuvar.repository.CategoryRepository;
import com.kuvar.repository.RecipeRepository;

import model.Category;
import model.Recipe;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/categoryController")
public class CategoryController {
	
	@Autowired
	CategoryRepository cr;
	
	@Autowired
	RecipeRepository rr;
	
	@RequestMapping(value = "addNewCategory", method = RequestMethod.POST)
	public String addNewCategory(HttpServletRequest request, String name) {
		Category category = new Category();
		category.setName(name);
		cr.save(category);
		request.getSession().setAttribute("addedCategory", category);
		return "/admin/createCategory";
	}
	
//	@RequestMapping(value = "getStatistic", method = RequestMethod.GET)
//	public String getStatistic(HttpServletRequest request, Date date) {
//		List<Recipe> recipes = rr.findByDate(date);
//		int numOfRecipes = 0;
//		if (recipes != null) {
//			numOfRecipes = recipes.size();
//		}
//		String msg = "Number of recipes is: " + numOfRecipes;
//		request.getSession().setAttribute("msgNumOfRecipes", msg);
//		return "/admin/statistic";
//	}
	

	
	@RequestMapping(value = "getStatisticForCategory", method = RequestMethod.GET)
	public String getStatisticForCategory(HttpServletRequest request) {
		List<Category> allCategories = cr.findAll();
		Map<String, Integer> info = new HashMap<String, Integer>();
		for(Category c : allCategories) {
			info.put(c.getName(), c.getRecipes().size());
		}
		request.getSession().setAttribute("informations", info);
		return "/admin/statistic";
	}
	
	@RequestMapping(value="generateReportCategory", method=RequestMethod.GET) 
	public void generateReport(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		List<Recipe> recipes = rr.findAll();
		Collections.sort(recipes, new Comparator<Recipe>() {
			@Override
			public int compare(Recipe r1, Recipe r2) {
				return r1.getCategory().getName().compareToIgnoreCase(r2.getCategory().getName());
			}
		});
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(recipes);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/RecipesPerCategory.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=RecipesPerCategory.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
	
	@RequestMapping(value="generateReportDate", method=RequestMethod.GET) 
	public void generateReportDate(HttpServletRequest request, HttpServletResponse response, Date date) throws Exception { 
		List<Recipe> recipes = rr.findByDate(date);
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(recipes);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/DailyReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=DailyReport.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}



}
