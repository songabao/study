package com.microservice;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceConfigServerEurekaApplicationTests {

	@Test
	public void contextLoads() {
		//data: [{
			// title: '陕西' //一级菜单
			// ,children: [{
			// title: '西安' //二级菜单
			// }]
			// }]
		JSONObject map = new JSONObject();
		JSONArray data = new JSONArray();
		//商品
		JSONObject seller = new JSONObject();
		seller.put("title", "商品");
		seller.put("id", "1");
		JSONArray sellerChildren = new JSONArray();
		//商品
		JSONObject seller2 = new JSONObject();
		sellerChildren.add(seller2);
		seller.put("children",sellerChildren);
		data.add(seller);
		map.put("data", data);
		System.out.println(map);
	}

}
