package de.tum.moveii.feature.component.logs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.*;
import org.springframework.boot.context.embedded.LocalServerPort;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Alexandru Obada on 05/03/17.
 */
public class LogsSteps {

    private String endpoint;
    private String payload;
    private Response response;

    @LocalServerPort
    private Integer serverPort;

    @Given("^the endpoint \"([^\"]*)\"$")
    public void theEndpoint(String endpoint) {
        this.endpoint = String.format("http://localhost:%d%s", serverPort, endpoint);
    }

    @Given("^the following payload:$")
    public void theFollowingPayload(String payload) {
        this.payload = payload;
    }

    @When("^a get request is performed$")
    public void aGetRequestIsPerformed() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(endpoint).build();
        response = client.newCall(request).execute();
    }

    @When("^a post request is performed$")
    public void aPostRequestIsPerformed() throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse(APPLICATION_JSON_UTF8_VALUE), payload);
        Request request = new Request.Builder().post(body).url(endpoint).build();
        response = client.newCall(request).execute();
    }

    @Then("^the response code is (\\d+)$")
    public void theResponseCodeIs(int expectedResponseCode) {
        int actualResponseCode = response.code();
        assertThat(actualResponseCode).isEqualTo(expectedResponseCode);
    }

    @Then("^the body contains the following fields:$")
    public void theBodyContainsTheFollowingFields(List<FieldContainer> expectedValues) throws IOException {
        String responseBody = response.body().string();
        JsonObject root = new JsonParser().parse(responseBody).getAsJsonObject();
        expectedValues.forEach(expectedValue -> {
            assertThat(root.get(expectedValue.getFieldName()).getAsString()).isEqualTo(expectedValue.getValue());
        });
    }

    @Then("^the response contains (\\d+) items$")
    public void theResponseContainsItems(int expectedLogCount) throws IOException {
        String responseBody = response.body().string();
        JsonArray root = new JsonParser().parse(responseBody).getAsJsonArray();
        assertThat(root.size()).isEqualTo(expectedLogCount);
    }
}
