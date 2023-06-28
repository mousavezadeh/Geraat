package mousavi.file.operation;

import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import mousavi.database.databasetest;
import mousavi.database.pdl_input;

	public class file_operation {
	public file_operation() {
		// TODO Auto-generated constructor stub
	}

	public String get_file_list_in_directory(String Directory)
	{

		String name="";

	File sdCardRoot = Environment.getExternalStorageDirectory();
	File yourDir = new File(sdCardRoot, Directory);
	File Temp=null;
	for (File f : yourDir.listFiles()) 
	{

	    if (f.isFile() )
	    {


			if (Temp==null) {

				Temp = f;
			}
			if (Temp!=null) {
				if (Temp.lastModified()<f.lastModified()) Temp=f;
			}
	 	    }

	}
	return Temp.getName();
	}

	public  Boolean DeleteFile(String Filepath) {
	File fdelete = new File(Filepath);
	if (fdelete.exists()) {
		if (fdelete.delete()) {
			System.out.println("file Deleted :" );
			return  true;
		} else {
			System.out.println("file not found" );
			return  false;
		}
	}
	return true;
}

	public Boolean check_exist_file(String filename) 
	{
		
		File f = new File(filename);
		 if(f.exists())
		 {
			 Log.e("errrrrrrrrr", "true");
			 return true;
		 }
		 else
		 {
			 return false;
		 }
		
	}

	public  String MakeFileNameWithDateTime()
	{
		return  new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.jpg'").format(new Date());
	}
	
	public Boolean MakeDirectory(String DirName)
	{
		//File dir=new File(sd.getAbsolutePath()+"/ABFA/Out");
		File sd=Environment.getExternalStorageDirectory();
		if (check_exist_file(DirName)==false)
		{
    	File dir=new File(sd.getAbsolutePath()+DirName);
    	dir.mkdirs();
    	return true;
    	}
		else
			return false;
		
	}

	private void CopyAssets(Context mycontext) {
	        AssetManager assetManager =mycontext.getAssets();
	        String[] files = null;
	        try {files = assetManager.list("Files");} catch (IOException e) {}
	        
	        for(String filename : files) {
	        	System.out.println("File name => "+filename);
	            InputStream in = null;
	            OutputStream out = null;
	            try {
	              in = assetManager.open("Files/"+filename);   // if files resides inside the "Files" directory itself
	              out = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() +"/ABFA/DataBase/" + "abfapdl_database");
	              copyFile(in, out);
	              in.close();
	              in = null;
	              out.flush();
	              out.close();
	              out = null;
	            } catch(Exception e) {
	            Log.e("Error copy data",e.getMessage());
	            }       
	        }
	    }

	private void copyFile(InputStream in, OutputStream out) throws IOException {
	        byte[] buffer = new byte[1024];
	        int read;
	        while((read = in.read(buffer)) != -1){
	          out.write(buffer, 0, read);
	        }
	    }

	public void copy_database_file(String file_name,Context mycontext)
	{
	try {
		Log.e("File Name-copy", file_name);
		if (check_exist_file(file_name))
		{
			Log.e("Exist", "exist databasr file");

		} else {
			Log.e("Not Exist", "not exist copyassets");
			CopyAssets(mycontext);

		}
	}catch (Exception Error){Log.e("copy_database_error", Error.getMessage());}
}

	public PackageInfo CurrentPackageInfo(Context context)
	{
	 PackageInfo CurrentPackageInfo;
	 PackageManager pm1=context.getPackageManager();
	 try 
	 {
	   return	CurrentPackageInfo=pm1.getPackageInfo(context.getPackageName(), 0);
	} catch (NameNotFoundException e)
	{
		return null;
	}
}
	public PackageInfo DownloadPackageInfo(Context context)
	{
try {
	PackageManager pm = context.getPackageManager();
	String Path=get_file_list_in_directory("/Abfa/Update");
	if (Path.compareTo("")==0) return  null;
	PackageInfo DownloadPackageInfo = pm.getPackageArchiveInfo(Environment.getExternalStorageDirectory() + "/Abfa/Update/" + Path, 0);
	return DownloadPackageInfo;
}catch (Exception error){Log.e("DownloadPackageInfo",error.getMessage());}
	return  null;
}

	    
}
