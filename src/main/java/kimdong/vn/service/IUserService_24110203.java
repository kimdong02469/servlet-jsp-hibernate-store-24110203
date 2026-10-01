package kimdong.vn.service;

import kimdong.vn.entity.Users_24110203;

public interface IUserService_24110203 {
	void register(Users_24110203 user);

	boolean verifyOtp(String email, String otp);

	Users_24110203 login(String username, String password);
}