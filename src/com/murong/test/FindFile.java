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
 * ����   ��FTP�����������ļ����ļ��Ĵ�С
 * @author pzc
 *
 */

public class FindFile {
	//����jar��   commons-loging-1.1.1   jsssh-core-0.2.9.jar
	//1����Զ�̷�����
	//2�ݹ�������е��ļ�
	//3�����ļ�����������
	//4�鿴���ļ��Ĵ�С
   //����Ҫ�ҵ��ļ�����
	/**
	 * ����FTP������
	 */
	public  void  findByFtp(String fileName){
		SshClient client =new SshClient();
		List<SftpFile> myList=new ArrayList<SftpFile>();
		try {
			client.connect("192.168.8.33");
			//�����û���������
			PasswordAuthenticationClient pwd=new PasswordAuthenticationClient();
			pwd.setUsername("epayftp");
			pwd.setPassword("epayftp20003");
			int result=client.authenticate(pwd);
			if(result==AuthenticationProtocolState.COMPLETE){   //�������
				System.out.println("-------------"+result);
				//��ȡĿ¼�����е��ļ�
				SftpClient openSftpClient = client.openSftpClient();
				List<SftpFile> list = openSftpClient.ls("/app/data/epayftp");
				//forѭ������
				for(SftpFile file:list){
					if(fileName.equals(file.getFilename())){
						myList.add(file);
						System.out.println("�ҵ�"+fileName+"�ļ�");
					}
					if(file.isDirectory()&&!(".".equals(file.getFilename()))&&!("..".equals(file.getFilename()))){
						//������ļ��Ǹ�Ŀ¼ ��ݹ�
						showAllFiles(file,openSftpClient,myList,fileName);
					}
				}
				//��ȡ�ļ��Ĵ�С
				if(myList!=null){
					getFileLength(myList, openSftpClient);
				}
				System.out.println("������һ������:"+myList.size()+"���ļ�");		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ݹ�������е��ļ�
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
					System.out.println("�ҵ�"+fileName+"�ļ�");
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
	 * ����ļ�����
	 * @param myList
	 * @param openSftpClient
	 */
	public void getFileLength(List<SftpFile> myList,SftpClient openSftpClient){
		for(SftpFile f:myList){
			 try {
				OutputStream os = new FileOutputStream("F:/mail/"+f.getFilename());
				openSftpClient.get(f.getAbsolutePath(), os);
                File file=new File("F:/mail/"+f.getFilename()); 
                System.out.println("�ļ��ĳ���Ϊ:"+file.length()+"���ֽ�");
                System.out.println("�ļ��ľ���·��Ϊ:"+f.getAbsolutePath());
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
