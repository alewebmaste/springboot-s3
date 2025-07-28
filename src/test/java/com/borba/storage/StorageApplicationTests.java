package com.borba.storage;

import com.borba.storage.sns.SnsPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class StorageApplicationTests {

	@MockitoBean
	private SnsPublisher snsPublisher;

	@Test
	void contextLoads() {
	}

}
