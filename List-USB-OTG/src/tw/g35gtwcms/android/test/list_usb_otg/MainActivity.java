package tw.g35gtwcms.android.test.list_usb_otg;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity{

	UsbManager mUsbManager = null;
	IntentFilter filterAttached_and_Detached = null;
	
	private static final String ACTION_USB_PERMISSION = "tw.g35gtwcms.android.test.list_usb_otg.USB_PERMISSION";	

	private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
	            synchronized (this) {
	                UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
	                
	                if(device != null){
	                      //	                   	
	                	Log.d("1","DEATTCHED-" + device);
	                  }
	            }
	        }
//
			if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
				synchronized (this) {
	                UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
	                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
	                	
	                    if(device != null){
	                      //
	                    	
	                    	Log.d("1","ATTACHED-" + device);
	                   }
	                } 
	                else {
	            		PendingIntent mPermissionIntent;
	            		mPermissionIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_ONE_SHOT);	                    
	                    mUsbManager.requestPermission(device, mPermissionIntent);
	                    
	                }					
	
				}
			}
			if (ACTION_USB_PERMISSION.equals(action)) {
				synchronized (this) {
	                UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
	                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
	                	
	                    if(device != null){
	                      readUdisk();
	                      File udisk_file =  new File(udisk);
	          			tv_source.append("\nUDISK "+udisk_file.getAbsolutePath());
	          			File[] files = udisk_file.listFiles();
	          			if (files != null) {
	          				tv_source.append("\nUDISK下存在"+files.length+"个文件");
	          				for (int i = 0; i < files.length; i++) {
	          					tv_result.append("\n 文件名 "+files[i].getName()+" 文件路径 "+files[i].getAbsolutePath());
	          				}
	          			}
	                   }
	                }					
	
				}
			}			
			
	    }
	};	
	
	String udisk = Environment.getExternalStorageDirectory().getParentFile().getParentFile()+File.separator+"udisk";
	
	TextView tv_result;
	
	TextView tv_source;
	
	
	public void  readUdisk(){
		File[] files = Environment.getRootDirectory().listFiles();
		int index = 0;
		tv_source.append(" \n遍历根目录 ");
		for (int i = 0; i < files.length; i++) {
			tv_source.append("\n文件名 "+files[i].getName()+" 文件路径 "+files[i].getAbsolutePath());	
			if (files[i].getName().equals("storage")) {
				index = i;
				break;
			}
		}
		
		File[] files2 = files[index].listFiles();
		for (int i = 0; i < files2.length; i++) {
			tv_result.append("\n文件名 "+files2[i].getName()+" 绝对路径 "+files2[i].getAbsolutePath());
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_result = (TextView) findViewById(R.id.tv2);
		tv_source = (TextView) findViewById(R.id.tv1);
		
		mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

		filterAttached_and_Detached = new IntentFilter(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		filterAttached_and_Detached.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
		filterAttached_and_Detached.addAction(ACTION_USB_PERMISSION);
		registerReceiver(mUsbReceiver, filterAttached_and_Detached);

		HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
		
		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		while(deviceIterator.hasNext()){
			UsbDevice device = deviceIterator.next();
			tv_source.append("\n usbManager 发现 USB设备 "+device.getDeviceName());
		}
		if (deviceList.size() > 0) {
			File udisk_file =  new File(udisk);
			tv_source.append("\nUDISK "+udisk_file.getAbsolutePath());
			File[] files = udisk_file.listFiles();
			if (files != null) {
				tv_source.append("\nUDISK下存在"+files.length+"个文件");
				for (int i = 0; i < files.length; i++) {
					tv_result.append("\n 文件名 "+files[i].getName()+" 文件路径 "+files[i].getAbsolutePath());
				}
			}
		}
//		readUdisk();
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mUsbReceiver);
	}}
