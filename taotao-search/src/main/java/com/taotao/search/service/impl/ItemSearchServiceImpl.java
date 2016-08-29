package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemSearchCustomMapper;
import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.pojo.custom.ItemSearchResult;
import com.taotao.search.dao.ItemSearchDao;
import com.taotao.search.service.ItemSearchService;
import com.taotao.utils.ExceptionUtil;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {
	@Autowired
	private ItemSearchCustomMapper itemSearchCustomMapper;

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private ItemSearchDao itemSearchDao;

	/**
	 * 将数据库中的数据导入到索引库中
	 */
	@Override
	public TaotaoResult importItemToIndex() throws Exception{
		List<ItemSearchCustom> list = itemSearchCustomMapper.getSearchIndexFromDB();
		if (list != null && list.size() > 0) {
				for (ItemSearchCustom itemSearchCustom : list) {
					SolrInputDocument doc = new SolrInputDocument();
					doc.addField("id", itemSearchCustom.getId());
					doc.addField("item_sell_point", itemSearchCustom.getSellPoint());
					doc.addField("item_price", itemSearchCustom.getPrice());
					doc.addField("item_title", itemSearchCustom.getTitle());
					doc.addField("item_image", itemSearchCustom.getImage());
					doc.addField("item_category_name", itemSearchCustom.getName());
					solrServer.add(doc);
				}
				solrServer.commit();
		}
		return TaotaoResult.ok();
	}

	/***
	 * 通过条件查询索引库商品信息
	 */
	@Override
	public ItemSearchResult queryItemByQuery(String keywords, Integer page, Integer rows) throws Exception{
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询条件
		solrQuery.setQuery(keywords);
		// 设置默认查询字段
		solrQuery.setParam("df", "item_keywords");
		// 设置分页信息
		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);
		// 设置高亮
		solrQuery.setHighlight(true);
		solrQuery.setParam("hl.fl", "item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");

		ItemSearchResult searchResult = null;
		searchResult = itemSearchDao.queryItemByQuery(solrQuery);
		long totalRecord = searchResult.getTotalRecord();
		long totalPage = (totalRecord % rows == 0) ? (totalRecord / rows) : (totalRecord / rows + 1);
		searchResult.setPageNow(page);
		searchResult.setTotalPage(totalPage);
		return searchResult;
	}

}
