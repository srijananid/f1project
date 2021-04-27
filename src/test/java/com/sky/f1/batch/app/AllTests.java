package com.sky.f1.batch.app;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@SelectPackages({"com.sky.f1.batch.config,com.sky.f1.batch.process"}) 
public class AllTests {

}
	