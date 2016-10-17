package com.localz.pinch.models;

import com.facebook.react.bridge.ReadableMap;

public class HttpRequest {
    public String endpoint;
    public String method;
    public ReadableMap headers;
    public String body;
    public String certFilename;
    public int timeout;

    private static final int DEFAULT_TIMEOUT = 10000;

    public HttpRequest() {
        this.timeout = DEFAULT_TIMEOUT;
    }

    public HttpRequest(String endpoint) {
        this.endpoint = endpoint;
        this.timeout = DEFAULT_TIMEOUT;
    }

    public HttpRequest(String endpoint, String method, ReadableMap headers, String body, String certFilename, int timeout) {
        this.endpoint = endpoint;
        this.method = method;
        this.headers = headers;
        this.body = body;
        this.certFilename = certFilename;
        this.timeout = timeout;
    }
}
