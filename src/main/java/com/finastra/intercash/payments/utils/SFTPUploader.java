package com.finastra.intercash.payments.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPUploader {

	private JSch jSch = null;
	private Session jschSession = null;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 5000;

	public void uploadFile(String server, String port, String user, String pwd, String localFile, String remoteFile) throws Exception {

		try {
			jSch = new JSch();

			jschSession = jSch.getSession(user, server, Integer.parseInt(port));

			// authenticate using password
			jschSession.setPassword(pwd);

			// 10 seconds session timeout
			jschSession.connect(SESSION_TIMEOUT);

			Channel sftp = jschSession.openChannel("sftp");

			// 5 seconds timeout
			sftp.connect(CHANNEL_TIMEOUT);

			// 5 seconds timeout
			sftp.connect(CHANNEL_TIMEOUT);

			ChannelSftp channelSftp = (ChannelSftp) sftp;

			// transfer file from local to remote server
			channelSftp.put(localFile, remoteFile);

			channelSftp.exit();

		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}
	}
}