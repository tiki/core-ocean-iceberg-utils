/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

package com.mytiki.core.iceberg.utils;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Initialize {

    public static void logger(){
        PropertyConfigurator.configure(Initialize.class.getClassLoader().getResource("log4j.properties"));
    }

    public static Properties properties(String name) {
        try (InputStream input = Initialize.class
                .getClassLoader()
                .getResourceAsStream(name)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
