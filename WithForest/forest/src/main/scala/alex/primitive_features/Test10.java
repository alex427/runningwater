package alex.primitive_features;

import java.io.OutputStreamWriter;

public class Test10 {

	/**
	 * 8�� ����г�һ���ַ�����ȫ�ַ���������ԭʼ�ַ�����û���ظ��ַ��� ���磺 ԭʼ�ַ�����"abc"�� ��ӡ�õ����������������� "a" "b"
	 * "c" "ab" "bc" "ca" "ba" "cb" "ac" "abc" "acb" "bac" "bca" "cab" "cba"
	 * 
	 * @author JX
	 * 
	 */
	/**
	 * ����г�һ���ַ�����ȫ�ַ���������ԭʼ�ַ�����û���ظ��ַ��� ���磺 ԭʼ�ַ�����"abc"����ӡ�õ����������������� "a" "b" "c"
	 * "ab" "bc" "ca" "ba" "cb" "ac" "abc" "acb" "bac" "bca" "cab" "cba"
	 */
	public static void main(String[] args) {
		String s = "abc";
		one(s);
		two(s);
		three(s);
	}

	public static void three(String s) {

		for (int i = 0; i < s.length(); i++) {
			String s1;
			if (i == s.length() - 1) {
				s1 = "" + s.charAt(i) + s.charAt(0) + s.charAt(1);
			} else if (i == s.length() - 2) {
				s1 = "" + s.charAt(i) + s.charAt(i + 1) + s.charAt(0);
			} else {
				s1 = "" + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2);
			}
			System.out.println(s1);
			StringBuffer s2 = new StringBuffer(s1);
			System.out.println(s2.reverse());
		}
	}

	public static void two(String s) {
		for (int i = 0; i < s.length(); i++) {
			String s1;
			if (i == s.length() - 1) {
				s1 = "" + s.charAt(i) + s.charAt(0);
			} else {
				s1 = "" + s.charAt(i) + s.charAt(i + 1);
			}
			System.out.println(s1);
			StringBuffer s2 = new StringBuffer(s1);
			System.out.println(s2.reverse());
		}
	}

	public static void one(String s) {
		char[] ch = s.toCharArray();
		for (char c : ch) {
			System.out.println(c);
		}
	}
}
