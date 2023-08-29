/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

package com.mytiki.ocean.common;

import org.apache.iceberg.aws.glue.GlueCatalog;
import org.apache.iceberg.catalog.Namespace;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Iceberg extends GlueCatalog {
    protected static final Logger logger = Logger.getLogger(Iceberg.class);

    private final String warehouse;
    private final Namespace database;

    public Iceberg(Properties properties){
        super();
        Map<String, String> catalogProperties = new HashMap<>() {{
            put("catalog-name", properties.getProperty("catalog-name"));
            put("catalog-impl", properties.getProperty("catalog-impl"));
            put("warehouse", properties.getProperty("warehouse"));
            put("io-impl", properties.getProperty("io-impl"));
            put("glue.skip-archive", properties.getProperty("glue.skip-archive"));
        }};
        warehouse = properties.getProperty("warehouse");
        database = Namespace.of(properties.getProperty("database-name"));
        super.initialize("glue_catalog", catalogProperties);
    }

    public static Iceberg load() {
        return Iceberg.load("iceberg.properties");
    }

    public static Iceberg load(String filename) {
        Properties properties = Initialize.properties(filename);
        return new Iceberg(properties);
    }

    @Override
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public String getWarehouse() {
        return warehouse;
    }

    public Namespace getDatabase() {
        return database;
    }
}
