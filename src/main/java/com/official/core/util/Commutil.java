package com.official.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * @author ShawnXII
 * @Version 1.0
 */
public final class Commutil {
	/**
	 * 默认验证码取值范围
	 */
	public static final String DEFAULT_CAPTCHE_RANGE = "123456789abcdefghjkmnpqrstuvwxy";
	/**
	 * 获取全拼 小写
	 */
	public static final int PINYIN_TRANSFORMATION_FULL_LOWER = 0;
	/**
	 * 获取全拼 大写
	 */
	public static final int PINYIN_TRANSFORMATION_FULL_CAPITAL = 1;
	/**
	 * 获取首拼 小写
	 */
	public static final int PINYIN_TRANSFORMATION_FIRST_LOWER = 2;
	/**
	 * 获取首拼音 大写
	 */
	public static final int PINYIN_TRANSFORMATION_FIRST_CAPITAL = 3;
	/**
	 * 姓名拼音 格式 首字母大写格式 每个字拼完加空格
	 */
	public static final int PINYIN_TRANSFORMATION_NAME = 4;
	/**
	 * 获取首字母 如王翔 返回为w 小写
	 */
	public static final int PINYIN_TRANSFORMATION_FIRST_ONE_LOWER = 5;
	/**
	 * 获取首字母 如王翔 返回为W 大写
	 */
	public static final int PINYIN_TRANSFORMATION_FIRST_ONE_CAPITAL = 6;

	private static IdGenerate idGenerate = null;

	private Commutil() {
		super();
	}

	/**
	 * 对象转String
	 * 
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		return null2String(obj, "");
	}

	/**
	 * 对象转String
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static String null2String(Object obj, String defaultV) {
		return (obj == null || StringUtils.isBlank(obj.toString().trim())) ? defaultV : obj.toString().trim();
	}

	/**
	 * 对象转int
	 * 
	 * @param obj
	 * @return
	 */
	public static int null2Int(Object obj) {
		return null2Int(obj, -1);
	}

	/**
	 * 对象转int
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static int null2Int(Object obj, int defaultV) {
		int v = defaultV;
		if (obj != null) {
			try {
				v = new BigDecimal(null2String(obj)).intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return v;
	}

	/**
	 * 对象转Double 默认保留2位小数
	 * 
	 * @param obj
	 * @return
	 */
	public static double null2Double(Object obj) {
		return null2Double(obj, -1);
	}

	/**
	 * 对象转Double 默认保留2为小数
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static double null2Double(Object obj, double defaultV) {
		return null2Double(obj, -1, 2);
	}

	/**
	 * 对象转Double
	 * 
	 * @param obj
	 * @param defaultV
	 * @param scale
	 * @return
	 */
	public static double null2Double(Object obj, double defaultV, int scale) {
		if (scale < 0) {
			scale = 0;
		}
		double v = defaultV;
		if (obj != null) {
			try {
				BigDecimal big = new BigDecimal(null2String(obj));
				v = big.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			} catch (Exception e) {
			}
		}
		return v;
	}

	/**
	 * 对象转Float
	 * 
	 * @param obj
	 * @return
	 */
	public static float null2Float(Object obj) {
		return null2Float(obj, -1F);
	}

	/**
	 * 对象转Float
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static float null2Float(Object obj, float defaultV) {
		float v = defaultV;
		if (obj != null) {
			try {
				v = new BigDecimal(null2String(obj)).floatValue();
			} catch (Exception e) {
				v = defaultV;
			}
		}
		return v;
	}

	/**
	 * 对象转long
	 * 
	 * @param obj
	 * @return
	 */
	public static long null2Long(Object obj) {
		return null2Long(obj, -1);
	}

	/**
	 * 对象转long
	 * 
	 * @param obj
	 * @param defaultV
	 * @return
	 */
	public static long null2Long(Object obj, long defaultV) {
		long v = defaultV;
		if (obj != null) {
			try {
				v = new BigDecimal(null2String(obj)).longValue();
			} catch (Exception e) {

			}
		}
		return v;
	}

