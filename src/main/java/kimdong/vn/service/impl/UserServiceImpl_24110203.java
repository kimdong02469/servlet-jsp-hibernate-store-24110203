package kimdong.vn.service.impl;

import kimdong.vn.dao.IUserDao_24110203;
import kimdong.vn.dao.impl.UserDaoImpl_24110203;
import kimdong.vn.entity.Users_24110203;
import kimdong.vn.service.IUserService_24110203;

public class UserServiceImpl_24110203 implements IUserService_24110203 {
	private IUserDao_24110203 userDao = new UserDaoImpl_24110203();

	@Override
	public void register(Users_24110203 user) {
		userDao.insert(user);
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		if (email == null || otp == null) {
			return false;
		}

		Users_24110203 user = userDao.findByEmail(email.trim());
		if (user != null && user.getCode() != null) {
			String dbCode = user.getCode().trim();
			String inputOtp = otp.trim();

			System.out.println(">>> KIỂM TRA OTP:");
			System.out.println("Email: " + email.trim());
			System.out.println("Mã trong DB: [" + dbCode + "]");
			System.out.println("Mã nhập vào: [" + inputOtp + "]");

			if (dbCode.equals(inputOtp)) {
				user.setStatus(1); // Kích hoạt tài khoản
				user.setCode(null); // Xóa OTP sau khi hoàn tất
				userDao.update(user);
				return true;
			}
		} else {
			System.out.println(">>> Không tìm thấy tài khoản với email: " + email);
		}
		return false;
	}

	@Override
	public Users_24110203 login(String username, String password) {
		Users_24110203 user = userDao.findByUsername(username);
		if (user != null && user.getPassword().equals(password) && user.getStatus() == 1) {
			return user;
		}
		return null;
	}
}