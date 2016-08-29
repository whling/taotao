package com.taotao.search.solrcloud;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {
	@Test
	public void testAddDocument() throws Exception{
		//
		String zkHost="192.168.1.151:2181,192.168.1.151:2182,192.168.1.151:2183";
		CloudSolrServer cloudSolrServer=new CloudSolrServer(zkHost);
		cloudSolrServer.setDefaultCollection("collection2");
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "小米手机");
		
		cloudSolrServer.add(document);
		cloudSolrServer.commit();
	}
	
	@Test
	public void testDelDocument() throws Exception{
		//
		String zkHost="192.168.1.151:2181,192.168.1.151:2182,192.168.1.151:2183";
		CloudSolrServer cloudSolrServer=new CloudSolrServer(zkHost);
		cloudSolrServer.setDefaultCollection("collection2");
		cloudSolrServer.deleteById("test001");
		cloudSolrServer.commit();
	}
}
