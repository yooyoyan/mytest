package cn.itcast.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdEncoderUtil {

	public static void main(String [] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//$2a$10$VFPVk.hwUjMbwZgNXCr1/OoQ2oBKMm.TSt8XB4QSFenwgF8lPv0Za
		//$2a$10$gWuslwY1JGYvetVQmS7Oy.gmjbrw1.o7s.VkvZJrZyzUwFxKg4Ghu
		//$2a$10$TvL01cEChAOFioPnruf02O7LBQJvl6J/kfo7rONSWgVrlqMc9HX3C
		//System.out.println(encoder.encode("123456"));
		//匹配的测试
		System.out.println(encoder.matches("123456", "$2a$10$gWuslwY1JGYvetVQmS7Oy.gmjbrw1.o7s.VkvZJrZyzUwFxKg4Ghu"));
	}
}
