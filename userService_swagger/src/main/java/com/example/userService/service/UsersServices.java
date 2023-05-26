package com.example.userService.service;

//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.userService.Repository.UserRepository;
import com.example.userService.exceptions.UserNotFoundException;
import com.example.userService.user.UserData;

@Component
public class UsersServices {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	RestTemplate restTemplate;

	
	public ArrayList<UserData> getUsers() {
		return (ArrayList<UserData>) repo.findAll();
	}
	
	public UserData getUserById(Integer uid) {
		return repo.findById(uid).orElseThrow(() -> new UserNotFoundException("User not Found with id "+uid));
	}
	
	public String demoGreet() {
		String res = restTemplate.getForObject("http://localhost:8087/greet", String.class);
		return res;
	}
	
	public UserData getUserByName(String uname) {
		return repo.findByUname(uname);
	}
	
	public List<UserData> getUserByAddress(String addr) {
		return repo.findByaddress(addr);
	}
	
	public UserData getUesrBynameaddress(String uname, String address) {
		return repo.findByUnameandAddress(uname,address);
	}
	
	public List<UserData> getUserPages(int page_no, int Page_size) {
		PageRequest pageable =  PageRequest.of(page_no, Page_size);
		Page<UserData> page = repo.findAll(pageable);
		if (page.getContent().isEmpty())
			throw new RuntimeException("No Rerord Found..!!");
		return page.toList();
	}
	
	public List<UserData> getUsersbysort(String sort, String sort_order) {
		
		if (sort_order.equalsIgnoreCase("dec")) 
			return (List<UserData>) repo.findAll(Sort.by(sort).descending());
		return (List<UserData>) repo.findAll(Sort.by(sort).ascending());
		
	}
	
	public UserData insertUser(UserData usr) {
		return repo.save(usr);
	}
	
	public UserData updateUser(int uid,  UserData usr) {
		UserData existing = this.getUserById(uid);
		if(usr.getUname()!=null)
			existing.setUname(usr.getUname());
		if(usr.getAddress()!=null)
			existing.setAddress(usr.getAddress());
		return repo.save(existing);
	}
	
	public String deleteUser(int uid) {
		repo.deleteById(uid);
		return "User deleted with id : "+ uid;
	}	
}
