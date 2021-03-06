package com.nettm.packer.packer;

import com.nettm.packer.exceptions.APIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackerApplicationTests {

	@Autowired
	public Packer packer;


	@Test
	public void contextLoads() {
	}

	@Test
	public void readFileTest(){
		try {
			System.out.println(packer.pack("data.txt"));
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

}
