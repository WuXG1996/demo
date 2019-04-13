package com.example.demo.utils;

import java.io.File;
import java.util.Collection;

import eu.medsea.mimeutil.MimeUtil;

/**
 * 检测文件的mime类型
 * @author Mr Wu
 *
 */
public class MimeUtils{
	public static void main(String[] args) {
		System.out.println(getFileMine("G://myeclipse2014/void/WebRoot/example/h5//movie.mp4"));
	}
	
	public static String getFileMine(String filePath){
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");  
        File f = new File(filePath);  
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(f);
        return mimeTypes.toString();
	}
}
