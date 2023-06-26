package com.sh.mvc.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 단방향 암호화 (Hashing)
 * - 평문은 특정 알고리즘에 의해 암호문으로 변환하며, 이를 다시 평문으로 복호화할 수 없다.
 * - 고정길이의 해시값은 평문이 비숫해도, 전혀 다른 결과를 출력한다.
 * 
 * - 암호화알고리즘 : md5, sha1, sha256, sha512 등
 * 		- sha256이상의 알고리즘을 사용할 것!
 * 
 * 1. MessageDigest
 * 2. Base64 Encoding
 *
 */
public class HelloMvcUtils {

	public static String getEncryptedPassword(String rawPassword, String salt) {
		String encryptedPassword = null;
		// 1. 암호화
		byte[] output = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = rawPassword.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			md.update(saltBytes); // salt 추가
			output = md.digest(input); // 평문비밀번호 전달
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(new String(output));
		
		// 2. 인코딩
		Encoder encoder = Base64.getEncoder();
		encryptedPassword = encoder.encodeToString(output);
		System.out.println(encryptedPassword);
		
		return encryptedPassword;
	}

}





