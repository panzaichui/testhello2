package com.murong.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
/**
 * 需求   从FTP服务器查找文件，文件的大小
 * @author pzc
 *
 */

public class FindFile {
	//所需jar包   commons-loging-1.1.1   jsssh-core-0.2.9.jar
	//1连接远程服务器
	//2递归遍历所有的文件
	//3根据文件名进行搜索
	//4查看该文件的大小
   //设置要找的文件名字
	/**
	 * 连接FTP服务器
	 */
	public  void  findByFtp(String fileName){
		SshClient client =new SshClient();
		List<SftpFile> myList=new ArrayList<SftpFile>();
		try {
			client.connect("192.168.8.33");
			//设置用户名和密码
			PasswordAuthenticationClient pwd=new PasswordAuthenticationClient();
			pwd.setUsername("epayftp");
			pwd.setPassword("epayftp20003");
			int result=client.authenticate(pwd);
			if(result==AuthenticationProtocolState.COMPLETE){   //连接完成
				System.out.println("-------------"+result);
				//读取目录下所有的文件
				SftpClient openSftpClient = client.openSftpClient();
				List<SftpFile> list = openSftpClient.ls("/app/data/epayftp");
				//for循环遍历
				for(SftpFile file:list){
					if(fileName.equals(file.getFilename())){
						myList.add(file);
						System.out.println("找到"+fileName+"文件");
					}
					if(file.isDirectory()&&!(".".equals(file.getFilename()))&&!("..".equals(file.getFilename()))){
						//如果该文件是个目录 则递归
						showAllFiles(file,openSftpClient,myList,fileName);
					}
				}
				//获取文件的大小
				if(myList!=null){
					getFileLength(myList, openSftpClient);
				}
				System.out.println("服务器一共存在:"+myList.size()+"个文件");		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 递归遍历所有的文件
	 * @param file
	 * @param openSftpClient
	 */
	public void showAllFiles(SftpFile file,SftpClient openSftpClient,List<SftpFile> myList,String fileName){
		List<SftpFile> list;
		try {
			list =  openSftpClient.ls(file.getAbsolutePath());
			for(SftpFile fileDi:list){
				if(fileName.equals(fileDi.getFilename())){
					myList.add(fileDi);
					System.out.println("找到"+fileName+"文件");
				}
				if(fileDi.isDirectory()&&!(".".equals(fileDi.getFilename()))&&!("..".equals(fileDi.getFilename()))){
					showAllFiles(fileDi,openSftpClient,myList,fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 输出文件长度
	 * @param myList
	 * @param openSftpClient
	 */
	public void getFileLength(List<SftpFile> myList,SftpClient openSftpClient){
		for(SftpFile f:myList){
			 try {
				OutputStream os = new FileOutputStream("F:/mail/"+f.getFilename());
				openSftpClient.get(f.getAbsolutePath(), os);
                File file=new File("F:/mail/"+f.getFilename()); 
                System.out.println("文件的长度为:"+file.length()+"个字节");
                System.out.println("文件的绝对路径为:"+f.getAbsolutePath());
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
