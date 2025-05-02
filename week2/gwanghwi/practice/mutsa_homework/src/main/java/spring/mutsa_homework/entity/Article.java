package spring.mutsa_homework.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Article
{
    private String title;
    private String content;

    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
}
