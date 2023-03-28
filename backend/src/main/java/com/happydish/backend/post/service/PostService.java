package com.happydish.backend.post.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.global.util.S3Uploader;
import com.happydish.backend.post.dto.comment.CommentRequestDto;
import com.happydish.backend.post.dto.post.EditRequestDto;
import com.happydish.backend.post.dto.post.SaveRequestDto;
import com.happydish.backend.post.model.Comment;
import com.happydish.backend.post.model.Post;
import com.happydish.backend.post.model.Status;
import com.happydish.backend.post.repository.CommentRepository;
import com.happydish.backend.post.repository.PostRepository;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
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
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public ResponseEntity<?> save(SaveRequestDto requestDto, MultipartFile multipartFile, PrincipleDetails principleDetails) throws IOException {
        String url = s3Uploader.uploadFiles(multipartFile,"static/posts/");

        Optional<User> optionalUser = userRepository.findByEmail(principleDetails.getUser().getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }
        User user = optionalUser.get();
        Post post = postRepository.save(Post.builder()
                                .title(requestDto.getTitle())
                                .content(requestDto.getContent())
                                .user(user)
                                .url(url)
                                .build());

        return ResponseEntity.ok(post.toPostDto());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> search(String title, Pageable pageable) {
        if (title.equals("")) {
            return ResponseEntity.ok(postRepository.findAllByStatus(Status.ACTIVE, pageable).map(Post::toPostDto));
        }
        return ResponseEntity.ok(postRepository.findAllByTitleContainingAndStatus(title, Status.ACTIVE, pageable).map(Post::toPostDto));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getDetail(long id, PrincipleDetails principleDetails) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }

        Post post = optionalPost.get();

        return ResponseEntity.ok(post.toDetailDtl());
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

    @Transactional
    public ResponseEntity<?> edit(long id, EditRequestDto requestDto,MultipartFile multipartFile, PrincipleDetails principleDetails) throws IOException{
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }
        Post post = optionalPost.get();
        User loginUser = principleDetails.getUser();

        if (!post.getUser().equals(loginUser)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        if (!multipartFile.isEmpty()) {
            String url = s3Uploader.uploadFiles(multipartFile, "/static/posts");
            requestDto.setImageUrl(url);
        }

        post.edit(requestDto);

        return ResponseEntity.ok(post.toPostDto());
    }

    @Transactional
    public ResponseEntity<?> saveComment(long id, CommentRequestDto requestDto, PrincipleDetails principleDetails) {
        User loginUser = principleDetails.getUser();

        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }
        Post post = optionalPost.get();

        Comment comment = commentRepository.save(Comment.builder()
                                        .content(requestDto.getContent())
                                        .post(post)
                                        .user(loginUser)
                                        .build());

        return ResponseEntity.ok(comment.toCommentDto());
    }
}
