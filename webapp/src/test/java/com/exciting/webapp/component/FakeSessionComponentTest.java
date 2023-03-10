package com.exciting.webapp.component;

import com.alibaba.fastjson.JSON;
import com.exciting.common.entity.vo.ExcitingVo;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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


    public static void main(String[] args) {
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ");

        System.err.println(value +", " + value.equals(10));
        System.err.println(binding.getVariable("y") +", " + binding.getVariable("y").equals(20));
        System.err.println(binding.getVariable("z") +", " + binding.getVariable("z").equals(30));
    }
}