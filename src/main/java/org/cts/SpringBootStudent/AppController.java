package org.cts.SpringBootStudent;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private StudentService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Student> listStudents = service.listAll();
		model.addAttribute("listStudents", listStudents);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewStudentPage(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "new_student";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("student") Student student) {
		
		service.save(student);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_student");
		Student student = service.get(id);
		mav.addObject("student", student);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
