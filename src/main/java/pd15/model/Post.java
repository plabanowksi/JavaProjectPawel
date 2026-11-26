package pd15.model;

import lombok.ToString;

@ToString
public class Post {
    public long id;
    public long userId;
    public String title;
    public String body;
}
