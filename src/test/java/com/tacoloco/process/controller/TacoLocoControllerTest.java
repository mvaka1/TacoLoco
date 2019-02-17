package com.tacoloco.process.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
	
	TacoOrderResponse orderresponse = new TacoOrderResponse();
	
	List<TacoOrders> tacoOrders = new ArrayList<>();
	
	@MockBean
	TacoLocoService tacoService;
	
	@Before
	public void init() {
		TacoOrders to1 = new TacoOrders();
		to1.setOrderName("Veggie Taco");
		to1.setQuantity(1);
		TacoOrders to2 = new TacoOrders();
		to2.setOrderName("Chorizo Taco");
		to2.setQuantity(2);
		tacoOrders.add(to1);
		tacoOrders.add(to2);
		OrderWrapper orderwrap = new OrderWrapper();
		orderwrap.setOrders(tacoOrders);
		orderresponse.setOrderTotalCost(9.50);
	}
	/**
	 * this test to check if the post resource is established 
	 * @throws Exception
	 */
	@Test
	public void getPostTest() throws Exception{
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
	 * This test to check the business logic of calculating roll ups, 
	 * here the quantity of total order is less than 4, so no discount
	 * @throws Exception
	 */
	@Test
	public void getTotalTestWithNoDiscount() throws Exception{
		TacoLocoService tacosrvc = new TacoLocoService();
		Assert.assertEquals(orderresponse.getOrderTotalCost(), tacosrvc.calculateTotal(tacoOrders), 0.001);
	}
	
	/**
	 * This test to check the business logic of calculating roll ups, 
	 * here the quantity of total order is 5 (which is greater or equal to 4),
	 * 20% discount to total order is applied
	 * @throws Exception
	 */
	@Test
	public void getTotalTestWith20PercentDiscount() throws Exception{
			TacoLocoService tacosrvc = new TacoLocoService();
			TacoOrders to3 = new TacoOrders();
			to3.setOrderName("Chicken Taco");
			to3.setQuantity(2);
			tacoOrders.add(to3);
			Assert.assertEquals(12.4, tacosrvc.calculateTotal(tacoOrders), 0.001);
	}
}