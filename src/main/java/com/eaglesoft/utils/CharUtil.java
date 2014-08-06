package com.eaglesoft.utils;
import java.util.regex.Pattern;

/**
 * �ַ�ͨ�ù�����
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class CharUtil {


	// ����Unicode�����������ж����ĺ��ֺͷ���
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	// �������ж����ĺ��ֺͷ���
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}
	

   
		

	// ֻ���жϲ���CJK�ַ���CJKͳһ���֣�
	public static boolean isChineseByREG(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}
    // is ��
	public static boolean isCharIncluded(String str, String unicode) {
		if (str == null) {
			return false;
		}
		//Pattern pattern = Pattern.compile("[\\u4ebf]");
		Pattern pattern = Pattern.compile(unicode);
		return pattern.matcher(str.trim()).find();
	}
	// ֻ���жϲ���CJK�ַ���CJKͳһ���֣�
	public static boolean isChineseByName(String str) {
		if (str == null) {
			return false;
		}
		// ��Сд��ͬ��\\p ��ʾ������\\P ��ʾ������ 
		// \\p{Cn} ����˼Ϊ Unicode ��δ�������ַ��ı��룬\\P{Cn} �ͱ�ʾ Unicode���Ѿ��������ַ��ı���
		String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str.trim()).find();
	}
}