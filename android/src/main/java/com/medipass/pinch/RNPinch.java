package com.medipass.pinch;

import android.util.Log;

import com.facebook.internal.BundleJSONConverter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.bridge.WritableMap;
import com.medipass.pinch.models.HttpRequest;
import com.medipass.pinch.models.HttpResponse;
import com.medipass.pinch.utils.HttpUtil;
import com.medipass.pinch.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class RNPinch extends ReactContextBaseJavaModule {

    private static final String OPT_METHOD_KEY = "method";
    private static final String OPT_HEADER_KEY = "headers";
    private static final String OPT_BODY_KEY = "body";
    private static final String OPT_SSL_PINNING_KEY = "sslPinning";

    private HttpUtil httpUtil;

    public RNPinch(ReactApplicationContext reactContext) {
        super(reactContext);
        httpUtil = new HttpUtil();
    }

    @Override
    public String getName() {
        return "RNPinch";
    }

    @ReactMethod
    public void fetch(String endpoint, ReadableMap opts, Callback callback) {
        try {
            WritableMap response = Arguments.createMap();
            HttpRequest request = new HttpRequest(endpoint);

            if (opts.hasKey(OPT_BODY_KEY)) {
                request.body = JsonUtil.convertReadableMapToJson(opts.getMap(OPT_BODY_KEY)).toString();
            }
            if (opts.hasKey(OPT_METHOD_KEY)) {
                request.method = opts.getString(OPT_METHOD_KEY);
            }
            if (opts.hasKey(OPT_HEADER_KEY)) {
                request.headers = opts.getMap(OPT_HEADER_KEY);
            }
            if (opts.hasKey(OPT_SSL_PINNING_KEY)) {
                request.certFilename = opts.getMap(OPT_SSL_PINNING_KEY).getString("cert");
            }

            HttpResponse httpResponse = httpUtil.sendHttpRequest(request);
            JSONObject jsonHeaders = new JSONObject(httpResponse.headers.toString());

            response.putInt("status", httpResponse.statusCode);
            response.putString("statusText", httpResponse.statusText);
            response.putString("bodyString", httpResponse.bodyString);
            response.putMap("headers", Arguments.fromBundle(BundleJSONConverter.convertToBundle(jsonHeaders)));

            callback.invoke(null, response);
        } catch(JSONException | IOException | UnexpectedNativeTypeException | KeyStoreException | CertificateException | KeyManagementException | NoSuchAlgorithmException e) {
            Log.e("RNPinch", "Error: " + e);
            callback.invoke(e.toString(), null);
        }
    }
}