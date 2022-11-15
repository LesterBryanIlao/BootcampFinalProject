package com.bootcamp.webapp.it.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bootcamp.webapp.it.util.OSUtil;
import com.bootcamp.webapp.it.util.OSUtil.OS;

public abstract class AbstractSeleniumTest {
    protected WebDriver webDriver;

    public AbstractSeleniumTest() {
        final OS os = OSUtil.getOS();
        System.setProperty("webdriver.chrome.driver", os.getPath());
        webDriver = new ChromeDriver();
    }
}
