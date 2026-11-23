package pd15;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd15.client.HttpClientService;
import pd15.model.UserReport;
import pd15.api.ApiGateway;

import java.net.http.HttpClient;

public class Main {

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpClientService httpClientService =
            new HttpClientService(client, mapper);

        ApiGateway apiGateway = new ApiGateway(httpClientService);
        UserReport report = apiGateway.getUserReport(1L);


        System.out.println("\nUSER:");
        System.out.println(report.user);
        System.out.println("\nINCOMPLETE TODOS:");
        report.incompleteTodo.forEach(System.out::println);
        System.out.println("\nLAST 3 POSTS:");
        report.latestPosts.forEach(System.out::println);
        System.out.println("\nWEATHER:");
        System.out.println(report.weather);
    }
}