package br.com.techHouse.zmed.security;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@URLMappings(mappings = { 
		@URLMapping(id = "login", pattern = "/login", viewId = "/login.jsf"),
		@URLMapping(id = "index", pattern = "/index", viewId = "/home.jsf"),
		@URLMapping(id = "home", pattern = "/home", viewId = "/home.jsf"),
		@URLMapping(id = "logout", pattern = "/logout", viewId = "/home.jsf"),
		@URLMapping(id = "medicamento", pattern = "/medicamento", viewId = "/pages/medicamento/medicamento.jsf"),
		})
public class ZmedViewConfig {

}