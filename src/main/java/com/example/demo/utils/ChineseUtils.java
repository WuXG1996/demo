package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


/**
 * 中文汉字工具类
 * 
 * @author DAYDAYUP
 * 
 */
public class ChineseUtils {

	private static String[] hanArr = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private static String[] unitArr = { "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };

	private static String[] hanArrSimple = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	private static String[] unitArrSimple = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

	/**
	 * 数字转中文数字（不能不小数点）
	 * @param numStr
	 * @return
	 */
	public static String numberToChinese(String numStr) {
		String result = "";
		int numLen = numStr.length();
		for (int i = 0; i < numLen; i++) {
			int num = numStr.charAt(i) - 48;
			if (i != numLen - 1 && num != 0) {
				result += hanArrSimple[num] + unitArrSimple[numLen - 2 - i];
			} else {
				result += hanArrSimple[num];
			}
		}
		return result;
	}
	
	/**
	 * 得到数字中指定位数对应的汉字,如果索引大于数字的长度返回零(护理忽略小数点)
	 * @param numStr
	 * @param index 索引，从右边开始，第一位为1,小数点不算
	 * @return
	 */
	public static String signleNum2Chinese(String numStr,int index){
		if(numStr ==null || "".equals(numStr))
			return "零";
		String str = numStr.replace(".", "");
		if(str.length() < index)
			return "零";
		String o = "";
		int begin,end;
		begin = str.length() - index;
		end = str.length() - index + 1;
		o = str.substring(begin,end);
		if("0".equals(o))
			return "零";
		if("1".equals(o))
			return "壹";
		if("2".equals(o))
			return "贰";
		if("3".equals(o))
			return "叁";
		if("4".equals(o))
			return "肆";
		if("5".equals(o))
			return "伍";
		if("6".equals(o))
			return "陆";
		if("7".equals(o))
			return "柒";
		if("8".equals(o))
			return "捌";
		if("9".equals(o))
			return "玖";
		return "零";
	}

	/**
	 * 阿拉伯数字转成指定长度的中文数字，保留小数点后两位，中文数字间可以插入字符串
	 * @param number 数字
	 * @param length 得到中文数字长度
	 * @param spacing 中间插入的字符串
	 * @return
	 */
	public static String numberToChinese(double number,int length,String spacing){
		DecimalFormat df = new DecimalFormat("#.00");
		String str = df.format(number);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++){
			sb.append(signleNum2Chinese(str,length - i ));
			if(StringUtils.isNotEmpty(spacing) && i < length - 1)
				sb.append(spacing);
		}
		return sb.toString();
	}
	
	/**
	 * 数字按小数点二位格式化，在前面加上符号，同时中间可以加入字符串
	 * @param number 数字
	 * @param spacing 中间插入的字符串
	 * @return
	 */
	public static String numberFormatAndAddSymbolByBefore(double number,String spacing){
		DecimalFormat df = new DecimalFormat("#.00");
		String str = df.format(number).replace(".", "");
		str = "¥" + str;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			sb.append(str.substring(i, i + 1));
			if(StringUtils.isNotEmpty(spacing) && i < str.length() - 1)
				sb.append(spacing);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * 获得阿拉伯数字对应的中文 最大只支持到9千9百九十九亿9千9百九十九万9千9百九十九
	 * @param number 要转换的数字
	 * @param depth 递归深度,使用时候直接给0即可
	 * @return String 数字的中文描述
	 */
	public static String getChinese(String number, int depth) {
		if (depth < 0) {
			depth = 0;
		}

		String chs = "";
		String src = number;
		if (src.length() > 4) {
			chs = getChinese(src.substring(0, src.length() - 4), depth + 1)
			+ getChinese(src.substring(src.length() - 4, src.length()),
			depth - 1);
		} else {
			for (int i = 0; i < src.length(); i++) {
				switch (src.charAt(i)) {
				case '0':
					chs = chs + "零";
					break;
				case '1':
					chs = chs + "壹";
					break;
				case '2':
					chs = chs + "贰";
					break;
				case '3':
					chs = chs + "叁";
					break;
				case '4':
					chs = chs + "肆";
					break;
				case '5':
					chs = chs + "伍";
					break;
				case '6':
					chs = chs + "陆";
					break;
				case '7':
					chs = chs + "柒";
					break;
				case '8':
					chs = chs + "捌";
					break;
				case '9':
					chs = chs + "玖";
					break;
				}
				switch (src.length() - 1 - i) {
				case 0: // 元
					if (depth == 0) {
						chs = chs + "元";
					}
					break;
				case 1: // 十
					chs = chs + "拾";
					break;
				case 2: // 百
					chs = chs + "佰";
					break;
				case 3: // 千
					chs = chs + "仟";
					break;
				}
			}
		}
		if (chs.length() > 0 && chs.lastIndexOf("零") == chs.length() - 1) {
			chs = chs.substring(0, chs.length() - 1);
		}
		if (depth == 1) {
			chs += "万";
		}
		if (depth == 2) {
			chs += "亿";
		}
		return chs;
	}

	public static String replaceNumber(String str){
		StringBuilder result = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		Character[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		List<Character> list = Arrays.asList(chars);
		for(int i=0;i<str.length();i++){
			Character character = str.charAt(i);
			if(list.contains(character)){
				//包含数字加入待处理的StringBuilder
				sb.append(character);
			}else if(sb.length()>0){
				result.append(numberToChinese(sb.toString()));
			}else{
				result.append(character);
			}
		}
		return result.toString();
	}

	public static void main(String[] arg){
//		System.out.println("========"+getChinese("40.21",0));
//		System.out.println("========"+getChinese("40.21",10));
//		System.out.println("========"+numberToChinese("c渝北厂区4000急聘保安"));
//		System.out.println("========"+signleNum2Chinese("40.21",1));
//		System.out.println("========"+numberToChinese(40.2d,7,"&nbsp;"));
//		System.out.println("========"+numberFormatAndAddSymbolByBefore(40.2d,"&nbsp;"));
		System.out.println(replaceNumber("c渝北厂区4000急聘保安"));
	}
}
