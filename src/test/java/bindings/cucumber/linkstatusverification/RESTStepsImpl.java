package bindings.cucumber.linkstatusverification;

import bindings.cucumber.funcjonal.Utils;
import bindings.cucumber.funcjonal.pages.orange.StoryProxyComponent;
import io.cucumber.datatable.DataTable;
import io.vavr.control.Option;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Collectors;

import static bindings.cucumber.linkstatusverification.Buffer.get;
import static bindings.cucumber.linkstatusverification.Buffer.getStringValue;
import static bindings.cucumber.linkstatusverification.RESTClient.getHeadersByName;

@StoryProxyComponent
    public class RESTStepsImpl {

    private static final Logger LOGGER =
            LogManager.getLogger(RESTStepsImpl.class);
    private static final String REQUEST_LOG = "Sending %s request to %s/%s...";
    private static final List<Integer> ACCEPTED_RESPONSE_CODES =
            Arrays.asList(200, 201, 204, 303, 412);
    private static final int MAX_REQUEST_RESEND_ATTEMPTS = 2;
    private static HttpURLConnection connection;
    private static Map<String, String> commonHeaders = new HashMap<>();

    /**
     * Gets REST response from Buffer and converts it to JSONObject, then reads the needed value from
     * provided path
     *
     * @param attribute path to searched attribute's value (eg. 'products.0.productOffers.0.productId'
     *        returns productId value from first object of the products and productOffers arrays)
     * @param response name of the response saved in the Buffer (with saveResponse method from
     *        TestDataSteps class)
     * @return the value of attribute from provided path
     */
    private static JSONObject jObject;
    private static JSONArray jArray;
    private final RestClientAddressProvider restClientAddressProvider;
    private final RESTClient restClient;
    private final List<CookiesHolder> cookiesHolders;

    public RESTStepsImpl(final RestClientAddressProvider restClientAddressProvider,
                         final RESTClient restClient,
                         final List<CookiesHolder> cookiesHolders) {

        this.restClientAddressProvider = restClientAddressProvider;
        this.restClient = restClient;
        this.cookiesHolders = cookiesHolders;
    }

    public static HttpURLConnection getConnection() {
        return connection;
    }

    private static String buildURLWithParams(final String baseUrl, final DataTable dataTable) {
        String url = baseUrl;
        final List<List<String>> rows = dataTable.asLists();
        if (!rows.isEmpty())
            url += "?";
        final String params = rows.stream().map(list -> list.get(0) + "=" + getStringValue(list.get(1)))
                .collect(Collectors.joining("&"));
        return url + params;
    }

    // Refactor this to use JSONPath
    static String getAttributeFromRestResponse(final String attribute, final String response,
                                               final boolean numeric) {

        jObject = new JSONObject(getStringValue(response));
        jArray = new JSONArray();
        if (attribute.contains(".")) {
            final String[] parts = attribute.split("\\.");
            final boolean currentIsArray = checkIfCurrentIsArray(parts);
            return getJsonObjectAsString(currentIsArray, parts, numeric);
        } else {
            if (numeric) {
                return Integer.toString(jObject.getInt(attribute));
            } else {
                return jObject.getString(attribute);
            }
        }
    }

    private static boolean checkIfCurrentIsArray(final String[] parts) {
        boolean currentIsArray = false;
        for (int i = 0; i < parts.length - 1; i++) {
            if (StringUtils.isNumeric(parts[i + 1])) {
                jArray = createJsonNumericArray(currentIsArray, parts, i);
                currentIsArray = true;
            } else {

                jObject = createJsonObject(currentIsArray, parts, i);
                currentIsArray = false;
            }
        }
        return currentIsArray;
    }

    private static JSONArray createJsonNumericArray(final boolean currentIsArray,
                                                    final String[] parts, final int i) {
        if (currentIsArray) {
            return jArray.getJSONArray(Integer.parseInt(parts[i]));
        } else {
            return jObject.getJSONArray(parts[i]);
        }
    }

    private static JSONObject createJsonObject(final boolean currentIsArray, final String[] parts,
                                               final int i) {
        if (currentIsArray) {
            return jArray.getJSONObject(Integer.parseInt(parts[i]));
        } else {
            return jObject.getJSONObject(parts[i]);
        }
    }

    private static String getJsonObjectAsString(final boolean currentIsArray, final String[] parts,
                                                final boolean numeric) {

        if (currentIsArray && !numeric) {
            return jArray.getString(Integer.parseInt(parts[parts.length - 1]));
        }

        if (!currentIsArray && !numeric) {
            return jObject.getString(parts[parts.length - 1]);
        }

        if (currentIsArray) {
            return Integer.toString(jArray.getInt(Integer.parseInt(parts[parts.length - 1])));
        }
        return Integer.toString(jObject.getInt(parts[parts.length - 1]));
    }

    private static String buildRequestBody(final DataTable dataTable) {

        final List<List<String>> rows = dataTable.asLists();
        return "{" +
                rows.stream()
                        .map(row -> {
                            final String value = Arrays.stream(row.get(1).split(", "))
                                    .map(Buffer::getStringValue)
                                    .collect(Collectors.joining(", "));
                            if (value.isEmpty()) {
                                return null;
                            }
                            final boolean nested = value.startsWith("{") || value.startsWith("[")
                                    || value.equals("true") || value.equals("false");
                            return String.format(nested ? "\"%s\": %s" : "\"%s\": \"%s\"", row.get(0), value);
                        }).filter(Objects::nonNull)
                        .collect(Collectors.joining(", "))
                +
                "}";
    }

    private static void setCookies() {
        final List<String> cookies = getHeadersByName(connection, "Set-Cookie");
        LOGGER.info("Received cookies: " + cookies);

        setCookies(cookies);
    }

    public static void setCookies(final List<String> cookies) {
        if (cookies != null) {
            cookies.forEach(c -> {
                final String[] parts = c.split("[=;]");
                Buffer.set(parts[0], parts[1]);
            });
        }
    }

    private static void setCookies(final String cookies) {
        setCookies(Arrays.asList(cookies.split("; ")));
    }

    private static void updateConnection(final HttpURLConnection newConnection) {
        connection = newConnection;
    }

    private static void initHeaders() {
        commonHeaders = new HashMap<>();
    }

    private <T> void sendRequestWithRetries(
            final CheckedFunction<T> checkedFunction)
            throws IOException {
        int retries = MAX_REQUEST_RESEND_ATTEMPTS;
        while (retries > 0) {
            final T responseCode = checkedFunction.get();
            if (ACCEPTED_RESPONSE_CODES.contains(responseCode)) {
                break;

            } else {
                final String wMessage = String.format("Received %s code!. Retrying.", responseCode);
                LOGGER.warn(wMessage);
                Utils.safeWait(1000);
                retries--;
            }
        }
    }

    void setupHeaders(final DataTable dataTable) {

        initHeaders();
        dataTable.asLists().forEach(row -> commonHeaders.put(row.get(0), row.get(1)));
    }

    void callMethodWithBody(final String methodType, final String method,
                            final DataTable dataTable)
            throws IOException {

        sendRequestWithRetriesAndBodyParameters(methodType, dataTable, method);
    }

    void callMethodWithChangedUrlAndWithBody(final String methodType, final String method,
                                             final String forRepleace, final String param, final DataTable dataTable) throws IOException {

        final String newMethod = method.replace(getStringValue(forRepleace), getStringValue(param));
        sendRequestWithRetriesAndBodyParameters(methodType, dataTable, newMethod);
    }

    private void sendRequestWithRetriesAndBodyParameters(final String methodType,
                                                         final DataTable dataTable,
                                                         final String newMethod) throws IOException {
        sendRequestWithRetries(
                () -> {
                    LOGGER.info(
                            String.format(REQUEST_LOG, methodType,
                                    (Object) this.restClientAddressProvider.provideRestEndpointAddress(),
                                    newMethod));
                    updateConnection(this.restClient.openConnection(methodType, newMethod, commonHeaders(),
                            buildRequestBody(dataTable), true));
                    setCookies();

                    return connection.getResponseCode();
                });
    }

    void callMethodWithParam(final String methodType, final String method, final String param)
            throws IOException {

        sendRequestWithRetries(
                () -> {
                    LOGGER
                            .info(String.format(REQUEST_LOG, methodType,
                                    this.restClientAddressProvider.provideRestEndpointAddress(),
                                    method));
                    updateConnection(this.restClient.openConnection(methodType, method, commonHeaders(),
                            getStringValue(param), true));
                    setCookies();

                    return connection.getResponseCode();
                });
    }

    void sendFormData(final String address, final DataTable dataTable) throws IOException {

        final Map<String, String> parameters = new HashMap<>();
        dataTable.<String, String>asMap(String.class, String.class)
                .forEach((key, value) -> parameters.put(key, getStringValue(value)));
        final String urlParams = "?" + parameters.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));

        final String sessionId =
                this.restClient.sendFormRequest(address + urlParams, commonHeaders(), parameters);
        setCookies(sessionId);
    }

    void sendRequest(final String methodType, final String url) throws IOException {

        sendRequestWithChangedUrlWithRedirect(methodType, url);
    }

    void sendEmptyRequestWithChangedUrl(final String methodType, final String url,
                                        final String forReplace,
                                        final String param) throws IOException {

        final String newUrl = url.replace(getStringValue(forReplace), getStringValue(param));
        sendRequestWithChangedUrlWithRedirect(methodType, newUrl);
    }

    void sendUntilAttributeValueAsExpected(
            final String methodType, final String url, final String forReplace,
            final String param, final String attribute, final String expected) {

        final String newUrl = url.replace(getStringValue(forReplace), getStringValue(param));
        Utils.waitUntil(
                () -> isAttributeValueFromRequestAsExpected(methodType, attribute, newUrl, expected));
    }

    private boolean isAttributeValueFromRequestAsExpected(final String methodType,
                                                          final String attribute,
                                                          final String newUrl, final String expected) {
        try {
            sendRequestWithChangedUrlWithRedirect(methodType, newUrl);
            Buffer.set("response", RESTClient.getStringResponse(connection));
            Buffer.set("responseAttribute",
                    getAttributeFromRestResponse(attribute, "response", false));
        } catch (final IOException e) {
            LOGGER.error("Exception on request to {} : {}", newUrl, e.getMessage());
        }
        return get("responseAttribute").equals(get(expected));
    }

    private void sendRequestWithChangedUrlWithRedirect(final String methodType, final String url)
            throws IOException {

        sendRequestWithRetries(
                () -> {
                    sendRequest(methodType, url, this.restClientAddressProvider.provideRestEndpointAddress(),
                            null,
                            true);
                    return connection.getResponseCode();
                });
    }

    void sendRequestWoRedirect(final String methodType, final String url) throws IOException {

        sendRequestWithRetries(
                () -> {
                    sendRequest(methodType, url, this.restClientAddressProvider.provideRestEndpointAddress(),
                            null,
                            false);
                    return connection.getResponseCode();
                });
    }

    void sendRequestWithParams(final String methodType, final String url,
                               final DataTable dataTable)
            throws IOException {

        sendRequestWithRetries(
                () -> {
                    sendRequest(methodType, buildURLWithParams(url, dataTable));
                    return connection.getResponseCode();
                });
    }

    void sendRequestWithParamsWoRedirect(
            final String methodType, final String url, final DataTable dataTable)
            throws IOException {

        sendRequestWithRetries(
                () -> {
                    sendRequestWoRedirect(methodType, buildURLWithParams(url, dataTable));
                    return connection.getResponseCode();
                });
    }

    private void sendRequest(
            final String methodType, final String url, final String endpoint, final DataTable dataTable,
            final boolean redirect) throws IOException {
        sendRequestWithRetries(
                () -> {
                    final RESTClient client = new RESTClient(endpoint);
                    final Map<String, String> headers = commonHeaders();

                    if (dataTable != null) {
                        dataTable.asLists().forEach(row -> headers.put(row.get(0), row.get(1)));
                    }

                    LOGGER.info(String.format(REQUEST_LOG, methodType, endpoint, url));
                    updateConnection(client.openConnection(methodType, url, headers, null, redirect));
                    setCookies();

                    return connection.getResponseCode();
                });
    }

    void assertResponseCode(final int expectedCode) throws IOException {

        final int code = connection.getResponseCode();
        Assert.assertEquals(
                String.format("Unexpected status code: %d when connecting to %s! Response msg: %s", code,
                        connection.getURL().toString(), getResponseMsg(code)),
                expectedCode, code);
    }

    private String getResponseMsg(final int responseCode) throws IOException {

        final String eMessage = "No response message was provided!";
        if (!(200 <= responseCode && responseCode <= 299)) {
            try (final BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream()))) {
                return bufferedReader.lines().collect(Collectors.joining());

            } catch (final NullPointerException e) {
                LOGGER.warn(e);
                return eMessage;
            }
        } else {
            return eMessage;
        }
    }

    private String buildCookie() {

        final String cookie = this.cookiesHolders.stream()
                .map(CookiesHolder::provideCookieEntry)
                .filter(Option::isDefined)
                .map(Option::get)
                .collect(Collectors.joining("; "));

        LOGGER.info("Prepared cookie: " + cookie);
        return cookie;
    }

    private Map<String, String> commonHeaders() {
        final Map<String, String> headers = new HashMap<>(commonHeaders);
        final String cookie = buildCookie();
        if (!cookie.isEmpty()) {
            headers.put("Cookie", cookie);
        }
        return headers;
    }

    @FunctionalInterface
    public interface CheckedFunction<T> {
        T get() throws IOException;
    }
}
