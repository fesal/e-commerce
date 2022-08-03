package com.ecomerce.demo.controller;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseAbstractController {
	
	public static final String FILE_LOCATION = "";//"var\\www\\html\\";
	public static final String FILE_DOMAIN = "http://localhost/rest-files/";
	public static final String NORMAL_IMAGE = "";
	public static final String _30x30 = "30x30/";
	public static final String _130x130 = "130x130/";
	public static final String _156x156 = "156x156/";
	public static final String SERVICE_DOMAIN = "";
	public static final String DOMAIN = "localhost/";
	
//	@Value("${file.domain}")
//	public static String FILE_DOMAIN;
//	
//	@Value("${file.location}")
//	public static String FILE_LOCATION;
//	
//	@Value("${servic.domain}")
//	public static String SERVICE_DOMAIN;
//	
//	@Value("${file.domain}")
//	public static String NORMAL_IMAGE;
//	
//	@Value("${30x30}")
//	public static String _30x30 = "30x30/";
//	
//	@Value("${130x130}")
//	public static String _130x130;
//	
//	@Value("${156x156}")
//	public static String _156x156;
	
//	@Value("${domain}")
//	public static String DOMAIN;


	protected String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	protected JsonNode getJsonNode(String json){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}

}
