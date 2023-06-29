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

	/** - totalContent 전체 게시물 수
	 * 	- totalPage 전체페이지수
	 * 	- pagebarSize 5개
	 * 	- pageNo 페이지바의 숫자(반복처리)
	 * 	- pageStart 페이지 바 시작번호
	 * 	- pageEnd 페이지바 끝번호
	 * 	- url 요청url
	 */
	public static String getPagebar(int cpage, int limit, int totalContent, String url) {
		StringBuilder pagebar = new StringBuilder(); // 문자열이 많다면, string 보다 stringbuilder가 좋다. 문자열 더하기 연산에 최적화
		
		// 총게시물수가 60개, LIMIT이 10이라면 6페이지 필요
		// 총게시물수가 61개, LIMIT이 10이라면 7페이지 필요
		int totalPage = (int) Math.ceil(totalContent / (double)limit);
		
		url += "?cpage=";
		
		int pagebarSize = 5;
		
		// 페이지바 사이즈 5, 현재 페이지가 1이면, 1 2 3 4 5
		// 페이지바 사이즈 5, 현재 페이지가 2이면, 1 2 3 4 5
		// 페이지바 사이즈 5, 현재 페이지가 3이면, 1 2 3 4 5
		// 페이지바 사이즈 5, 현재 페이지가 4이면, 1 2 3 4 5
		// 페이지바 사이즈 5, 현재 페이지가 5이면, 1 2 3 4 5
		// 페이지바 사이즈 5, 현재 페이지가 6이면, 6 7 8 9 10
		// 페이지바 사이즈 5, 현재 페이지가 7이면, 6 7 8 9 10
		// 페이지바 사이즈 5, 현재 페이지가 8이면, 6 7 8 9 10
		// 페이지바 사이즈 5, 현재 페이지가 9이면, 6 7 8 9 10
		// 페이지바 사이즈 5, 현재 페이지가 10이면, 6 7 8 9 10
		int pageStart = (cpage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + 4;
		int pageNo = pageStart;
		
		// 1. 이전
		if(pageNo == 1) {
			// 이전 버튼 비활성화
		} else {
			pagebar.append("<a href='%s%d'>이전</a>".formatted(url, pageNo-1));
			pagebar.append("\n");
		}
		
		// 2. 숫자
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo==cpage) {
				// 현재페이지인 경우
				pagebar.append("<span class='cPage'>%d</span>".formatted(pageNo));
				pagebar.append("\n");
			}
			else {
				// 아닌 경우
				pagebar.append("<a href='%s%d'>%d</a>".formatted(url, pageNo, pageNo));
				pagebar.append("\n");
			}
			
			pageNo++;
		}
		
		// 3. 다음
		if (pageNo > totalPage) {
			// 마지막 페이지가 이미 노출된 경우
		}
		else {
			pagebar.append("<a href='%s%d'>다음</a>".formatted(url, pageNo));
		}
		
		return pagebar.toString();
	}

}





