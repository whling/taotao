package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.custom.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response,Model model) {
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		CartItem cartItem =(CartItem) result.getData();
		model.addAttribute("cartItem", cartItem);
		return "cartSuccess";
	}
	
	@RequestMapping("/update/num/{itemId}/{num}")
	public String updateCartItem(@PathVariable Long itemId,@PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response){
		cartService.updateCartItemList(itemId, num, request, response);
		return null;
	}

	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		model.addAttribute("count",list.size());
		return "cart";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request, HttpServletResponse response){
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}