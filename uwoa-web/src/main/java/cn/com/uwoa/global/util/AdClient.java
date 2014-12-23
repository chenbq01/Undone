package cn.com.uwoa.global.util;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.xml.rpc.ServiceException;

import cn.b2m.eucp.sdkhttp.Mo;
import cn.b2m.eucp.sdkhttp.SDKServiceBindingStub;
import cn.b2m.eucp.sdkhttp.SDKServiceLocator;
import cn.b2m.eucp.sdkhttp.StatusReport;
public class AdClient {
private String softwareSerialNo;
private String key;
	public AdClient(String sn,String key) {
		this.softwareSerialNo=sn;
		this.key=key;
		init();
	}
	
	SDKServiceBindingStub binding;
	
	
	public void init() {
		
		 try {
			 
            binding = (SDKServiceBindingStub)
                          new SDKServiceLocator().getSDKService();
		 }
        catch (Exception e) {
            e.printStackTrace();
        }
        
	}
	
	public int chargeUp(  String cardNo,String cardPass)
			throws RemoteException {
		int value=-1;
		value=binding.chargeUp(softwareSerialNo, key, cardNo, cardPass);
		return value;
	}

	public double getBalance() throws RemoteException {
		double value=0.0;
		value=binding.getBalance(softwareSerialNo, key);
		return value;
	}

	public double getEachFee( ) throws RemoteException {
		double value=0.0;
		value=binding.getEachFee(softwareSerialNo, key);
		return value;
	}
	public List<Mo> getMO( ) throws RemoteException {
		Mo[] mo=binding.getMO(softwareSerialNo, key);
		
		if(null == mo){
			return null;
		}else{
			List<Mo> molist=Arrays.asList(mo);
		    return molist;
		}
	}
	

	public List<StatusReport> getReport( )
			throws RemoteException {
		StatusReport[] sr=binding.getReport(softwareSerialNo, key);
		if(null!=sr){
			return Arrays.asList(sr);
		}else{
			return null;
		}
	}


	public int logout( ) throws RemoteException {
		int value=-1;
		value=binding.logout(softwareSerialNo, key);
		return value;
	}

	public int registDetailInfo(
			String eName, String linkMan, String phoneNum, String mobile,
			String email, String fax, String address, String postcode
) throws RemoteException {
		int value=-1;
		value=binding.registDetailInfo(softwareSerialNo, key, eName, linkMan, phoneNum, mobile, email, fax, address, postcode);
		return value;
	}

	public int registEx(String password)
			throws RemoteException {
		int value=-1;
		value=binding.registEx(softwareSerialNo, key, password);
		return value;
	}

	public int sendSMS( String[] mobiles, String smsContent, String addSerial,int smsPriority,long smsId)
			throws RemoteException {
		int value=-1;
		value=binding.sendSMS(softwareSerialNo, key,"", mobiles, smsContent, addSerial, "gbk", smsPriority,smsId);
		return value;
	}
	
	public int sendScheduledSMSEx(String[] mobiles, String smsContent,String sendTime,String srcCharset,long smsId)
	throws RemoteException {
      int value=-1;
      value=binding.sendSMS(softwareSerialNo, key, sendTime, mobiles, smsContent, "", srcCharset, 3,smsId);
      return value;
	}
	
	
	/**
	 * @author YinZhiwei
	 * @param softwareSerialNo
	 * @param key
	 * @param sendTime
	 * @param mobile
	 * @param smsContent
	 * @param addSerial
	 * @param srcCharset
	 * @param smsPriority
	 * @param smsId
	 * @return
	 * @throws RemoteException
	 * date 2013-06-09
	 */
	public int sendSMSExMsgId(String sendTime,String [] mobile,String smsContent,String addSerial,String srcCharset,int smsPriority,long smsId)throws RemoteException{
		 int value=-1;
	      value=binding.sendSMS(softwareSerialNo,key,sendTime, mobile,smsContent,addSerial,srcCharset, smsPriority,smsId);
	      return value;
	}
	

	public int serialPwdUpd( String serialPwd, String serialPwdNew)
			throws RemoteException {
		int value=-1;
		value=binding.serialPwdUpd(softwareSerialNo, key, serialPwd, serialPwdNew);
		return value;
	}
}
