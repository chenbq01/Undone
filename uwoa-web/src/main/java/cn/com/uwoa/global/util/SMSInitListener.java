package cn.com.uwoa.global.util;

import java.rmi.RemoteException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SMSInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			//int a = SingletonClient.getClient().logout();
			//System.out.println("testLogout:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		try {
			int i = SingletonClient.getClient().registEx(
					bundle.getString("password"));
			System.out.println("testTegistEx:" + i);
			i = SingletonClient.getAdClient().registEx(
					bundle.getString("adpassword"));
			System.out.println("testTegistAdEx:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
