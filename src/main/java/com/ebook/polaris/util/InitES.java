package com.ebook.polaris.util;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class InitES {
	
	/**
	 * 静态,单例...
	 */
	private static TransportClient client;

	public static TransportClient initESClient() {
		Settings settings = Settings.settingsBuilder().put("cluster.name","ebook").put("client.transport.sniff", true).build();
		try {
			client = TransportClient.builder().settings(settings).build().addTransportAddress(new   InetSocketTransportAddress(InetAddress.getByName("localhost"),   9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static void closeESClient() {
		if (client != null) {
			client.close();
		}
	}

}
