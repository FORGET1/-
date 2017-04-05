package com.pingdu.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TakeFilePathAndName {

	public static void main(String[] args) {
		// This is the path where the file's name you want to take.
		String path = "/E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\patrol_demo\\WEB-INF\\classes\\tasks\\10";
		getFile(path);
	}

	public static List<String> getFile(String path) {
		// get file list where the path has
		if ("\\".equals(File.separator)) {
			path = path.substring(1);
			path = path.replace("/", File.separator);
		}
		File file = new File(path);
		System.out.println(path);
		List<String> filePaths = new ArrayList<>();
		// get the folder list
		if (file.exists() && file.isDirectory() && file.list().length > 0) {
			File[] array = file.listFiles();

			for (int i = 0; i < array.length; i++) {
				if (array[i].isFile()) {
					// // only take file name
					// System.out.println("^^^^^" + array[i].getName());
					// // take file path and name
					// System.out.println("#####" + array[i]);
					// // take file path and name
					// System.out.println("*****" + array[i].getPath());
					filePaths.add(array[i].getPath());
				}
				// else if(array[i].isDirectory()){
				// getFile(array[i].getPath());
				// }
			}
		}
		return filePaths;
	}
}