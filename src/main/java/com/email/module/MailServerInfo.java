package com.email.module;

import java.util.Properties;

public class MailServerInfo {

	// variable for gmail
	private String host;

	// gmail port
	private String port;

	// mail ssl enable
	private boolean isSslEnable;

	// mail auth
	private boolean isAuth;

	private boolean enableDebug;

	// from | username
	private String from;

	// username
	private String username;

	// password
	private String password;

	private Properties mailProperties;

	public MailServerInfo(String host, String port, boolean isSslEnable, boolean isAuth, boolean enableDebug,
			String from, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.isSslEnable = isSslEnable;
		this.isAuth = isAuth;
		this.enableDebug = enableDebug;
		this.from = from;
		this.username = username;
		this.password = password;
		composeMailProperties();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public boolean isSslEnable() {
		return isSslEnable;
	}

	public void setSslEnable(boolean isSslEnable) {
		this.isSslEnable = isSslEnable;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public boolean isEnableDebug() {
		return enableDebug;
	}

	public void setEnableDebug(boolean enableDebug) {
		this.enableDebug = enableDebug;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Properties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}

	private void composeMailProperties() {
		Properties properties = System.getProperties();

		// setting important information to properties object
		properties.put("mail.smtp.host", this.host);
		properties.put("mail.smtp.port", this.port);
		properties.put("mail.smtp.ssl.enable", this.isSslEnable);
		properties.put("mail.smtp.auth", this.isAuth);

		this.mailProperties = properties;
	}

	@Override
	public String toString() {
		return "MailServerInfo [host=" + host + ", port=" + port + ", isSslEnable=" + isSslEnable + ", isAuth=" + isAuth
				+ ", enableDebug=" + enableDebug + ", from=" + from + ", username=" + username + ", password="
				+ password + "]";
	}

}
