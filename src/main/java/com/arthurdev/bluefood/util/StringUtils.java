package com.arthurdev.bluefood.util;

public class StringUtils {
    
	public static boolean isEmpty(String str) {
		if (str == null) {                              
			return true;
		}
		
		return str.trim().length() == 0;           //tira espaço embrnco das pontas e retorna o tmanho
	}
	
	public static String encrypt(String rawString) {
		if (isEmpty(rawString)) {
			return null;
		}
		
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//return encoder.encode(rawString);
		return null;
	}
	
}
