package com.iktpreobuka.schoolregister.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {
	
	public static String getPassEncoded(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(pass);
	}
	
	public static Boolean comparePassword(String normalPass,String crypPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(normalPass, crypPass);
		
	}

	public static void main(String[] args) {
		System.out.println(getPassEncoded("sifra"));
	}

}
