package br.edu.ifsp.ifitness.model.util.users;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.ifitness.model.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.util.LocalDateTypeAdapter;

public class UsersWriter {

	public static Boolean write(User user) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, 
						new LocalDateTypeAdapter())
				.create();
		List<User> users = UsersReader.read();
		String path = Util.RESOURCES_PATH.getValue() + "/users.json";

		if(users == null) {
			// quando não tem nenhum objeto cadastrado
			users = new ArrayList<>();
			user.setId(1L);
		}else {
			// quando tem, verificar e-mail
			for(User u : users) {
				if(u.getEmail().equals(user.getEmail())) {
					return false;
				}
			}
			// definir o id do novo usuário
			user.setId(users.get(users.size() -1).getId() + 1);
		}
		// criptografar a senha
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		
		// adicionar o novo usuário na lista
		users.add(user);
		
		// converte para o formato JSON
		String json = gson.toJson(users);
		
		// escrever no arquivo
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(json);
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}








