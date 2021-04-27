package com.sky.f1.batch.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;



class PropertyLoaderTest {

	@Test
	void returnValueIfKeyIsPresent() {
		assertEquals("test",PropertyLoader.getInstance().getProperty("sample"));
	}

}
