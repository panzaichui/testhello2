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
 * ���ݾ��������·�������ļ�
 * @author pzc
 *
 */
public class FindFileFomFtp {
	//����jar��   commons-loging-1.1.1   jsssh-core-0.2.9.jar
	//1����Զ�̷�����
	//2��ȡ�ļ���Ϣ
	@Test
	public void FindFileByPath(){
		SshClient client =new SshClient();
		try {
			client.connect("192.168.8.33");
			PasswordAuthenticationClient pwd=new PasswordAuthenticationClient();
			pwd.setUsername("epayftp");
			pwd.setPassword("epayftp20003");
			int result=client.authenticate(pwd);
			if(result==AuthenticationProtocolState.COMPLETE){   //�������
				SftpClient openSftpClient = client.openSftpClient();
				FileAttributes fileAttributes=openSftpClient.get("/chkfile/sit/acm/ACC_JNR_102000_20160921.dat");
				if(fileAttributes!=null){//�ļ�����
					UnsignedInteger64 unsignedInteger64=fileAttributes.getSize();
					long l=unsignedInteger64.longValue();
				}
			}
		} catch (Exception e) {
			System.out.println("û���ҵ��ļ�");
		}
		
		
		
	}

}
