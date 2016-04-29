package com.exner.tools.analyticstdd.GenericTests4AnalyticsProject.tests.adobe.at;

import com.exner.tools.analyticstdd.GenericTests4AnalyticsProject.tests.WebDriverBasedTestCase;

public class GlobalMboxExistenceTestCase extends WebDriverBasedTestCase {
	private final String _mboxName;

	public GlobalMboxExistenceTestCase(String pageURL, String mboxName) {
		super(pageURL);
		_mboxName = mboxName;
		setName("Adobe Target global mbox " + mboxName + " exists - " + pageURL);
	}

	@Override
	protected void runTest() throws Throwable {
		// get the value of the dl element from the page
		Object response = _jsExecutor.executeScript("if (typeof mboxCurrent != 'undefined' && mboxCurrent.getName() == '"
				+ _mboxName + "') { return true; } else { return false; }");

		// make sure the element exists
		if (Boolean.class.isAssignableFrom(response.getClass())) {
			assertTrue("Adobe Target global mbox " + _mboxName + " must exist", (Boolean) response);
		} else {
			fail("Adobe Target global mbox " + _mboxName + " does not exist");
		}
	}

}