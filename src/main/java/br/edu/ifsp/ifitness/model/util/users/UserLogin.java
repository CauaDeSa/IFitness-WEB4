package br.edu.ifsp.ifitness.model.util.users;

import java.util.List;

import br.edu.ifsp.ifitness.model.User;

public class UserLogin {
	
	public static User login(String email, String password) {
		// ler o arquivo de usuários
		List<User> users = UsersReader.read();
		if(users != null) {
			for(User u: users) {
				if(u.getEmail().equals(email) && 
					u.getPassword().equals(
							PasswordEncoder.encode(password))) {
					return u;
				}
			}
		}
		return null;
	}

}





