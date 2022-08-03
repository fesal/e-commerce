package com.ecomerce.demo.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ecomerce.demo.auth.service.SitemapService;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;


@Controller
public class SitemapController {
	
	private static final String BASE_URL = "https://openviza.com/product?id="; 
	private static String path2 = "http://localhost:8000/";
	String path = System.getProperty("user.dir")+"/src/main/resources/templates/sitemap/";
	private int numberOfProductsInSitemap = 30000;
	
	@Autowired
	private SitemapService siteMap;
	
	@GetMapping(value= {"/sitemap.xml", "sitemap"}, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
    public FileSystemResource sitemap(HttpSession httpSession, Model model) {
        try {

        	WebSitemapGenerator sitemap = WebSitemapGenerator
                    .builder(path2, new File(path))
                    .allowMultipleSitemaps(true)
                    //.gzip(true)
                    .build();
        	int count = siteMap.countProd();
        	if(count<numberOfProductsInSitemap)
        		count =2;
        	else
        		count = count/numberOfProductsInSitemap;
        	
        	System.err.println("--------"+count);
            for (int i = 1; i<count; i++) {
            	WebSitemapUrl url = new WebSitemapUrl
                        .Options(path2+"sitemap"+i+".xml")
                        .lastMod(new Date())
                        .priority(0.8)
                        .changeFreq(ChangeFreq.MONTHLY)
                        .build();
                sitemap.addUrl(url);
            }
          
            List<File> write = sitemap.write();
            
            return new FileSystemResource(write.get(0));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } 

        return null;
    }
	
	@GetMapping(value= {"/sitemap{numberId}.xml"}, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public FileSystemResource createFile(@PathVariable("numberId") Integer numberId)
	{
		try {
			    path = System.getProperty("user.dir")+"/src/main/resources/templates/sitemap/"+"sitemap"+numberId+".xml";
	            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	            Document document = documentBuilder.newDocument();

	            Element root = document.createElement("urlset");
	            document.appendChild(root);
	            List<Integer> ids = null;
	            if(numberId==1)
	            {
	            	ids = siteMap.createSitemap(numberId, numberOfProductsInSitemap);
	            }
	            else {
	            	ids = siteMap.createSitemap((numberId-1)*numberOfProductsInSitemap, numberId*numberOfProductsInSitemap);
	            }
	            
	            
	            for(int j=1; j<ids.size(); j++)
	            {
		            Element url = document.createElement("url");
		            root.appendChild(url);

		            Element firstName = document.createElement("loc");
		            firstName.appendChild(document.createTextNode(BASE_URL+ids.get(j)));
		            url.appendChild(firstName);

		            Element lastname = document.createElement("lastmod");
		            lastname.appendChild(document.createTextNode(new Date().toString()));
		            url.appendChild(lastname);

		            Element email = document.createElement("changefreq");
		            email.appendChild(document.createTextNode("monthly"));
		            url.appendChild(email);

		            Element department = document.createElement("priority");
		            department.appendChild(document.createTextNode("0.8"));
		            url.appendChild(department);

	            }
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();

	            Transformer transformer = transformerFactory.newTransformer();

	            DOMSource domSource = new DOMSource(document);

	            StreamResult streamResult = new StreamResult(new File(path));
	            transformer.transform(domSource, streamResult);
	            return new FileSystemResource(path);

	        } catch (ParserConfigurationException pce) {

	            pce.printStackTrace();

	        } catch (TransformerException tfe) {

	            tfe.printStackTrace();
	        } 
		return null;
	 }
}
