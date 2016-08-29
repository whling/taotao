package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.custom.CartItem;
import com.taotao.pojo.custom.ItemSearchCustom;
import com.taotao.portal.service.CartService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class CartServiceImpl implements CartService {

	@Value("${rest.baseUrl}")
	private String REST_BASE_URL;
	@Value("${rest.itemUrl}")
	private String ITME_INFO_URL;

	/**
	 * 添加购物车商品
	 * <p>
	 * Title: addCartItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param itemId
	 * @param num
	 * @return
	 * @see com.taotao.portal.service.CartService#addCartItem(long, int)
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// 取商品信息
		CartItem cartItem = null;
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 判断购物车商品列表中是否存在此商品
		if (itemList != null && itemList.size() > 0) {
			for (CartItem cItem : itemList) {
				// 如果存在此商品
				if (Long.parseLong(cItem.getId()) == itemId) {
					// 增加商品数量
					cItem.setNum(cItem.getNum() + num);
					cartItem = cItem;
					break;
				}
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + "/" + itemId);
			// 把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemSearchCustom.class);
			if (taotaoResult.getStatus() == 200) {
				ItemSearchCustom item = (ItemSearchCustom) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车列表写入cookie
		String cart = JsonUtils.objectToJson(itemList);
		CookieUtils.setCookie(request, response, "TT_CART", cart, true);

		return TaotaoResult.ok(cartItem);
	}

	/**
	 * 从cookie中取商品列表
	 * <p>
	 * Title: getCartItemList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/**
	 * 获取购物车列表
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}

	/**
	 * 通过购物车更新cookie中的商品个数
	 */
	@Override
	public TaotaoResult updateCartItemList(long itemId, int num, HttpServletRequest request,
			HttpServletResponse response) {
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 判断购物车商品列表中是否存在此商品
		if (itemList != null && itemList.size() > 0) {
			for (CartItem cItem : itemList) {
				// 如果存在此商品
				if (Long.parseLong(cItem.getId()) == itemId) {
					// 增加商品数量
					cItem.setNum(num);
					break;
				}
			}
		}
		String cart = JsonUtils.objectToJson(itemList);
		CookieUtils.setCookie(request, response, "TT_CART", cart, true);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 从列表中找到此商品
		for (CartItem cartItem : itemList) {
			if (Long.parseLong(cartItem.getId()) == itemId) {
				itemList.remove(cartItem);
				break;
			}

		}
		// 把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

}
