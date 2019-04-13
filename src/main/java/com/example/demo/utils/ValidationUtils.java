package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtils {

	private static String requiredMessage(String title) {
		StringBuffer sb = new StringBuffer(title);
		sb.append("不能为空!");
		return sb.toString();
	}

	/**
	 * 检查必须录入
	 * 
	 * @param o
	 * @param title
	 * @return
	 */
	public static String required(Object o, String title) {
		if (o == null){
			return requiredMessage(title);
		}else{
			if(o instanceof String){
				if (StringUtils.isEmpty((String)o))
					return requiredMessage(title);
			}
		}
		return "";
	}

	
	/**
	 * 验证必须录入和最大长度
	 * @param o
	 * @param maxLength
	 * @param title
	 * @return
	 */
	public static String requiredAndMaxLength(String o,int maxLength, String title){
		String err = required(o,title);
		if("".equals(err)){
			return maxLength(o, maxLength, title);
		}
		return err;
	}

	/**
	 * 验证最大长度
	 * 
	 * @param o
	 * @param maxLength
	 * @param title
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String maxLength(String o, int maxLength, String title){
		if (StringUtils.isNotEmpty(o)) {
			try {
				if (o.getBytes("UTF-8").length > maxLength) {
					StringBuffer sb = new StringBuffer(title);
					sb.append("的长度不能超过").append(maxLength).append("个字符(一个中文算3字符)！");
					return sb.toString();
				}
			} catch (Exception e) {
				if (o.length() > maxLength) {
					StringBuffer sb = new StringBuffer(title);
					sb.append("的长度不能超过").append(maxLength).append("个字符(一个中文算3字符)！");
					return sb.toString();
				}
			}
		}
		return "";
	}
	
	public static String isPhone(String o,String title){
		if (StringUtils.isNotEmpty(o)) {
			if(!regex("(^[0-9]{3,4}\\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\\([0-9]{3,4}\\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)",o)){
				StringBuffer sb = new StringBuffer(title);
				sb.append("不是合法的电话号码!");
				return sb.toString();
			}
		}
		return "";
	}
	
	public static String isMobile(String o,String title){
		if (StringUtils.isNotEmpty(o)) {
			if(!regex("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$",o)){
				StringBuffer sb = new StringBuffer(title);
				sb.append("不是合法的手机号码!");
				return sb.toString();
			}
		}
		return "";
	}
	
	public static String isPhoneOrMobile(String o,String title){
		if (StringUtils.isNotEmpty(o)) {
			if(!regex("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$",o) && !regex("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}",o)){
				StringBuffer sb = new StringBuffer(title);
				sb.append("不是合法的电话或手机号码!");
				return sb.toString();
			}
		}
		return "";
	}
	
	/**
	 * 验证是否为数字
	 * 
	 * @param o
	 * @param title
	 * @return
	 */
	public static String isNumber(String o,String title){
		if(StringUtils.isNotEmpty(o) && !regex("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?$", o)){
			StringBuffer sb = new StringBuffer(title);
			sb.append("不是合法的数字!");
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 验证是否为整数
	 * 
	 * @param o
	 * @param title
	 * @return
	 */
	public static String isDigits(String o,String title){
		if(StringUtils.isNotEmpty(o) && !regex("^\\d+$", o)){
			StringBuffer sb = new StringBuffer(title);
			sb.append("不是合法的整数!");
			return sb.toString();
		}
		return "";
	}
	
	public static String isDateISO(String o,String title){
		if(StringUtils.isNotEmpty(o) && !regex("^\\d{4}[\\/-]\\d{1,2}[\\/-]\\d{1,2}$", o)){
			StringBuffer sb = new StringBuffer(title);
			sb.append("不是合法的日期!");
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 验证 正则表达式
	 * 
	 * regex 正则表达式 
	 * value 所属字符串
	 * @return boolean
	 */
	public static boolean regex(String regex, String value) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public static void main(String[] arg){
		System.out.println("isPhone==="+isPhone("0755-77777777", "aa"));
		System.out.println("isDigits==="+isDigits("12", "aa"));
		System.out.println("isNumber==="+isNumber("11.22", "aa"));
		System.out.println("maxLength==="+maxLength("天天同1", 10,"aa"));
		System.out.println("required==="+required(new Double(11), "aa"));
	}

}
