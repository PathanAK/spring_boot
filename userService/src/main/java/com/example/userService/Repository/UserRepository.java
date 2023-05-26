package com.example.userService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.userService.user.UserData;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserData, Integer>{
	
	public UserData findByUname(String uname);
	
	public List<UserData> findByaddress(String address);
	
	@Query(value = "select * from user_data where uname=:uname and address=:address", nativeQuery=true)
	public UserData findByUnameandAddress(@Param(value = "uname") String uname, @Param(value = "address") String address);

}
