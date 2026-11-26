package pd15.api;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import pd15.client.HttpClientService;
import pd15.exception.*;
import pd15.model.*;
import pd15.model.user.User;
import pd15.model.Weather;

import java.util.List;

@RequiredArgsConstructor
public class ApiGateway {

    private final HttpClientService httpClientService;

    public User getUser(long userId) {

        String url = "https://jsonplaceholder.typicode.com/users/" + userId;

        try {
            return httpClientService.get(url, User.class);

        } catch (UserFetchException e) {
            System.out.println("Cant download user info: " + e.getMessage());
            return null;
        }
    }

    public List<Post> getPosts(long userId) {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";

        try {
            return httpClientService.getList(
                url,
                new TypeReference<>() {
                }
            );

        } catch (PostFetchException e) {
            System.out.println("Cant download user posts info: " + e.getMessage());
            return null;
        }
    }

    public List<Todo> getTodos(long userId) {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        try {
            return httpClientService.getList(
                url,
                new TypeReference<>() {
                }
            );
        } catch (TodoFetchException e) {
            System.out.println("Cant download user todos info: " + e.getMessage());
            return null;
        }
    }


    public Weather getWeather(double latitude, double longitude) {
        String url = "https://api.open-meteo.com/v1/forecast"
            + "?latitude=" + latitude
            + "&longitude=" + longitude
            + "&current=temperature_2m,wind_speed_10m";
        try {
            return httpClientService.get(url, Weather.class);

        } catch (WeatherFetchException e) {
            System.out.println("Cant download weather info: " + e.getMessage());
            return null;
        }
    }

    public UserReport getUserReport(Long userId) {

        User user = getUser(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        double latitude = Double.parseDouble(user.address.geo.lat);
        double longitude = Double.parseDouble(user.address.geo.lng);

        List<Todo> todos = getTodos(userId);
        List<Post> posts = getPosts(userId);
        Weather weather = getWeather(latitude, longitude);

        List<Todo> incompleteTodos = todos == null
            ? List.of()
            : todos.stream()
              .filter(todo -> !todo.completed)
              .toList();

        List<Post> lastThreePosts = posts == null
            ? List.of()
            : posts.stream()
              .skip(Math.max(0, posts.size() - 3))
              .toList();

        UserReport report = new UserReport();

        report.user = user;
        report.incompleteTodo = incompleteTodos;
        report.latestPosts = lastThreePosts;
        report.weather = weather;

        return report;
    }
}
