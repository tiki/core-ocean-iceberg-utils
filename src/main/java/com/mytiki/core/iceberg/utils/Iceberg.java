/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

package com.mytiki.core.iceberg.utils;

import org.apache.iceberg.aws.glue.GlueCatalog;
import org.apache.iceberg.catalog.Namespace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Iceberg extends GlueCatalog {
    public static final String NAME = "glue_catalog";
    public static final String CATALOG_NAME = "catalog-name";
    public static final String CATALOG_IMPL = "catalog-impl";
    public static final String WAREHOUSE = "warehouse";
    public static final String IO_IMPL = "io-impl";
    public static final String GLUE_SKIP_ARCHIVE = "glue.skip-archive";
    public static final String PROPERTIES = "iceberg.properties";
    protected static final Logger logger = LogManager.getLogger(Iceberg.class);

    private final Map<String, String> properties;
    private final Namespace database;

    public Iceberg(Properties properties) {
        super();
        this.properties = new HashMap<>() {{
            put("catalog-name", properties.getProperty("catalog-name"));
            put("catalog-impl", properties.getProperty("catalog-impl"));
            put("warehouse", properties.getProperty("warehouse"));
            put("io-impl", properties.getProperty("io-impl"));
            put("glue.skip-archive", properties.getProperty("glue.skip-archive"));
        }};
        database = Namespace.of(properties.getProperty("database-name"));
    }

    public Iceberg initialize() {
        super.initialize(NAME, properties);
        return this;
    }

    public static Iceberg load() {
        return Iceberg.load(PROPERTIES);
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

    public Map<String, String> getProperties() {
        return properties;
    }

    public Namespace getDatabase() {
        return database;
    }

    public String getCatalogName() {
        return properties.get(CATALOG_NAME);
    }

    public String getCatalogImpl() {
        return properties.get(CATALOG_IMPL);
    }

    public String getWarehouse() {
        return properties.get(WAREHOUSE);
    }

    public String getIoImpl() {
        return properties.get(IO_IMPL);
    }

    public String getGlueSkipArchive() {
        return properties.get(GLUE_SKIP_ARCHIVE);
    }
}
