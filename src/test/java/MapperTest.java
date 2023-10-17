/*
 * Copyright (c) TIKI Inc.
 * MIT license. See LICENSE file in root directory.
 */

import com.mytiki.core.iceberg.utils.ApiError;
import com.mytiki.core.iceberg.utils.ApiException;
import com.mytiki.core.iceberg.utils.Mapper;
import org.junit.Test;
import software.amazon.awssdk.http.HttpStatusCode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MapperTest {

    private final Mapper mapper = new Mapper();

    @Test
    public void Mapper_readValue_success() {
        String json = "{ \"id\":\"dummy\" }";
        ApiError deserialized = mapper.readValue(json, ApiError.class);
        assertEquals(deserialized.getId(), "dummy");
    }

    @Test
    public void Mapper_readValue_fail() {
        String json = "{ \"invalid\" }";
        ApiException ex = assertThrows(ApiException.class, () -> mapper.readValue(json, ApiError.class));
        assertEquals(HttpStatusCode.BAD_REQUEST, ex.getStatus());
    }

    @Test
    public void Mapper_writeValueAsString_success() {
        ApiError error = new ApiError();
        error.setId("dummy");
        String serialized = mapper.writeValueAsString(error);
        ApiError deserialized = mapper.readValue(serialized, ApiError.class);
        assertEquals(deserialized.getId(), "dummy");
    }
}

