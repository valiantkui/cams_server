package kui.cams.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kui.cams.entity.User;

@Repository("userDao")
public interface UserDao {

	public List<User> findAllUser();
}
