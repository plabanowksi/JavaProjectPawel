package pd15.model;

import lombok.ToString;

@ToString
public class Todo {
    public long id;
    public long userId;
    public String title;
    public boolean completed;
}
