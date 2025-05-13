package mutsa.ex4.service;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.PostRequestDto;
import mutsa.ex4.dto.response.PostResponseDto;
import mutsa.ex4.entity.Post;
import mutsa.ex4.entity.User;
import mutsa.ex4.repository.PostRepository;
import mutsa.ex4.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createPost(Long userId, @RequestBody PostRequestDto postRequestDto) {
        //userId로 회원 존재 확인 후 user를 받아와서(없으면 error) post에 넣어 post 생성

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .view(postRequestDto.getView())
                .user(user) //userId 저장하기
                .build();
        return postRepository.save(post).getPostId();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return new PostResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getView(),
                post.getUser().getId()
        );
    }

    public List<PostResponseDto> getAllPosts() {
        List<PostResponseDto> postResponseDtos = postRepository.findAll().stream()
                .map(post -> new PostResponseDto(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getView(),
                        post.getUser().getId()
                )).collect(Collectors.toList());
        return postResponseDtos;
    }
}
