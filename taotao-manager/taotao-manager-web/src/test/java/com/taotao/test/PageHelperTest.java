package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
/**
 * 测试mybatis的分页插件
 * @author Administrator
 *
 */
public class PageHelperTest {
	
	@Test
	public void testPageHelper(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//不添加任何条件，默认查询所有
		TbItemExample example=new TbItemExample();
		PageHelper pageHelper=new PageHelper();
		pageHelper.startPage(1, 10);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			for (TbItem tbItem : list) {
				System.out.println(tbItem.getTitle());
			}
		}
		PageInfo pageInfo=new PageInfo(list);
		System.out.println(pageInfo.getTotal());
	}
}
