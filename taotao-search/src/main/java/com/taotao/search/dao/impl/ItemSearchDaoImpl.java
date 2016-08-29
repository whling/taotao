package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.pojo.custom.ItemSearchResult;
import com.taotao.search.dao.ItemSearchDao;

@Repository
public class ItemSearchDaoImpl implements ItemSearchDao {
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public ItemSearchResult queryItemByQuery(SolrQuery solrQuery) throws Exception {
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		ItemSearchResult itemSearchResult=new ItemSearchResult();
		List<ItemSearchCustom> itemSearchCustoms = new ArrayList<ItemSearchCustom>();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			ItemSearchCustom itemSearchCustom=new ItemSearchCustom();
			itemSearchCustom.setId((String) solrDocument.get("id"));
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title="";
			if(list!=null && list.size()>0){
				title = list.get(0);
			}else{
				title=(String) solrDocument.get("item_title");
			}
			itemSearchCustom.setTitle(title);
			itemSearchCustom.setPrice((Long) solrDocument.get("item_price"));
			itemSearchCustom.setImage((String) solrDocument.get("item_image"));
			itemSearchCustom.setName((String) solrDocument.get("item_category_name"));
			itemSearchCustom.setSellPoint((String) solrDocument.get("item_sell_point"));
			itemSearchCustoms.add(itemSearchCustom);
		}
		
		itemSearchResult.setTotalRecord(solrDocumentList.getNumFound());
		itemSearchResult.setItemSearchCustoms(itemSearchCustoms);
		return itemSearchResult;
	}

}
