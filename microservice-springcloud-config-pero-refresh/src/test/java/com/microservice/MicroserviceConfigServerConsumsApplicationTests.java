package com.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceConfigServerConsumsApplicationTests {

	@Test
	public void contextLoads() {
		String[] str=null;
		String string = "1,2,";
		if (string != null){
            str = string.split(",");
        }else{
		    str = new String[1];
        }
        if (str.length ==1){
		    System.out.println(str.toString());
        }else{
            List<String> list = Arrays.asList(str);
            List<String> strings=    new ArrayList<>(list);
            strings.remove(0);
           Object[] objects = strings.toArray();
           String[] newStr = new String[objects.length+1];
           System.arraycopy(objects, 0, newStr, 0, objects.length);//数组copy
            newStr[newStr.length-1] = "3";
            for (int i = 0; i < newStr.length; i++) {
                System.out.println("i =="+newStr[i]);
            }
        }


	}

}
