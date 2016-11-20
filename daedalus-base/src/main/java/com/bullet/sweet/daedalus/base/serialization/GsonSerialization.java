package com.bullet.sweet.daedalus.base.serialization;

import com.bullet.sweet.daedalus.base.Constants;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import lombok.val;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;

/**
 * Created by leduo on 16/11/7.
 */
public class GsonSerialization implements Serialization {

    private final Gson gson;

    public GsonSerialization() {
        this(FieldNamingPolicy.IDENTITY);
    }

    public GsonSerialization(FieldNamingPolicy fieldNamingPolicy) {
        val builder = new GsonBuilder().setFieldNamingPolicy(fieldNamingPolicy).registerTypeAdapter(Instant.class,
                                                                                                    InstantCodec
                                                                                                        .instance());
        gson = builder.create();
    }

    @Override
    public byte[] serialize(Object obj) {
        return gson.toJson(obj).getBytes(Constants.DEFAULT_CHARSET);
    }

    @Override
    public void serialize(Object obj, OutputStream outputStream) {
        val writer = new JsonWriter(new OutputStreamWriter(outputStream, Constants.DEFAULT_CHARSET));
        gson.toJson(obj, obj.getClass(), writer);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] in, Class<T> type) {
        return gson.fromJson(new String(in, Constants.DEFAULT_CHARSET), type);
    }

    @Override
    public <T> T deserialize(byte[] in, Type type) {
        return gson.fromJson(new String(in, Constants.DEFAULT_CHARSET), type);
    }

    @Override
    public <T> T deserialize(InputStream input, Type type) {
        return gson.fromJson(new InputStreamReader(input, Constants.DEFAULT_CHARSET), type);
    }

    @Override
    public <T> T deserialize(InputStream input, Class<T> type) {
        return gson.fromJson(new InputStreamReader(input, Constants.DEFAULT_CHARSET), type);
    }

    public static class InstantCodec implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

        public static InstantCodec instance() {
            return instance;
        }

        private static final InstantCodec instance = new InstantCodec();

        @Override
        public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            return Instant.ofEpochMilli(json.getAsLong());
        }

        @Override
        public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toEpochMilli());
        }

    }





}
