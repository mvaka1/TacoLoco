package com.tacoloco.process.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tacoloco.process.model.OrderWrapper;
import com.tacoloco.process.model.TacoOrderResponse;
import com.tacoloco.process.service.TacoLocoService;

@EnableWebMvc
@RestController("tacoloco")
public class TacoController {
	
	@Autowired
	public TacoLocoService tacoService;
	
	 @RequestMapping(name = "/ ", method = RequestMethod.POST)
	 @ResponseBody
	public TacoOrderResponse giveTotal(@RequestBody OrderWrapper tacoorders){
		TacoOrderResponse response = new TacoOrderResponse();
		response.setOrderTotalCost(tacoService.calculateTotal(tacoorders.getOrders()));
		return response;
	}

	

}
