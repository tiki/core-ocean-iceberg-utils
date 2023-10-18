/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

package com.mytiki.core.iceberg.utils;

public class Env {
    public String get(String var) {
        return System.getenv(var);
    }

    public String name(String var) {
        return var.replace(".", "_").toUpperCase();
    }
}
