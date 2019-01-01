package kui.cams.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kui.cams.dao.UserDao;
import kui.cams.entity.User;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@RequestMapping("/findAllUser")
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
}
