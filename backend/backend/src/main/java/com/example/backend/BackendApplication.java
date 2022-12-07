package com.example.backend;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
		JSONObject obj=new JSONObject();
		obj.put("name","sonoo");
		System.out.print(obj);
	}

}
enum color
{
	
}
