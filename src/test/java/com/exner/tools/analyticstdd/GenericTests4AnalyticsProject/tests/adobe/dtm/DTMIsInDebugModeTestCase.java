package com.exner.tools.analyticstdd.GenericTests4AnalyticsProject.tests.adobe.dtm;

import com.exner.tools.analyticstdd.GenericTests4AnalyticsProject.tests.WebDriverBasedTestCase;

public class DTMIsInDebugModeTestCase extends WebDriverBasedTestCase {
	public DTMIsInDebugModeTestCase(String pageURL) {
		super(pageURL);
		setName("DTM in debug mode - " + pageURL);
	}

	@Override
	protected void runTest() throws Throwable {
		Object response = _jsExecutor.executeScript("return localStorage.getItem('sdsat_debug');");

		// make sure the element exists
		if (null == response) {
			fail("DTM is not in debug mode");
		}
		if (Boolean.class.isAssignableFrom(response.getClass())) {
			assertTrue("DTM in debug mode ", (Boolean) response);
		} else if (String.class.isAssignableFrom(response.getClass())) {
			assertEquals("DTM in debug mode", "true", (String) response);
		} else {
			fail("DTM not in debug mode");
		}

	}
}