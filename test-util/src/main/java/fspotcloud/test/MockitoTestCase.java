package fspotcloud.test;


import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;

public class MockitoTestCase{

	@BeforeTest
	protected void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		System.out.println("InitMocks ran");
	}

}
