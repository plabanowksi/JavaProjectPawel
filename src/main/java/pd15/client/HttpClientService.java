package pd15.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
public class HttpClientService {
    private final HttpClient client;
    private final ObjectMapper mapper;

    public <T> T get(String url, Class<T> responseType) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() != 200) {
                throw new RuntimeException("Http error: " + response.statusCode());
            }
            return mapper.readValue(response.body(), responseType);

        } catch (Exception e) {
            throw new RuntimeException("Error with downloading data", e); //ZAPYTAĆ
        }

    }

    public <T> List<T> getList(String url, TypeReference<List<T>> typeReference) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                    "HTTP error: " + response.statusCode()
                );
            }

            return mapper.readValue(
                response.body(),
                typeReference
            );

        } catch (Exception e) {
            throw new RuntimeException("Error fetching list", e);
        }
    }
}
