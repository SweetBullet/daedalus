package com.bullet.sweet.daedalus.base.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * Created by zhanlan on 16/11/20.
 */
public interface Serialization {

    byte[] serialize(Object obj);

    default void serialize(Object obj, OutputStream outputStream) {
        throw new UnsupportedOperationException();
    }

    <T> T deserialize(byte[] in, Class<T> type);

    @SuppressWarnings("unchecked")
    default <T> T deserialize(byte[] in, Type type) {
        if (type instanceof Class) {
            return this.deserialize(in, (Class<T>) type);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    default <T> T deserialize(InputStream input, Type type) {
        throw new UnsupportedOperationException();
    }

    default <T> T deserialize(InputStream input, Class<T> type) {
        throw new UnsupportedOperationException();
    }
}