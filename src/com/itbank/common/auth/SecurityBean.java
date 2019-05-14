package com.itbank.common.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//������ �����ͺ��̽��� ��й�ȣ�� ���� ���� �Ϲ� �ؽ�Ʈ�� �������� ���� ������ ��ȣȭ�� �����ͷ� �־��
public class SecurityBean {
	//�Ϲ� �ؽ�Ʈ�� �ؽð�(16������)���� ��ȯ�Ͽ� ��ȯ�ϴ� �޼��� ����
	public String textToHash(String password) {
		StringBuilder sb=new StringBuilder();
		try {
			//���� ����� �˰��� �����ϱ�
			//�˰����� ���� : 224, 256 ,384, 512 ��
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			
			md.update(password.getBytes());//�ؽ�ȭ��ų �����͸� ����Ʈȭ(�� �ɰ��� �־���� ��)
			
			//��ȣȭ �� ����Ʈ ��ȯ�ޱ�
			byte[] data=md.digest();//�Ϲ� ����Ʈ�迭�� ��ȣȭ�� �����ͷ� ��ȯ
			//�迭�� ����ϱ⿣ ������ �����Ƿ� �ٽ� ��Ʈ��ȭ ��Ű��
			for(int i=0;i<data.length;i++) {
				sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
			}
			System.out.println("��ȣ�� ���̴� "+sb.toString().length());
			System.out.println("������ ��ȣ�� "+sb.toString());
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
