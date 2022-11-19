package model;

import dao.UserDAO;

public class U_LoginLogic {

	public U_User execute(U_User user) {

		UserDAO dao = new UserDAO();
		U_User result = dao.checkUser(user);

		return result;
	}


}
