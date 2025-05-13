package mutsa.ex4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor //기본 생성자(JPA가 객체 만들 때 사용)
@AllArgsConstructor //모든 필드를 받는 생성자(Builder가 내부적으로 사용)
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값
    private Long id;

    private String nickname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Likes> likes = new ArrayList<>(); //한 유저는 여러 개의 like를 달 수 있으니. onetomany에서는 list로 선언해야한다.

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();


}
