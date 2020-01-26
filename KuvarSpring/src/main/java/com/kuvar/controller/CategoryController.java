package com.kuvar.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value = "getStatistic", method = RequestMethod.GET)
	public String getStatistic(HttpServletRequest request, Date date) {
		List<Recipe> recipes = rr.findByDate(date);
		int numOfRecipes = 0;
		if (recipes != null) {
			numOfRecipes = recipes.size();
		}
		String msg = "Number of recipes is: " + numOfRecipes;
		request.getSession().setAttribute("msgNumOfRecipes", msg);
		return "/admin/statistic";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
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
	
//	@RequestMapping(value="generateReport", method=RequestMethod.GET) 
//	public void generateReport(HttpServletRequest request, HttpServletResponse response) throws Exception { 
////		List<Predstava> predstave = (List<Predstava>)request.getSession().getAttribute("predstave");
//	
//		response.setContentType("text/html");
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource();
//		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/PredstaveRezisera.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//		Map<String, Object> params = new HashMap<String, Object>();
////		String reziser="";
////			reziser=predstave.get(0).getReziser().getIme()+" "+predstave.get(0).getReziser().getPrezime();
////		if(predstave!=null && predstave.size()>0)
////			params.put("reziser", reziser);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//		inputStream.close();
//		
//		
//		response.setContentType("application/x-download");
//		response.addHeader("Content-disposition", "attachment; filename=PredstaveRezisera.pdf");
//		OutputStream out = response.getOutputStream();
//		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
//	}



}
