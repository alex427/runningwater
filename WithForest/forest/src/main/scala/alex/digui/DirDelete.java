package alex.digui;

import java.io.File;

public class DirDelete {


	public static void main(String[] args) {
		//File dir = DirLengthDemo.getDir();					//获取文件夹路径
        File dir = new File("F:\\aaa");
        deleteDir(dir);
	}


	public static void deleteFile(File dir) {	
		File[] subFiles = dir.listFiles();
		for (File subFile : subFiles) {
			if(subFile.isFile()) {
				subFile.delete();
			}else {
				deleteFile(subFile);			
			}
		}
		dir.delete();
	}


	public static void deleteDir(File dir){
	    File[] files = dir.listFiles();
	    for (File file : files){
	        if (file.isFile()){
	            file.delete();
            }else{
	            deleteFile(file);
            }
        }
        dir.delete();
    }

















}
