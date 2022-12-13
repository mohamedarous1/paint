package com.example.backend;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(BackendApplication.class, args);

//		ArrayList<JSONObject> Shapes =  MainSystem.SaveShapes();
//
//		System.out.println(Shapes.get(0).toString());
	}
}