	/**
	 * 对象转Boolean 支持yes/no true/false 0/1
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean null2Boolean(Object obj) {
		return null2Boolean(obj, false);
	}

	/**
	 * 对象转Boolean 支持yes/no true/false 0/1
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean null2Boolean(Object obj, boolean defaultV) {
		boolean v = defaultV;
		String s = null2String(obj);
		if ("yes".equalsIgnoreCase(s) || "0".equals(s) || "true".equalsIgnoreCase(s)) {
			v = true;
		}
		if ("no".equalsIgnoreCase(s) || "1".equals(s) || "false".equalsIgnoreCase(s)) {
			v = false;
		}
		return v;
	}

	/**
	 * 是否是数字
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(Object obj) {
		String v = null2String(obj);
		String reg = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}

	/**
	 * 是否是邮箱
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmail(Object obj) {
		String v = null2String(obj);
		String reg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}

	/**
	 * 是否是手机号码
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isMobile(Object obj) {
		String v = null2String(obj);
		String reg = "^[1][3,4,5,7,8][0-9]{9}$";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(v);
		return m.matches();
	}

	/**
	 * 获取随机验证码
	 * 
	 * @param size
	 * @return
	 */
	public static String getCatche(int size) {
		return getCatche(size, DEFAULT_CAPTCHE_RANGE);
	}

	/**
	 * 获取手机的验证码
	 * 
	 * @param size
	 * @param range
	 * @return
	 */
	public static String getCatche(int size, String range) {
		String randString = range;
		String str = "";
		while (str.length() < size) {
			str += randString.charAt(RandomUtils.nextInt(randString.length()));
		}
		return str.toUpperCase();
	}

	/**
	 * 密码加密算法
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		String encodePassword = Md5Encrypt.md5(password);
		String findpassword = Md5Encrypt.md5(encodePassword + salt);
		return findpassword;
	}

	/**
	 * 用户名隐藏
	 * 
	 * @param obj
	 * @return
	 */
	public static String hidename(Object obj) {
		String v = null2String(obj);
		// mobile
		if (StringUtils.isBlank(v)) {
			return v;
		}
		int len = v.length();
		if (len <= 4) {
			return v;
		}
		if (isMobile(obj)) {
			return v.substring(0, 3).concat("****").concat(v.substring(7));
		}
		// email
		if (isEmail(obj)) {
			String str = v.split("@")[0];
			int len1 = str.length();
			if (len1 < 4) {
				String str1 = "";
				for (int i = 0; i < len1; i++) {
					str1 += "*";
				}
				return str1.concat(v.substring(len1));
			}
			int x = len1 - 4;
			int a = x / 2;
			return str.substring(0, a).concat("****").concat(v.substring(a + 4));
		}
		int x = len - 4;
		int a = x / 2;
		return v.substring(0, a).concat("****").concat(v.substring(a + 4));
	}

	/**
	 * 随机生成ID
	 * 
	 * @return
	 */
	public static Long generateId() {
		if (idGenerate == null) {
			synchronized (IdGenerate.class) {
				if (idGenerate == null) {
					idGenerate = new IdGenerate(5);
				}
			}
		}
		return idGenerate.generateId();
	}
	/**
	 * 获取中文拼音
	 * @param str
	 * @return
	 */
	public static String TransformationPy(String str) {
		return TransformationPy(str,Commutil.PINYIN_TRANSFORMATION_NAME);
	}
	/**
	 * 获取首拼音
	 * @param str
	 * @param type
	 * @return
	 */
	public static String TransformationPy(String str, int type) {
		char[] t1 = str.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		boolean isSp = false;
		boolean isOne = false;
		String kg = "";
		if (type == PINYIN_TRANSFORMATION_FULL_LOWER) {
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			kg = " ";
		} else if (type == PINYIN_TRANSFORMATION_FULL_CAPITAL) {
			t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			kg = " ";
		} else if (type == PINYIN_TRANSFORMATION_FIRST_LOWER) {
			isSp = true;
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		} else if (type == PINYIN_TRANSFORMATION_FIRST_CAPITAL) {
			isSp = true;
			t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		} else if (type == PINYIN_TRANSFORMATION_FIRST_ONE_LOWER) {
			isOne = true;
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		} else if (type == PINYIN_TRANSFORMATION_FIRST_ONE_CAPITAL) {
			isOne = true;
			t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		} else {
			kg = " ";
			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		}
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		StringBuffer buffer = new StringBuffer();
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				if (isOne) {
					if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
						t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
						buffer.append(t2[0].substring(0, 1));
						break;
					} else {
						char ch = t1[i];
						if (('a' <= ch && 'z' >= ch) || 'A' <= ch && 'Z' >= ch) {
							buffer.append(ch);
							break;
						}
					}
					continue;
				}
				if (isSp) {
					if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
						t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
						buffer.append(t2[0].substring(0, 1).toUpperCase());
					} else {
						buffer.append(java.lang.Character.toString(t1[i]));
					}
					continue;
				}
				if (i > 0) {
					buffer.append(kg);
				}
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					buffer.append(t2[0]);
				} else {
					buffer.append(java.lang.Character.toString(t1[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(TransformationPy("阿玛尼", Commutil.PINYIN_TRANSFORMATION_FIRST_ONE_CAPITAL));
	}

}
