package com.qidi.crm.util;

import java.util.UUID;

/**
 * 文件上传的工具类
 */
public class UploadUtils {
	/**
	 * 解决目录下文件名重复的问题
	 */
	public static String getUuidFileName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String extions = fileName.substring(index);
		return UUID.randomUUID().toString().replace("-", "") + extions;
	}
	
	/**
	 * 目录分离的方法
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf;
		return "/" + d1 + "/" + d2;
	}
	
	public static void main(String[] args) {
		System.out.println(UploadUtils.getUuidFileName("aa.txt"));
	}
}
