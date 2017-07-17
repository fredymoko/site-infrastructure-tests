package com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.adobe;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exner.tools.analyticstdd.SiteInfrastructureTests.Tools;
import com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.WebDriverBasedTestCase;
import com.fasterxml.jackson.databind.node.TextNode;

public class DTMEventBasedRuleExistsTestCase extends WebDriverBasedTestCase {
	private String _ruleName;

	public DTMEventBasedRuleExistsTestCase(String pageURL, List<String> blockPatterns, Object params) {
		super(pageURL, blockPatterns);

		if (!TextNode.class.isAssignableFrom(params.getClass())) {
			throw new IllegalArgumentException("Must specify an element");
		}
		_ruleName = ((TextNode) params).asText();

		setName(Tools.DTM + " EBR " + _ruleName + " exists - " + pageURL);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void runTest() throws Throwable {
		// get the list of EBRs from the page
		List<Object> response = (List<Object>) _page.executeJavaScript("_satellite.rules").getJavaScriptResult();

		// loop through the list until we find the rule we're looking for
		for (Iterator<Object> iterator = response.iterator(); iterator.hasNext();) {
			Object ebr = iterator.next();
			Map<String, Object> map = (Map<String, Object>) ebr;
			String ruleName = (String) map.get("name");
			if (ruleName.equals(_ruleName)) {
				// good, we found the rule! It's a pass!
				return;
			}
		}

		// didn't find the rule? Well...
		fail(Tools.DTM + " EBR " + _ruleName + " doesn't exist");
	}

}
