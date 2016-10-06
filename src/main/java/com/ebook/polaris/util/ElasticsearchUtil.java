package com.ebook.polaris.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;


public class ElasticsearchUtil {

	static Log log = LogFactory.getLog(ElasticsearchUtil.class);
	
	/**
	 * 客户端对象
	 */
	private static TransportClient client = InitES.initESClient();
	
	static ElasticsearchUtil instance;
	
	 public static ElasticsearchUtil getInstance() {
	        synchronized (ElasticsearchUtil.class) {
	            if (instance == null) {
	                instance = new ElasticsearchUtil();
	            }
	       }
	      return instance;
	  }


	/**
	 * 
	 * @Title: searcher
	 * @Description: 搜索方法
	 * @param indexname
	 *            :类似数据库
	 * @param type
	 *            ：类似数据表
	 * @param nowPage
	 * @param pageSize
	 * @param keyWord
	 * @return SearchResponse
	 * @author kangj@doone.com.cn
	 * @date 2013-12-21 下午5:09:05
	 */
	public static SearchResponse searcher(String indexname, String type,
			int nowPage, int pageSize,String field ,String keyWord, String highFields) {
		SearchResponse response = null;
		if (client != null) {
			// 创建查询索引
			SearchRequestBuilder searchRequestBuilder = client
					.prepareSearch(indexname);
			// 设置查询索引表名称
			searchRequestBuilder.setTypes(type);
			searchRequestBuilder.setFrom((nowPage-1)*pageSize);
			// 设置查询结果集的最大条数
			searchRequestBuilder.setSize(pageSize-1);
			// 设置是否按查询匹配度排序
			searchRequestBuilder.setExplain(true);
			// 高亮字段
			searchRequestBuilder.addHighlightedField(highFields);
			
			searchRequestBuilder
					.setHighlighterPreTags("<font style='color:red'>");
			searchRequestBuilder.setHighlighterPostTags("</font>");
			searchRequestBuilder.setQuery(QueryBuilders.matchQuery(
					field, keyWord));
			
			// 最后就是返回搜索响应信息
			response = searchRequestBuilder.execute().actionGet();
		}
		return response;
	}

	/**
	 * 
	 * @Title: get
	 * @Description: 获取单条记录
	 * @param indexname
	 * @param type
	 * @param id
	 * @return String
	 */
	public static Map<String,Object> get(String indexname, String type, String id) {
		if (client != null) {
			// 在这里创建我们要索引的对象
			GetResponse response = client.prepareGet(indexname, type, id)
					.execute().actionGet();

			System.out.println("response.getSource()：" + response.getSource());
			System.out.println("response.getId():" + response.getId());
			System.out.println("response.getSourceAsString():"
					+ response.getSourceAsString());
			return response.getSourceAsMap();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Title: getHighlightFields
	 * @Description: 获取带有关键字高亮的内容信息
	 * @param hit
	 *            :记录行
	 * @param field
	 *            :字段
	 * @return String
	 */
	public static String getHighlightFields(SearchHit hit, String field) {
		String content = "";
		if (hit != null) {
			Map<String, HighlightField> result = hit.highlightFields();
			HighlightField contentField = result.get(field);
			if (contentField != null) {
				Text[] contentTexts = contentField.fragments();
				for (Text text : contentTexts) {
					content = text.toString();
				}
			} else {
				content = (String) hit.getSource().get(field);
			}
		}
		return content;
	}

}
