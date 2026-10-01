package kimdong.vn.dao;

import kimdong.vn.entity.Users_24110203;

public interface IUserDao_24110203 {
	void insert(Users_24110203 user);

	void update(Users_24110203 user);

	Users_24110203 findByUsername(String username);

	Users_24110203 findByEmail(String email);
}