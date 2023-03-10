package com.exciting.webapp.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FakeSessionComponentTest {

    @Resource
    private FakeSessionComponent fakeSessionComponent;

    @Before
    public void setUp() throws Exception {
        String key = "testSession";
        fakeSessionComponent.setAttribute(key,"test",30L);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAttribute() {
        String key = "testSession";
        fakeSessionComponent.setAttribute(key,"test",30L);
    }

    @Test
    public void getAttribute() {
        String testSession = fakeSessionComponent.getAttribute("testSession");
        System.out.println(testSession);
    }

    @Test
    public void clean() {
    }


    public static void main(String[] args) throws ScriptException, NoSuchMethodException {


        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
        engine.eval(HelloLanguage);
        Invocable inv = (Invocable) engine;
        Object[] params = {new String("Groovy")};
        Object result = inv.invokeFunction("hello", params);
        //assert result.equals("Hello Groovy");
        System.out.println(result);
    }
}