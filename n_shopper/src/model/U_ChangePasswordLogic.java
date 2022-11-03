package model;


import dao.UserDAO;

public class U_ChangePasswordLogic {
	public static U_User execute(U_User user) {
		UserDAO dao = new UserDAO();
		U_User	result = dao.CollationUser(user);
		return result;
	}
}
