package com.tacoloco.process.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tacoloco.process.model.OrderWrapper;
import com.tacoloco.process.model.TacoOrderResponse;
import com.tacoloco.process.model.TacoOrders;
import com.tacoloco.process.service.TacoLocoService;

@RunWith(SpringRunner.class)
@WebMvcTest(TacoController.class)
public class TacoLocoControllerTest{
	@Autowired
	public MockMvc mockmvc;
	
	ObjectMapper jsonmapper = new ObjectMapper();
	
	@MockBean
	TacoLocoService tacoService;
	/**
	 * this test to check if the post resource is established 
	 * @throws Exception
	 */
	@Test
	public void getPostTest() throws Exception{
		TacoOrders to1 = new TacoOrders();
		to1.setOrderName("Veggie Taco");
		to1.setQuantity(1);
		TacoOrders to2 = new TacoOrders();
		to2.setOrderName("Chorizo Taco");
		to2.setQuantity(2);
		List<TacoOrders> tacoOrders = new ArrayList<>();
		tacoOrders.add(to1);
		tacoOrders.add(to2);
		OrderWrapper orderwrap = new OrderWrapper();
		orderwrap.setOrders(tacoOrders);
		TacoOrderResponse orderresponse = new TacoOrderResponse();
		orderresponse.setOrderTotalCost(9.50);
		Mockito.when(
				tacoService.calculateTotal(tacoOrders)).thenReturn(orderresponse.getOrderTotalCost());
		String jsonContent = "{" + 
				"    \"orders\": [" + 
				"        {" + 
				"            \"orderName\": \"Veggie Taco\"," + 
				"            \"quantity\": 2" + 
				"        }," + 
				"        {" + 
				"            \"orderName\": \"Chicken Taco\"," + 
				"            \"quantity\": 4" + 
				"        }" + 
				"    ]" + 
				"}";
		MvcResult result = mockmvc.perform(post("/tacoloco").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse resultContent = result.getResponse();
		assertEquals(200, resultContent.getStatus());

	
	}
	/**
	 * This test to check the business logic of calculating roll ups
	 * @throws Exception
	 */
	@Test
	public void getTotalTest() throws Exception{
		TacoLocoService tacosrvc = new TacoLocoService();
		TacoOrders to1 = new TacoOrders();
		to1.setOrderName("Veggie Taco");
		to1.setQuantity(1);
		TacoOrders to2 = new TacoOrders();
		to2.setOrderName("Chorizo Taco");
		to2.setQuantity(2);
		List<TacoOrders> tacoOrders = new ArrayList<>();
		tacoOrders.add(to1);
		tacoOrders.add(to2);
		OrderWrapper orderwrap = new OrderWrapper();
		orderwrap.setOrders(tacoOrders);
		TacoOrderResponse orderresponse = new TacoOrderResponse();
		orderresponse.setOrderTotalCost(9.50);
		Assert.assertEquals(orderresponse.getOrderTotalCost(), tacosrvc.calculateTotal(tacoOrders), 0.001);

	
	}
}