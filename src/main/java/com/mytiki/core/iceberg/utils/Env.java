/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

package com.mytiki.core.iceberg.utils;

public class Env {
    public String get(String name) {
        return System.getenv(name);
    }

    public static String var(String name) {
        return System.getenv(name);
    }
}
