package com.example.delahuerta.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class ResponseSanitizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    ContentCachingResponseWrapper wrappedResponse
    = new ContentCachingResponseWrapper(
        (HttpServletResponse) response
    );
    chain.doFilter(request, wrappedResponse);

    String responseBody
    = new String(
        wrappedResponse.getContentAsByteArray(),
        StandardCharsets.UTF_8
    );
    String sanitized = sanitizeJson(responseBody);

    response.getWriter().write(sanitized);
    wrappedResponse.copyBodyToResponse();
    }

    private String sanitizeJson(String json) {
    JSONObject jsonObject = new JSONObject(json);
    removeIdAttributes(jsonObject);
    return jsonObject.toString();
    }

    private void removeIdAttributes(JSONObject jsonObject) {
    Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.endsWith("id")) {
                keys.remove();
            } else if (jsonObject.get(key) instanceof JSONObject) {
                removeIdAttributes(jsonObject.getJSONObject(key));
            }
        }
    }
}