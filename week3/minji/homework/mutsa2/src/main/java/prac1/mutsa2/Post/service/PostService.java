package prac1.mutsa2.Post.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac1.mutsa2.Post.dto.LikeRequestDto;
import prac1.mutsa2.Post.dto.PostRequestDto;
import prac1.mutsa2.Post.entity.Post;
import prac1.mutsa2.Post.entity.PostLike;
import prac1.mutsa2.Post.repository.PostLikeRepository;
import prac1.mutsa2.Post.repository.PostRepository;
import prac1.mutsa2.User.entity.User;
import prac1.mutsa2.User.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public Post createPost(PostRequestDto postRequestDto){
        User user = findUserById(postRequestDto.getUserId());
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        categoryService.findCategory(post, postRequestDto.getCategoryIds());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto likerequestDto){
        User user = findUserById(likerequestDto.getUserId());
        Post post = findPostById(likerequestDto.getPostId());

        // 중복 좋아요 방지
        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new IllegalStateException("이미 좋아요를 누른 게시글");
        }
        PostLike postLike = PostLike.builder()
                .user(user)
                .post(post)
                .build();
        postLikeRepository.save(postLike);
    }

    public void unlikePost(LikeRequestDto likerequestDto){
        User user = findUserById(likerequestDto.getUserId());
        Post post = findPostById(likerequestDto.getPostId());
        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않음"));

        postLikeRepository.delete(postLike);
    }


    public List<Post> findPostsByUserId(Long userId){
        return postRepository.findAllByUser_UserId(userId);
    }
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물 없음"));
    }

}
