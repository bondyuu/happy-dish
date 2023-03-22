package com.happydish.backend.post.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.global.util.S3Uploader;
import com.happydish.backend.post.dto.SaveRequestDto;
import com.happydish.backend.post.model.Post;
import com.happydish.backend.post.model.PostStatus;
import com.happydish.backend.post.repository.PostRepository;
import com.happydish.backend.user.model.Role;
import com.happydish.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public ResponseEntity<?> save(SaveRequestDto requestDto, MultipartFile multipartFile, PrincipleDetails principleDetails) throws IOException {
        String url = s3Uploader.uploadFiles(multipartFile,"static/posts/");

        Post post = postRepository.save(Post.builder()
                                .title(requestDto.getTitle())
                                .content(requestDto.getContent())
                                .user(principleDetails.getUser())
                                .url(url)
                                .build());

        return ResponseEntity.ok(post.toPostDto());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> search(String title, Pageable pageable) {
        if (title.equals("")) {
            return ResponseEntity.ok(postRepository.findAllByStatus(PostStatus.ACTIVE, pageable).map(Post::toPostDto));
        }
        return ResponseEntity.ok(postRepository.findAllByTitleContainingAndStatus(title, PostStatus.ACTIVE, pageable).map(Post::toPostDto));
    }

    @Transactional
    public ResponseEntity<?> delete(long id, PrincipleDetails principleDetails) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }
        Post post = optionalPost.get();
        User loginUser = principleDetails.getUser();

        if (loginUser.canNotControl(post)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        post.deletedBy(loginUser.getRole());

        return ResponseEntity.ok("SUCCESS");
    }
}
