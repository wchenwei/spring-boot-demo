package com.finance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PropertiesUtil extends PropertyPlaceholderConfigurer {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Map<String,String> propertyMap = new HashMap<>();
    private static Properties properties = null;
    private static String placeholderSuffix = ".properties";


    private EncodedResource[] resources = null;

    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {
        placeholderSuffix = PropertiesUtil.placeholderSuffix;
        super.setPlaceholderSuffix(placeholderSuffix);
    }

    public static String getProperties(String key){
        if(properties == null){
            load();
            for (Object pro : properties.keySet()){
                String key1 = pro.toString();
                String value = properties.getProperty(key1);
                propertyMap.put(key,value);
            }
        }
        return propertyMap.get(key);
    }


    public static void load(){

        properties = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getPro(String key) {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }
}
