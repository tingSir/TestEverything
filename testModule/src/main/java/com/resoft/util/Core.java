package com.resoft.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 核心存储类，全局只保存一份，单例模式
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年4月23日 下午2:33:56
 * @version 1.0
 *
 */
public class Core {

	private static Core instance;

	public Core() {
		
	}

	public static Core getInstance() {
		if (instance == null) {
			synchronized (Core.class) {
				instance = new Core();
			}
		}
		return instance;
	}
	


	private Map<String, Object> chatmap = new HashMap<String, Object>();


	MyHttpClient myHttpClient = MyHttpClient.getInstance();

	int receivingRetryCount = 5;


	public int getReceivingRetryCount() {
		return receivingRetryCount;
	}

	public void setReceivingRetryCount(int receivingRetryCount) {
		this.receivingRetryCount = receivingRetryCount;
	}

	public MyHttpClient getMyHttpClient() {
		return myHttpClient;
	}

	public void setMyHttpClient(MyHttpClient myHttpClient) {
		this.myHttpClient = myHttpClient;
	}

	public Map<String, Object> getChatmap() {
		return chatmap;
	}

	public void setChatmap(Map<String, Object> chatmap) {
		this.chatmap = chatmap;
	}

	


}
