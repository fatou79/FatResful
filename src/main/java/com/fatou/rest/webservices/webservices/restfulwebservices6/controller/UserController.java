package com.fatou.rest.webservices.webservices.restfulwebservices6.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatou.rest.webservices.webservices.restfulwebservices.binette.User;
import com.fatou.rest.webservices.webservices.restfulwebservices.service.UserDaoService;
import com.fatou.rest.webservices.webservices.restfulwebservices6.exception.UserNotFoundException;

@RestController
public class UserController {
	
	
//	@Autowired(required = false)
//	private UserDaoService service;
	
	private UserDaoService service = new UserDaoService();
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
		}
	
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
	
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
	User saveUser = service.saveUser(user);
	saveUser.getId();
	URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saveUser.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
