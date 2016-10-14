package com.medipass.pinch.models;

import com.facebook.react.bridge.ReadableMap;

public class HttpRequest {
    public String endpoint;
    public String method;
    public ReadableMap headers;
    public String body;
    public String certFilename;
    public int timeout;

    public HttpRequest() {}

    public HttpRequest(String endpoint) {
        this.endpoint = endpoint;
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
