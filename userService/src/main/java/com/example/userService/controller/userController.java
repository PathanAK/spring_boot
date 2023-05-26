package com.example.userService.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.userService.service.UsersServices;
import com.example.userService.user.UserData;

@RestController
public class userController {
	
	@Autowired
	UsersServices service;
	
	@GetMapping(value = "/greet", produces = MediaType.TEXT_PLAIN_VALUE )
	
	public ResponseEntity<String> greet() {
		
		return new ResponseEntity<String>(service.demoGreet(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/users")
	public ResponseEntity<ArrayList<UserData>> getUsers() {
		return new ResponseEntity<ArrayList<UserData>>(service.getUsers(),  HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{uid}")
	public ResponseEntity<UserData> getUserById(@PathVariable Integer uid) {
		return new ResponseEntity<UserData>(service.getUserById(uid), HttpStatus.OK);
	}
	
	@GetMapping(value = "/username/{uname}")
	public ResponseEntity<UserData> getUserByName(@PathVariable String uname) {
		return new ResponseEntity<UserData>(service.getUserByName(uname), HttpStatus.OK);
	}
	
	@GetMapping(value = "/useraddr/{address}")
	public ResponseEntity<List<UserData>> getUserByAddress(@PathVariable String address) {
		return new ResponseEntity<List<UserData>>(service.getUserByAddress(address), HttpStatus.OK);
	}
	
	@GetMapping(value = "/usernameaddr/{uname}/{address}")
	public ResponseEntity <UserData> getUesrBynameaddress(@PathVariable String uname, @PathVariable String address) {
		return new ResponseEntity<UserData>(service.getUesrBynameaddress(uname, address), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pages/{page_size}/{page_no}")
	public ResponseEntity <List<UserData>> getUserBypages(@PathVariable int page_size, @PathVariable int page_no) {
		return new ResponseEntity <List<UserData>>(service.getUserPages(page_no, page_size), HttpStatus.OK);
	}
	
	@GetMapping(value = "/sorteduser/{sort}/{sort_order}")
	public ResponseEntity<List<UserData>> getSortOrder(@PathVariable String sort, @PathVariable String sort_order) {
		return new ResponseEntity<List<UserData>>(service.getUsersbysort(sort, sort_order), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserData> insertUser(@RequestBody UserData usr) {
		return new ResponseEntity<UserData>(service.insertUser(usr), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update/{uid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserData> updateUser(@PathVariable int uid, @RequestBody UserData usr) {
		return new ResponseEntity<UserData>(service.updateUser(uid, usr), HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable int uid) {
		return new ResponseEntity<String>(service.deleteUser(uid), HttpStatus.ACCEPTED);
	}
}
