package com.es.config;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.es.model.Book;

public class EsClientTest {

	@Test
	public void test() {
		/*try {
			EsClient.init();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void createIndex(){
		try {
			EsClient.createIndex("library");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createMapping(){
		 try {
			XContentBuilder builder = 
			            XContentFactory.jsonBuilder()
			                            .startObject()
			                                .field("properties")//
			                                    .startObject()
			                                        .field("author")//
			                                            .startObject()
			                                                .field("type", "string")
			                                            .endObject()
			                                        .field("characters")//
			                                            .startObject()
			                                                .field("type", "string")
			                                            .endObject()
			                                        .field("copies")//
			                                            .startObject()
				                                            .field("type", "long")
				                                            .field("ignore_malformed", false)
			                                            .endObject()
			                                        .field("otitle")//
			                                            .startObject()
			                                            	.field("type", "string")
			                                            .endObject()
			                                        .field("tags")//
			                                            .startObject()
			                                            	.field("type", "string")
			                                            .endObject()
			                                        .field("title")//
			                                            .startObject()
			                                            	.field("type", "string")
			                                            .endObject()
			                                        .field("year")//
			                                            .startObject()
				                                            .field("type", "long")
				                                            .field("ignore_malformed", "false")
				                                            .field("index", "analyzed")
			                                            .endObject()
			                                        .field("available")//
			                                            .startObject()
			                                            	.field("type", "boolean")
			                                            .endObject()
			                                    .endObject()
			                            .endObject();
			EsClient.addMapping("library", "book", builder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addBooks(){
		try {
			Book b = new Book();
			b.setTitle("All Quiet on the Western Front");
			b.setOtitle("Im Westen nichts Neues");
			b.setAuthor("Erich Maria Remarque");
			b.setYear(1929l);
			String[] characters = {"Paul Bäumer","Albert Kropp","Haie Westhus","Fredrich Müller",
					"Stanislaus Katczinsky","Tjaden"};
			b.setCharacters(Arrays.asList(characters));
			String[] tags = {"novel"};
			b.setTags(Arrays.asList(tags));
			b.setCopies(1l);
			b.setAvailable(true);
			b.setId("1");
			EsClient.createIndexResponse("library", "book", JSON.toJSON(b).toString());
			
			
			b = new Book();
			b.setTitle("Catch-22");
			b.setAuthor("Joseph Heller");
			b.setYear(1961l);
			String[] characters1 = {"John Yossarian","Captain Aardvark","Chaplain Tappman","Colonel Cathcart",
					"Doctor Daneeka"};
			b.setCharacters(Arrays.asList(characters1));
			String[] tags1 = {"novel"};
			b.setTags(Arrays.asList(tags1));
			b.setCopies(6l);
			b.setAvailable(false);
			b.setId("2");
			EsClient.createIndexResponse("library", "book", JSON.toJSON(b).toString());
			
			
			b = new Book();
			b.setTitle("The Complete Sherlock Holmes");
			b.setAuthor("Arthur Conan Doyle");
			b.setYear(1936l);
			String[] characters2 = {"Sherlock Holmes","Dr. Watson","G. Lestrade"};
			b.setCharacters(Arrays.asList(characters2));
			String[] tags2 = {};
			b.setTags(Arrays.asList(tags2));
			b.setCopies(0l);
			b.setAvailable(false);
			b.setId("3");
			EsClient.createIndexResponse("library", "book", JSON.toJSON(b).toString());
			
			b = new Book();
			b.setTitle("Crime and Punishment");
			b.setOtitle("Преступлéние инаказáние");
			b.setAuthor("Fyodor Dostoevsky");
			b.setYear(1886l);
			String[] characters3 = {"Raskolnikov","Sofia Semyonovna Marmeladova"};
			b.setCharacters(Arrays.asList(characters3));
			String[] tags3 = {};
			b.setTags(Arrays.asList(tags3));
			b.setCopies(0l);
			b.setAvailable(true);
			b.setId("4");
			EsClient.createIndexResponse("library", "book", JSON.toJSON(b).toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
