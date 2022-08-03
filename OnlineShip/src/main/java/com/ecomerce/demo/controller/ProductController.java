package com.ecomerce.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ecomerce.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecomerce.demo.auth.service.UserService;
import com.ecomerce.demo.model.Category;
import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.model.User;
import com.ecomerce.demo.service.ProductServices;
import com.ecomerce.demo.util.FileUtil;

@Controller
public class ProductController extends BaseAbstractController{

	@Autowired
	private ProductServices productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"product", "product?","product.html/{id}"})
	public String product(Model model, @RequestParam("id") int id){
		Product p = productService.findByMyId(id);
		if(p!=null)
		{
			model.addAttribute("product", p);
			return "product";
		}
		return "redirect:/index";
	}
	
	@RequestMapping({"create_product","create_product.html"})
	public String createProductPage(Model model){
		model.addAttribute("categories", categoryService.findAll());
		return "create_product";
	}
	
	@RequestMapping({"edit_product","edit_product.html"})
	public String editProductPage(){
		return "edit_product";
	}
	
	private static String UPLOADED_FOLDER = "/root/Desktop/";

    @PostMapping("/create_new_product")
    public String singleFileUpload(@RequestParam("category")String category, @RequestParam("name") String name, @RequestParam("price") String price,
    		@RequestParam("description") String description, @RequestParam("shipping") String shipping,
    		@RequestParam("tax") String tax, 
    			@RequestParam("file") MultipartFile[] file, RedirectAttributes redirectAttributes) {
    	
    	Product p = null;
        if (file[0].isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }

        try {
        	String[] imageNames = new String[3];
        	int i = 0;
        	
        	String temporaryImageName =  getUUID();
        	for(MultipartFile uploadFile : file)
        	{
	            /*byte[] bytes = uploadFile.getBytes();
	            System.err.println(uploadFile.getOriginalFilename()+" bytes "+ bytes);
	            Path path = Paths.get(UPLOADED_FOLDER + uploadFile.getOriginalFilename());
	            Files.write(path, bytes);*/
	            //uploadFile.getOriginalFilename(); 
        		int dot = uploadFile.getOriginalFilename().lastIndexOf('.');
        		byte[] bytes = uploadFile.getBytes();
        		imageNames[i] = FILE_LOCATION+NORMAL_IMAGE+temporaryImageName+uploadFile.getOriginalFilename().substring(dot);
        		Path path = Paths.get(imageNames[i]);//UPLOADED_FOLDER + uploadFile.getOriginalFilename());
	            Files.write(path, bytes);
        		FileUtil.saveImage(uploadFile, FILE_LOCATION, temporaryImageName, uploadFile.getOriginalFilename().substring(dot));
	            i++;
        	}
        	User u = userService.findByUsername(AuthenticatedUser.getUsername());
        	Category c = categoryService.findByName(category.trim());
        	System.err.println(c.getId());
        	p = new Product(name, description, price, shipping, tax, c, u, imageNames[0], imageNames[1],imageNames[2]);
        	productService.create(p);
        	p = productService.findById(p.getId());
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/product?id="+p.getId();
    }
}
