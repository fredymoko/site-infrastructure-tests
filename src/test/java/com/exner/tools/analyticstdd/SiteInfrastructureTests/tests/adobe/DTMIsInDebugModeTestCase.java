package com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.adobe;

import java.util.List;

import com.exner.tools.analyticstdd.SiteInfrastructureTests.Tools;
import com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.WebDriverBasedTestCase;
import com.fasterxml.jackson.databind.node.BooleanNode;

public class DTMIsInDebugModeTestCase extends WebDriverBasedTestCase {

	private final boolean _test;

	public DTMIsInDebugModeTestCase(String pageURL, List<String> blockPatterns, Object params) {
		super(pageURL, blockPatterns);

		String note = "";
		if (BooleanNode.class.isAssignableFrom(params.getClass())) {
			_test = ((BooleanNode) params).asBoolean();
			if (!_test) {
				note = " (inactive)";
			}
		} else {
			_test = true;
		}

		setName(Tools.DTM + " in debug mode " + note + " - " + pageURL);
	}

	@Override
	protected void runTest() throws Throwable {
		if (_test) {
			Object response = _page.executeJavaScript("localStorage.getItem('sdsat_debug');").getJavaScriptResult();

			// make sure the element exists
			if (null == response) {
				fail(Tools.DTM + " is not in debug mode");
			}
			if (Boolean.class.isAssignableFrom(response.getClass())) {
				assertTrue(Tools.DTM + " in debug mode ", (Boolean) response);
			} else if (String.class.isAssignableFrom(response.getClass())) {
				assertEquals(Tools.DTM + " in debug mode", "true", (String) response);
			} else {
				fail(Tools.DTM + " not in debug mode");
			}
		}
	}
}
