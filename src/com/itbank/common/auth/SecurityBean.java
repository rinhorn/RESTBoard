package com.itbank.common.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//앞으로 데이터베이스에 비밀번호를 넣을 때는 일반 텍스트로 넣지말고 보안 목적상 암호화된 데이터로 넣어보자
public class SecurityBean {
	//일반 텍스트를 해시값(16진수값)으로 변환하여 반환하는 메서드 정의
	public String textToHash(String password) {
		StringBuilder sb=new StringBuilder();
		try {
			//내가 사용할 알고리즘 선택하기
			//알고리즘의 종류 : 224, 256 ,384, 512 등
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			
			md.update(password.getBytes());//해쉬화시킬 데이터를 바이트화(즉 쪼개서 넣어줘야 함)
			
			//암호화 된 바이트 반환받기
			byte[] data=md.digest();//일반 바이트배열을 암호화된 데이터로 반환
			//배열로 사용하기엔 무리가 있으므로 다시 스트링화 시키자
			for(int i=0;i<data.length;i++) {
				sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
			}
			System.out.println("암호의 길이는 "+sb.toString().length());
			System.out.println("생성된 암호는 "+sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	public static void main(String[] args) {
		SecurityBean sb=new SecurityBean();
		sb.textToHash("apple");
		sb.textToHash("apple");
	}
}
