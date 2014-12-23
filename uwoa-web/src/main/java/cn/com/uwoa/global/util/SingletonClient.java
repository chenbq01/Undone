package cn.com.uwoa.global.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import cn.emay.sdk.client.api.Client;


public class SingletonClient {
	private static Client client=null;
	private static Client adclient=null;
	private SingletonClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(client==null){
			try {
				client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public synchronized static Client getAdClient(String softwareSerialNo,String key){
		if(adclient==null){
			try {
				adclient=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return adclient;
	}
	public synchronized static Client getAdClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(adclient==null){
			try {
				adclient=new Client(bundle.getString("adsoftwareSerialNo"),bundle.getString("adkey"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return adclient;
	}
	public static void main(String str[]) throws Exception{
		for(int i = 0; i < 200; i++){
			System.out.println(SingletonClient.getClient().getBalance());
		}
	}
	
	
	
	
	
}
