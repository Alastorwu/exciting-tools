package com.exciting;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CodeCompileTest {

    @Test
    public void groovy() throws  NoSuchMethodException, ScriptException {


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


    @Test
    public void java() throws  NoSuchMethodException, ScriptException {


        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("java");
        String HelloLanguage = "public String hello(String language) {return \"Hello $language\";}";
        engine.eval(HelloLanguage);
        Invocable inv = (Invocable) engine;
        Object[] params = {new String("java")};
        Object result = inv.invokeFunction("hello", params);
        //assert result.equals("Hello Groovy");
        System.out.println(result);
    }

    public static void main(String[] args) {
        String code = "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello World!\");\n" +
                "    }\n" +
                "}";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("java");
        try {
            engine.eval(code);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
