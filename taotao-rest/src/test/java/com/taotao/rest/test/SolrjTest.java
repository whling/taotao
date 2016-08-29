package com.taotao.rest.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {
	@Test
	public void createIndex() throws Exception{
		//创立链接
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.151/solr");
		//创建文档
		SolrInputDocument document=new SolrInputDocument();
		//创建索引,必须要有id属性
		document.addField("id", "test007");
		document.addField("item_title", "遇见");
		document.addField("item_sell_point", "听见，冬天的离开，我在某年某月醒过来");
		document.addField("item_price", 999);
		
		//提交
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void deleteIndex() throws SolrServerException, IOException{
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.151/solr");
		//通过id删除索引
//		solrServer.deleteById("test007");
		//通过条件删除
		solrServer.deleteByQuery("item_sell_point:离开");
		solrServer.commit();
	}
	
	@Test
	public void queryIndex() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.1.151/solr");
		SolrQuery query=new SolrQuery();
		query.setQuery("item_sell_point:离开");
		
		query.setHighlight(true);
		query.setParam("hl.fl", "item_sell_point");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		
//		query.setStart(start);
//		query.setRows(rows);
		QueryResponse queryResponse = solrServer.query(query);
		//获取所有符合记录的数（不包含高亮）
		SolrDocumentList results = queryResponse.getResults();
		//获取高亮数据
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		System.out.println("所有数据："+results.size()+"   高亮数据："+highlighting.size());
		for (SolrDocument solrDocument : results) {
			String item_sell_point = (String) solrDocument.get("item_sell_point");
			System.out.println(item_sell_point);
			Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
			List<String> list = map.get("item_sell_point");
			for (String string : list) {
				System.out.println(string);
			}
		}
	}
}
