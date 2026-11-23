package pd15.model;

import lombok.Getter;
import pd15.model.user.User;

import java.util.List;

@Getter
public class UserReport {
    public User user;
    public List<Todo> incompleteTodo;
    public List<Post> latestPosts;
    public Weather weather;
    public Country country;
}
