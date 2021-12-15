package com.spring.test.junit;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class E2ETests {

	final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
			"          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";

	Map<String, String> cookies = new HashMap<>();

	Document getLoginPage() throws IOException {
		var loginFormUrl = "https://github.com/login";
		var loginForm = Jsoup.connect(loginFormUrl)
				.method(Connection.Method.GET)
				.userAgent(USER_AGENT).execute();
		cookies = loginForm.cookies();
		return loginForm.parse();
	}

	@Test
	@DisplayName("Test Login Failed")
	void testLoginFailed() throws IOException {
		var loginDoc = getLoginPage();
		var authToken = loginDoc.select(".auth-form-body > form > input[type=\"hidden\"]:nth-child(1)")
				.first()
				.attr("value");

		//Perform Login Action
		var formData = new HashMap<String, String>();
		formData.put("commit", "Sign in");
		formData.put("utf8", "e2 9c 93");
		formData.put("login", "username");
		formData.put("password", "password");
		formData.put("authenticity_token", authToken);

		var homePage = Jsoup.connect("https://github.com/session")
				.cookies(cookies)
				.data(formData)
				.method(Connection.Method.POST)
				.userAgent(USER_AGENT)
				.execute();

		//Confirm Result
		var loginPage = homePage.parse();
		var divMsg = loginPage.getElementsByClass("container-lg px-2").get(0);

		assertTrue(divMsg != null);
		assertTrue(divMsg.html().contains("Incorrect username or password."));
	}
}
