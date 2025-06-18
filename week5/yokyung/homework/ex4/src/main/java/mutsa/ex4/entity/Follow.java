package mutsa.ex4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor //기본 생성자(JPA 스펙에 따라 객체 만들 때 사용)
@AllArgsConstructor //모든 필드를 받는 생성자(Builder가 내부적으로 사용)
@Builder
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private User toUser;
}
