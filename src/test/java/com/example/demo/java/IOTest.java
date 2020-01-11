package com.example.demo.java;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;
import java.util.zip.*;

/**
 * 对IO流的测试用例
 * @author void
 * 2018年3月20日下午9:07:05
 */
public class IOTest {

	/*public static void main(String[] args) {
		IOTest t = new IOTest();
		try {
			t.test18();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 创建一个新文件
	 */
	@Test
	public void test1(){
		File f = new File("F://hello.txt");
		try{
			f.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * File类的2个常量
	 */
	public void test2(){
		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
		
		String fileName = "E:"+File.separator+"hello.txt";
		File f = new File(fileName);
		try{
			f.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件
	 */
	public void test3(){
		String fileName = "E:"+File.separator+"hello.txt";
		File f = new File(fileName);
		if(f.exists()){
			f.delete();
		}else{
			System.out.println("文件不存在");
		}
	}
	
	/**
	 * 创建文件夹
	 */
	public void test4(){
		String fileName = "E:"+File.separator+"hello";
		File f = new File(fileName);
		f.mkdir();
	}
	
	/**
	 * 列出文件夹下文件
	 */
	public void test5(){
		String fileName = "E:"+File.separator;
		File f = new File(fileName);
		String[] str = f.list();
		for(int i=0;i<str.length;i++){
			System.out.println(str[i]);
		}
	}
	
	/**
	 * 判断一个指定的路径是否为目录
	 */
	public void test6(){
		String fileName = "D:"+File.separator+"test";
		File f = new File(fileName);
		if(f.isDirectory()){
			System.out.print("YES");
		}else{
			System.out.println("NO");
		}
	}
	
	/**
	 * 检索指定目录
	 */
	public void test7(){
		String fileName = "E:"+File.separator+"图片";
		File f = new File(fileName);
		print(f);
	}
	
	public static void print(File f){
		if(f!=null){
			if(f.isDirectory()){
				File[] fileArray = f.listFiles();
				if(fileArray!=null){
					for(int i=0;i<fileArray.length;i++){
						//递归调用
						print(fileArray[i]);
					}
				}
			}else{
				System.out.println(f);
			}
		}
	}
	
	/**
	 * 向文件追加内容
	 * @throws IOException
	 */
	public void test8() throws IOException{
		String fileName = "G:"+File.separator+"hello.txt";
		File f = new File(fileName);
		OutputStream out = new FileOutputStream(f,true);
		String str = "Guess I'm a sucker for you";
		byte[] b = str.getBytes();
		for(int i=0;i<b.length;i++){
			out.write(b[i]);
		}
		out.close();
	}
	
	/**
	 * 复制文件
	 * @throws IOException
	 */
	public void test9() throws IOException{
		String[] array = {"G://hello.txt","G://test.txt"};
		File file1 = new File(array[0]);
		File file2 = new File(array[1]);
		if(!file1.exists()){
			System.out.println("被复制的文件不存在！");
			System.exit(1);
		}
		InputStream input = new FileInputStream(file1);
		OutputStream output = new FileOutputStream(file2);
		if(input!=null&&output!=null){
			int temp = 0;
			while((temp=input.read())!=(-1)){
				output.write(temp);
			}
		}
		input.close();
		output.close();
	}
	
	/**
	 * 用内存流操作字符转为大写字母
	 * 内容操作流一般用来生成一些临时信息,避免了删除的麻烦
	 * @throws IOException
	 */
	public void test10() throws IOException{
		String str = "rolling in the deep";
		ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int[] number = new int[1024];
		int i = 0;
		int temp = 0;
		while((temp = input.read())!=-1){//temp其实就是每个字符的assic码值,用char字符转码后变成英文字母
			number[i] = temp;
			i++;
			char c = (char)temp;
			output.write(Character.toUpperCase(c));
		}
		String outStr = output.toString();
		input.close();
		output.close();
		System.out.println(outStr);
	}
	
	/**
	 * Scanner简单键盘输入测试
	 */
	public void test11(){
		Scanner scan = new Scanner(System.in);
		//读一个整数
		int temp = scan.nextInt();
		System.out.println(temp);
		//读一个浮点数
		float flo = scan.nextFloat();
		System.out.println(flo);
		
		scan.close();
	}
	
	/**
	 * 从文件中读取内容，但是遇到空格会停止
	 */
	public void test12(){
		File file = new File("G:"+File.separator+"hello.txt");
		Scanner sca = null;
		try{
			sca = new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String str = sca.next();
		System.out.println("从文件中读取的内容是:"+str);
	}
	
	/**
	 * 压缩文件
	 * @throws IOException
	 */
	public void test13() throws IOException{
		File file = new File("G:"+File.separator+"hello.txt");
		File zipFile = new File("G:"+File.separator+"hello.zip");
		InputStream input = new FileInputStream(file);
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.putNextEntry(new ZipEntry(file.getName()));
		//设置解压的注释
		zipOut.setComment("just a test");
		int temp = 0;
		while((temp=input.read())!=-1){
			zipOut.write(temp);
		}
		input.close();
		zipOut.close();
	}
	
	/**
	 * 批量压缩,压缩指定目录
	 * @throws IOException
	 */
	public void test14() throws IOException{
		File file = new File("G:"+File.separator+"test");
		File zipFile = new File("G:"+File.separator+"test.zip");
		InputStream input = null;
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.setComment("wuxianggan");
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i=0;i<files.length;i++){
				input = new FileInputStream(files[i]);
				zipOut.putNextEntry(new ZipEntry(file.getName()+File.separator+files[i].getName()));//塞入新的zip条目
				int temp = 0;
				while((temp = input.read())!=-1){
					zipOut.write(temp);
				}
				input.close();
			}
		}
		zipOut.close();
	}
	
	public void test15() throws ZipException, IOException{
		File file = new File("G:"+File.separator+"hello.zip");
		System.out.println(file.getName());
		ZipFile zipFile = new ZipFile(file);
		System.out.println("压缩文件的名称为:"+zipFile.getName());
		zipFile.close();
	}
	
	/**
	 * 解压缩文件
	 * @throws ZipException
	 * @throws IOException
	 */
	public void test16() throws ZipException, IOException{
		File file = new File("G:"+File.separator+"hello.zip");//压缩文件(其中只有一个hello.txt)
		File outFile = new File("F:"+File.separator+"unZipFile.txt");//解压缩后的文件名
		ZipFile zipFile = new ZipFile(file);
		ZipEntry entry = zipFile.getEntry("hello.txt");
		InputStream input = zipFile.getInputStream(entry);
		OutputStream output = new FileOutputStream(outFile);
		int temp = 0;
		while((temp=input.read())!=-1){
			output.write(temp);
		}
		input.close();
		output.close();
		zipFile.close();
	}
	
	/**
	 * 批量解压缩
	 * @throws ZipException
	 * @throws IOException
	 */
	public void test17() throws ZipException, IOException{
		File file = new File("G:"+File.separator+"test.zip");
		File outFile = null;//解压出所有文件,所以先不指定
		ZipFile zipFile = new ZipFile(file);
		ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));//通过文件得到文件输入流,再包装成zip输入流
		ZipEntry entry = null;//之前是制定某个文件,此处不指定解压缩所有文件
		InputStream input = null;
		OutputStream output = null;
		while((entry = zipInput.getNextEntry())!=null){//利用zip流循环其中压缩实体,只要不为空就解压出来
			System.out.println("解压缩"+entry.getName()+"文件");
			outFile = new File("G:"+File.separator+"test2"+File.separator+entry.getName());//解压缩出来的文件
			if(!outFile.getParentFile().exists()){//如果上级目录不存在则创建
				outFile.getParentFile().mkdirs();//mkdir和mkdirs的区别:前者只会创建单级目录，后者创建多级目录
			}
			input = zipFile.getInputStream(entry);
			output = new FileOutputStream(outFile);
			int temp = 0;
			while((temp = input.read())!=-1){
				output.write(temp);
			}
			input.close();
			output.close();
		}
		zipInput.close();
		zipFile.close();
	}
	
	/**
	 * 输出序列化后的对象数据到txt文本
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void test18() throws FileNotFoundException, IOException{
		File file = new File("G:"+File.separator+"hello.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//		oos.writeObject(new SerialDemo("haha",22));
		oos.close();
	}
}
