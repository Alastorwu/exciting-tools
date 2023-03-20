package com.exciting.webapp.component;

import com.exciting.common.util.DateUtils;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        String expression = "sysdate()";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
//        env.put("a", "abcde");
//        env.put("b", "bc");
//        env.put("c", "cb");
//        env.put("d", "123");
        //Boolean result = (Boolean) compiledExp.execute(env);
        Date execute = (Date) compiledExp.execute(env);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(execute);
        System.out.println(format);
//        String contentInfo = getContentInfo("MIN{aaa}+min{bbb}dfgfd{jsdfhdjhd}");
        //System.out.println(contentInfo);
    }

    public static String getContentInfo(String content) {
        Pattern regex = Pattern.compile("min\\{([^}]*)\\}",Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(content);
        while (matcher.find()) {
            String group = matcher.group(1);
            String findString = "min{" + group + "}";
            content = content.replace(findString,group);
            System.out.println(group);
        }
        return content;
    }
}