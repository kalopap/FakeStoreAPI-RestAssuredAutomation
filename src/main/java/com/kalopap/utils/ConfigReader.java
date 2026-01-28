package com.kalopap.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();
    private static String activeEnv;

    static{
        try {

            InputStream is = new FileInputStream("src/test/java/resources/config.properties");
            props.load(is);
        }catch(FileNotFoundException fe){
            throw new RuntimeException("Cannot find config.properties",fe);
        }catch(IOException ioe){
            throw new RuntimeException("Exception reading the config file : ",ioe);
        }
    }

    //setting env value From Base-Test class
    public static void setEnv(String env){
        activeEnv = env;
    }

    //Get the environment : JVM(-Denv) from Jenkins > testng.xml(BaseTest) > config.properties(local)
    public static String getEnv(){
        if(System.getProperty("env") != null){
            return System.getProperty("env");
        }
        if(activeEnv != null){
            return activeEnv;
        }
        return props.getProperty("env");
    }

    public static String getBaseUrl(){
        String env1 = getEnv();
        String key = env1 + ".base.url";
        return props.getProperty(key);
    }

}
