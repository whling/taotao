package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.pojo.custom.ItemSearchResult;

public interface ItemSearchDao {
	/**
	 * 通过条件查询索引库
	 * @param solrQuery
	 * @return
	 */
	ItemSearchResult queryItemByQuery(SolrQuery solrQuery)throws Exception;
}
