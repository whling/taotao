package com.taotao.rest.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.service.ItemCatService;

public class ItemCatServiceImplTest {
	private ItemCatService catService;
	private ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	@Test
	public void testGetItemCat() {
		catService=applicationContext.getBean(ItemCatService.class);
		catService.getItemCat();
	}

}
