package br.edu.ifsp.ifitness.model.util.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.util.LocalDateTypeAdapter;
import br.edu.ifsp.ifitness.model.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class UsersReader {

	public static List<User> read(){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, 
						new LocalDateTypeAdapter())
				.create();
		List<User> users = null;
		String path = Util.RESOURCES_PATH.getValue() + "/users.json";
		try {
			File file = new File(path);
			if(file.exists()) {
				BufferedReader buffer = new BufferedReader(
						new FileReader(path));
				TypeToken<List<User>> type =
                        new TypeToken<>() {
                        };
				users = gson.fromJson(buffer, type);
				buffer.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static User findUserByEmail(String email){
		List<User> users = read();
		
		for(User user: users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		
		return null;
	}
	
}






