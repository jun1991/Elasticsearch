package com.es.config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings({ "resource", "unchecked" })
public class EsClient {
	private static TransportClient client = null;
	private static Settings settings = null;
	
	static{
		// 配置信息
		settings = Settings.builder()
		        .put("cluster.name", "elasticsearch").build();

	    // 添加连接地址
		try {
			client = new PreBuiltTransportClient(settings)
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	    
	}
	
	/**
	 * 创建一个索引
	 * @param indexName 索引名
	 * @throws UnknownHostException 
	 */
	public static void createIndex(String indexName) throws UnknownHostException {
	    try {
	        CreateIndexResponse indexResponse = client
	                                .admin()
	                                .indices()
	                                .prepareCreate(indexName)
	                                .get();

	        System.out.println(indexResponse.isAcknowledged()); // true表示创建成功
	    } catch (ElasticsearchException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 给索引增加mapping,使用XContentBuilder创建Mapping。
	 * @param index 索引名
	 * @param type mapping所对应的type
	 * @param builder XContentBuilder
	 */
	public static void addMapping(String index, String type,XContentBuilder builder) {
	    try {
	        System.out.println("映射对象：" + builder.string());   
	        PutMappingRequest mappingRequest = Requests.putMappingRequest(index).source(builder).type(type);
	        client.admin().indices().putMapping(mappingRequest).actionGet();
	    } catch (ElasticsearchException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
     * 创建索引
     * @param client
     * @param jsondata
     * @return
	 * @throws UnknownHostException 
     */
    public static IndexResponse createIndexResponse(String index, String type,Object obj) throws UnknownHostException{
    	String jsonStr = JSON.toJSON(obj).toString();
    	System.out.println("插入对象：" + jsonStr);
    	JSONObject jo = JSONObject.parseObject(jsonStr);
    	String id = jo.getString("id");
    	jo.remove("id");
        IndexResponse response = client.prepareIndex(index, type, id)
            .setSource(jo.toJSONString(),XContentType.JSON)
            .execute()
            .actionGet();
        return response;
    }
    
    /** 
     * 执行搜索 
     * @param indexname 索引名称 
     * @param type 索引类型 
     * @param queryBuilder 查询条件 
     * @return 
     * @throws UnknownHostException 
     */  
    public static SearchResponse searcher(String indexName, String typeName,  
            QueryBuilder queryBuilder) throws UnknownHostException {  
        SearchResponse searchResponse = client.prepareSearch(indexName)  
                .setTypes(typeName).setQuery(queryBuilder).execute()  
                .actionGet();//执行查询  
        return searchResponse;  
    }
}
