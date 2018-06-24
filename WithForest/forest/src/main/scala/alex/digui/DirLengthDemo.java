package alex.digui;

import java.io.File;
import java.util.Scanner;

public class DirLengthDemo {


	public static void main(String[] args) {
		//File dir = new File("F:\\day06");
		//System.out.println(dir.length());				//直接获取文件夹的结果是0
		File dir = getDir();
		System.out.println(getDirLength(dir));
		
	}
	

	public static File getDir() {
		//1,创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个文件夹路径:");
		//2,定义一个无限循环
		while(true) {
			//3,将键盘录入的结果存储并封装成File对象
			String line = sc.nextLine();
			File dir = new File(line);
			//4,对File对象判断
			if(!dir.exists()) {
				System.out.println("您录入的文件夹路径不存在,请输入一个文件夹路径:");
			}else if(dir.isFile()) {
				System.out.println("您录入的是文件路径,请输入一个文件夹路径:");
			}else {
				//5,将文件夹路径对象返回
				return dir;
			}
		}
		
	}
	
	/*
	 * 统计该文件夹大小 
	 * 1,返回值类型long
	 * 2,参数列表File dir
	 */
	public static long getFileLength(File dir) {	//F:\downloadf
		//1,定义一个求和变量
		long len = 0;
		//2,获取该文件夹下所有的文件和文件夹listFiles();
		File[] subFiles = dir.listFiles();			//day07 Demo1_Student.class Demo1_Student.java
		//3,遍历数组
		for (File subFile : subFiles) {
			//4,判断是文件就计算大小并累加
			if(subFile.isFile()) {
				len = len + subFile.length();
			//5,判断是文件夹,递归调用
			}else {
				len = len + getFileLength(subFile);
			}
		}
		return len;
	}


	//获取文件夹大小, 就是统计文件夹下和子文件下问价的大小总和
	public static long getDirLength(File dir){
		long len =0; //不能用int, 值可能会比较大
		File[] files = dir.listFiles();
		for (File file :files){
			//边界条件
			if(file.isFile()){
				len = len+file.length();
			} else {
				len = len + getDirLength(file);
			}
		}
		return len;
	}


}
