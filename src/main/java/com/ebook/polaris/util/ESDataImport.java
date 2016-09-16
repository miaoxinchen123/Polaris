package com.ebook.polaris.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ESDataImport {
	public static void main(String[] args) throws UnknownHostException {
	        /* 
	         * 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象 
	         * 用完记得要关闭 
	         */  
	        @SuppressWarnings("resource")
	        
	        Settings settings = Settings.settingsBuilder().put("cluster.name","ebook").put("client.transport.sniff", true).build();
	        Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new   InetSocketTransportAddress(InetAddress.getByName("localhost"),   9300));
			
	        BulkRequestBuilder bulkRequest = client.prepareBulk(); 
	        
	        //读取文件生成json数组
	        String JsonContext = Util.ReadFile("C:\\book-3.txt");
	        System.out.println(JsonContext);
	        JSONArray jsonArray = JSONArray.fromObject(JsonContext);
	        
	        //循环遍历文件体统的
	        for (int i = 0; i < jsonArray.size(); i++) {  
	        	String index = "polaris"; // 相当于数据库名  
	        	String type = "ebook"; // 相当于表名  
	        	JSONObject jsonObject = jsonArray.getJSONObject(i);
		        @SuppressWarnings("unchecked")
				IndexRequest request = client  
		                .prepareIndex(index, type, jsonObject.get("md5").toString()).setSource(jsonObject)  
		                .request();  
		       
		        bulkRequest.add(request);  
	        }
	      
	        
	        
	        BulkResponse bulkResponse = bulkRequest.execute().actionGet();  
	        if (bulkResponse.hasFailures()) {  
	            System.out.println("批量导入索引错误");
	        }   
	    }  
}
