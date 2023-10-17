/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

import com.mytiki.core.iceberg.utils.Iceberg;
import com.mytiki.core.iceberg.utils.Initialize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.Properties;

public class IcebergTest {

    @Test
    public void Test_Initialize_Success() {
        Properties properties = Initialize.properties("iceberg.properties");
        Iceberg iceberg = Mockito.spy(new Iceberg(properties));
        initialize(iceberg);
    }

    @Test
    public void Test_Load_Success() {
        Iceberg iceberg = Mockito.spy(Iceberg.load());
        initialize(iceberg);
    }

    @Test
    public void Test_Load_Filename_Success() {
        Iceberg iceberg = Mockito.spy(Iceberg.load("iceberg.properties"));
        initialize(iceberg);
    }

    private void initialize(Iceberg iceberg) {
        Mockito.doNothing().when(iceberg).initialize(Mockito.any(), Mockito.any());

        iceberg = iceberg.initialize();
        Assertions.assertEquals("dummy-warehouse", iceberg.getWarehouse());
        Assertions.assertEquals("org.apache.iceberg.aws.glue.GlueCatalog", iceberg.getCatalogImpl());
        Assertions.assertEquals("org.apache.iceberg.aws.s3.S3FileIO", iceberg.getIoImpl());
        Assertions.assertEquals("iceberg", iceberg.getCatalogName());
        Assertions.assertEquals("true", iceberg.getGlueSkipArchive());
        Assertions.assertEquals("dummy-database", iceberg.getDatabase().toString());
    }
}
