/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

import com.mytiki.core.iceberg.utils.Initialize;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Properties;

public class InitializeTest {

    @Test
    public void Initialize_Logger_Success() {
        Initialize.logger();
        Logger logger = Logger.getLogger(InitializeTest.class);
        Assertions.assertNotNull(logger);
        logger.debug("test log");
    }

    @Test
    public void Initialize_Properties_Success() {
        Properties properties = Initialize.properties("iceberg.properties");
        Assertions.assertEquals(properties.getProperty("catalog-name"), "iceberg");
    }

    @Test
    public void Initialize_Properties_Failure() {
        Assertions.assertThrows(RuntimeException.class, () -> Initialize.properties("DUMMY"));
    }
}
