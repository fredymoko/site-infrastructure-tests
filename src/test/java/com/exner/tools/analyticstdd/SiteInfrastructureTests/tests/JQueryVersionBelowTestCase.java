package com.exner.tools.analyticstdd.SiteInfrastructureTests.tests;

import java.util.List;

import com.exner.tools.analyticstdd.SiteInfrastructureTests.Tools;
import com.fasterxml.jackson.databind.node.TextNode;

public class JQueryVersionBelowTestCase extends WebDriverBasedTestCase {
	private final String _maxVersion;

	public JQueryVersionBelowTestCase(String pageURL, List<String> blockPatterns, Object params) {
		super(pageURL, blockPatterns);

		if (TextNode.class.isAssignableFrom(params.getClass())) {
			_maxVersion = ((TextNode) params).asText();
		} else {
			_maxVersion = "0";
			throw new IllegalArgumentException("Must define a max version!");
		}

		setName("jQuery version before " + _maxVersion + " - " + pageURL);
	}

	@Override
	protected void runTest() throws Throwable {
		// check whether DTM has been loaded on the page
		Object response = _page.executeJavaScript("(typeof jQuery !== 'undefined') ? jQuery.fn.jquery : 'unavailable'")
				.getJavaScriptResult();

		// make sure the element exists
		if (String.class.isAssignableFrom(response.getClass())) {
			boolean result = Tools.testVersionIsOlderThanBaseVersion((String) response, _maxVersion);
			assertTrue("jQuery version should be before " + _maxVersion, result);
		} else {
			fail("jQuery version not available");
		}

	}

}
