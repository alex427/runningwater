package alex.digui;

import java.io.File;

public class FileNamePrinter {

	/**
	 * 需求:4,从键盘接收一个文件夹路径,把文件夹中的所有文件以及文件夹的名字按层级打印, 例如:
	 * 把文件夹中的所有文件以及文件夹的名字按层级打印
	 * 分析:
	 * 1,获取所有文件和文件夹,返回的File数组
	 * 2,遍历数组
	 * 3,无论是文件还是文件夹,都需要直接打印
	 * 4,如果是文件夹,递归调用
	 * 	day07
	 * 		day08
	 * 			xxx.jpg
	 * 			yyy.txt
	 * 		Demo1_Consturctor.class
	 * 		Demo1_Consturctor.java
	 * 	Demo1_Student.class
	 * 	Demo1_Student.java
	 */
	public static void main(String[] args) {
		//File dir = DirLengthDemo.getDir();				//获取文件夹路径
		//printLev(dir,0);

        File dir = new File("F:\\instantclient_11_2");
        printLev(dir,0);
        printnames(dir);
	}

	public static void printLev(File dir,int lev) {
		//1,把文件夹中的所有文件以及文件夹的名字按层级打印
		File[] subFiles = dir.listFiles();
		//2,遍历数组
		for (File subFile : subFiles) {
			for(int i = 0; i <= lev; i++) {
				System.out.print("\t");
			}
			//3,无论是文件还是文件夹,都需要直接打印
			System.out.println(subFile);
			//4,如果是文件夹,递归调用
			if(subFile.isDirectory()) {
				//printLev(subFile,lev + 1);
				printLev(subFile,++lev);
			}
		}
	}



	//递归好实现, 如何按层级打印
	public static void printnames(File dir){
	    File[] files = dir.listFiles();
	    for(File file : files){
	        if(file.isFile()){
	            System.out.println(file.getName());
            } else {
	            System.out.println("----------"+file.getName()+"-------------");
                printnames(file);
                System.out.println("-----------------------");
            }
        }
    }























}
