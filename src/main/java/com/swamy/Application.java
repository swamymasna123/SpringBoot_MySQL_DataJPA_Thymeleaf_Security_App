package com.swamy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		/*ConfigurableApplicationContext context = null;
		IUserService service = null;
		context = SpringApplication.run(Application.class, args);

		service = context.getBean(IUserService.class);
		System.out.println(service);

		Set roles = new HashSet<>();
		roles.add("ADMIN");
		roles.add("EMP");
		
		UserDTO user = new UserDTO(111, "ABC", "abc@gmail.com", "abc", roles);
		Integer id = service.saveUser(user);
		System.out.println("User Saved Successfully with Id: "+ id);
		
		
		List<UserDTO> list = service.getAllUsers();
		list.forEach(ob ->{
			System.out.println(ob);
		});
		
		
		UserDTO obj1 = service.getOneUser(1);
		System.out.println(obj1);
		
		
		Set roles2 = new HashSet<>();
		roles2.add("ADMIN");
		roles2.add("EMP");
		
		UserDTO user2 = new UserDTO(111, "ABC-NEW", "abc@gmail.com", "abc", roles2);
		service.updateUser(user2);
		System.out.println("User Updated Successfully with Id: "+ user2.getUserId());
		
		
//		service.deleteUser(4);
//		System.out.println("User Deleted Successfully..!");
		
		
		Optional<UserDTO> obj2 = service.getOneUserById(6);
		System.out.println(obj2);
		
		context.close();
		
		*/
	}

}




