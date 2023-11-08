package com.example.demo.repository;

import com.example.demo.entity.Login;
import com.example.demo.entity.StockInput;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Repository
public class RepoApi {
    @Value("${api.url}")
    public String api_url;

    @Value("${api.username}")
    public String api_username;

    @Value("${api.password}")
    public String api_password;

    private final Logger log = LoggerFactory.getLogger(RepoApi.class);

    public String login() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        User user = new User();

        user.setUsername(api_username);
        user.setPassword(api_password);

        String string_body = objectMapper.writeValueAsString(user);

        RequestBody body = RequestBody.create(string_body, mediaType);

        Request request = new Request.Builder()
                .url(api_url + "customer-order-login/v1/auth/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() != 200) {
            return "";// "Error:"+Integer.toString(response.code());
        }

        Login login = objectMapper.readValue(response.body().byteStream(), Login.class);

        log.info("result api_code : {}", response.code());
        log.info("result token : {}", login.getAccess_token());

        return login.getAccess_token();
    }

    public int stock_inc(StockInput stock, String accesstoken) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String string_body = objectMapper.writeValueAsString(stock);

        RequestBody body = RequestBody.create(string_body, mediaType);
        Request request = new Request.Builder()
                .url(api_url + "centralized-stock-load/v1/stock/quantity")
                .method("PUT", body)
                .addHeader("Authorization", "Bearer " + accesstoken)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        return response.code();
    }

}
