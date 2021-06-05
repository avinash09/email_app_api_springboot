package com.email;

import java.io.File;
import java.net.URL;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new App().file();
	}
	
	public void file()
	{
		URL url = this.getClass().getResource("/static/footer-img.png");
		File file = new File(url.getPath());
		System.out.println(file.exists());
	}
}
