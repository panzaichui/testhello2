package com.murong.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.io.UnsignedInteger64;
import com.sshtools.j2ssh.sftp.FileAttributes;
import com.sshtools.j2ssh.sftp.SftpFile;

/**
 * 根据具体给出的路径查找文件
 * @author pzc
 *
 */
public class FindFileFomFtp {
	//所需jar包   commons-loging-1.1.1   jsssh-core-0.2.9.jar
	//1连接远程服务器
	//2获取文件信息
	@Test
	public void FindFileByPath(){
		SshClient client =new SshClient();
		try {
			client.connect("192.168.8.33");
			PasswordAuthenticationClient pwd=new PasswordAuthenticationClient();
			pwd.setUsername("epayftp");
			pwd.setPassword("epayftp20003");
			int result=client.authenticate(pwd);
			if(result==AuthenticationProtocolState.COMPLETE){   //连接完成
				SftpClient openSftpClient = client.openSftpClient();
				FileAttributes fileAttributes=openSftpClient.get("/chkfile/sit/acm/ACC_JNR_102000_20160921.dat");
				if(fileAttributes!=null){//文件存在
					UnsignedInteger64 unsignedInteger64=fileAttributes.getSize();
					long l=unsignedInteger64.longValue();
				}
			}
		} catch (Exception e) {
			System.out.println("没有找到文件");
		}
		
		
		
	}

}
