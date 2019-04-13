package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class HttpHelper {
	public static final String URL="http://dxt.dcjet.com.cn/Blc/Service/BlcService.ashx?do=ReceiveCiqState&access_token=8EB7AAC964E74BA787B63083D2D2C47F";
	public static String URLS=null;
	public static String checkVerifyStr=null;
	public static String CIQSERVER=null;
	public HttpHelper(){
		Properties p = new Properties();   
		  try {   
		   p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));   
		   URLS=p.getProperty("EZT");
		   checkVerifyStr=p.getProperty("checkVerifyStr");
		   CIQSERVER=p.getProperty("CIQSERVER");
		  } catch (IOException e1) {   
		   e1.printStackTrace();   
		  }    
	}
	public static StringBuffer submitPost(String url, String paramContent) {
		StringBuffer responseMessage = null;
		java.net.URLConnection connection = null;
		java.net.URL reqUrl = null;
		OutputStreamWriter reqOut = null;
		InputStream in = null;
		BufferedReader br = null;
		String param = paramContent;
		try {
			
			System.out.println("url=" + url + "?" + paramContent + "\n");
			System.out.println("===========post method start=========");
			responseMessage = new StringBuffer();
			reqUrl = new java.net.URL(url);
			connection = reqUrl.openConnection();
			connection.setDoOutput(true);
			reqOut = new OutputStreamWriter(connection.getOutputStream());
			reqOut.write(param);
			reqOut.flush();
			int charCount = -1;
			in = connection.getInputStream();
			
			br = new BufferedReader(new InputStreamReader(in, "GBK"));
			while ((charCount = br.read()) != -1) {
				responseMessage.append((char) charCount);
			}
			
			System.out.println(responseMessage);
			System.out.println("===========post method end=========");
		} catch (Exception ex) {
			System.out
			.println("url=" + url + "?" + paramContent + "\n e=" + ex);
		} finally {
			try {
				in.close();
				reqOut.close();
			} catch (Exception e) {
				System.out
				.println("paramContent=" + paramContent + "|err=" + e);
			}
			
		}
		return responseMessage;
	}
	public static String generateParam(String declNo,String Status){
		StringBuilder sb=new StringBuilder();
		
		return sb.append("requestData={'Serial':'").append(declNo).append("','State':'")
				.append(Status).append("'}").toString();
	}
	public static void main(String[] args) {
		/*submitPost(URL,
				"requestData={\"Serial\":\"123567890\",\"State\":\"0\"}");*/
		
		//String params=generateParam("1234567890","0");
		//System.out.println(new HttpHelper().URLS);
		//submitPost(new HttpHelper().URLS, params);
		System.out.println(Runtime.getRuntime().totalMemory()
				+"\\\\"+Runtime.getRuntime().freeMemory());
	}

}
