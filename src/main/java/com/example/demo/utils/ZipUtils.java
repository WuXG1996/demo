package com.example.demo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;


public class ZipUtils {
	private static final int BUFFER = 2048;

	/**
	 * ZIP压缩
	 * @param entryName 压缩条目名称
	 * @param input 压缩项目输入流
	 * @param out 压缩后输出流
	 * @throws IOException
	 */
	public static void zip(String entryName, InputStream input, OutputStream out) throws IOException {
		ZipOutputStream zipOut = new ZipOutputStream(out);
		BufferedInputStream bs = new BufferedInputStream(input);
		int count = 0;
		byte[] buf = new byte[BUFFER];
		zipOut.putNextEntry(new ZipEntry(entryName));
		while ((count = bs.read(buf)) > 0) {
			zipOut.write(buf, 0, count);
		}
		zipOut.closeEntry();
		zipOut.flush();
		zipOut.close();
	}

	/**
	 * ZIP压缩
	 * @param entryName 压缩条目名称
	 * @param input 压缩项目输入字节数组
	 * @param out 压缩后输出流
	 * @throws IOException
	 */
	public static void zip(String entryName, byte[] input, OutputStream out) throws IOException {
		ZipOutputStream zipOut = new ZipOutputStream(out);
		ByteArrayInputStream bis = new ByteArrayInputStream(input);
		int count = 0;
		byte[] buf = new byte[BUFFER];
		zipOut.putNextEntry(new ZipEntry(entryName));
		while ((count = bis.read(buf)) > 0) {
			zipOut.write(buf, 0, count);
		}
		zipOut.closeEntry();
		zipOut.flush();
		zipOut.close();
	}

	/**
	 * ZIP解压缩
	 * @param entryName 解压的条目名称
	 * @param input zip文件输入流
	 * @param out 解压缩后输出流
	 * @throws IOException
	 */
	public static void unzip(String entryName, InputStream input, OutputStream out) throws IOException {
		ZipInputStream zipIn = new ZipInputStream(input);
		ZipEntry entry;
		BufferedOutputStream bos = new BufferedOutputStream(out);
		while ((entry = zipIn.getNextEntry()) != null) {
			if (!entry.isDirectory() && StringUtils.equalsIgnoreCase(entryName, StringUtils.trim(entry.getName()))) {
				int count = 0;
				byte[] buf = new byte[BUFFER];
				while ((count = zipIn.read(buf)) > 0) {
					bos.write(buf, 0, count);
				}
				zipIn.closeEntry();
				break;
			}
		}
		bos.flush();
		bos.close();
		zipIn.close();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		byte[] bytes = new String("xxxxxxxxxdfdfdddfdf").getBytes();
		OutputStream out = new FileOutputStream("e:/test.zip");
		System.out.println("-----start zip------");
		ZipUtils.zip("test.txt", bytes, out);
		System.out.println("-----end zip------");
		Thread.sleep(2000);
		System.out.println("------start unzip------");
		InputStream in = new FileInputStream("e:/test2.zip");
		//out = new FileOutputStream("e:/test.txt");
		ZipUtils.unzip("test.txt", in, out);
		ZipInputStream zis = new ZipInputStream(in);
		ZipEntry entry;
		while (null != (entry = zis.getNextEntry())) {
			System.out.println(entry.getName());
			System.out.println("isDirectory:" + entry.isDirectory());
			if (!entry.isDirectory()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(zis));
				String line = reader.readLine();
				if (null != line)
					System.out.println(line);
			}
		}
		System.out.println("------end unzip------");
	}
}
