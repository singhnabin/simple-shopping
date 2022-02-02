package com.nabinsingh34.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
		String s="i like programming and programming is my passion";
		String[] str=s.split(" ");
		Map<String, Integer> words= new HashMap<>();
		for(String s1: str){
			if(words.containsKey(s1)){
				words.put(s1,words.get(s1)+1);
			}else{
				words.put(s1,1);
			}
		}

		for(Map.Entry<String,Integer> word: words.entrySet()){
			System.out.println("word "+word.getKey()+" is repeated: "+word.getValue()+" times");
		}
		String input="Welcome to Java Session Session Session";  //Input String
		String[] word=input.split(" ");  //Split the word from String
		int wrc=1;    //Variable for getting Repeated word count

		for(int i=0;i<word.length;i++) //Outer loop for Comparison
		{
			for(int j=i+1;j<word.length;j++) //Inner loop for Comparison
			{

				if(word[i].equals(word[j]))  //Checking for both strings are equal
				{
					wrc=wrc+1;    //if equal increment the count
					word[j]="0"; //Replace repeated words by zero
				}
			}
			if(word[i]!="0")
				System.out.println(word[i]+"--"+wrc); //Printing the word along with count
			wrc=1;

		}



	}


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
