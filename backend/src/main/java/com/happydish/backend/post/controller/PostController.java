package com.happydish.backend.post.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.post.dto.comment.CommentRequestDto;
import com.happydish.backend.post.dto.post.EditRequestDto;
import com.happydish.backend.post.dto.post.SaveRequestDto;
import com.happydish.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestPart(value = "requestDto") SaveRequestDto requestDto,
                                  @RequestPart(value = "image")MultipartFile multipartFile,
                                  @AuthenticationPrincipal PrincipleDetails principleDetails) throws IOException {
        return postService.save(requestDto, multipartFile, principleDetails);
    }

    @GetMapping()
    public ResponseEntity<?> search(@RequestParam String title,
                                    @PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC)Pageable pageable) {
        return postService.search(title, pageable);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getDetail(@PathVariable(name = "postId") long id,
                                       @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return postService.getDetail(id, principleDetails);
    }

    @PostMapping("/{postId}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "postId") long id,
                                    @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return postService.delete(id, principleDetails);
    }

    @PostMapping("/{postId}/edit")
    public ResponseEntity<?> edit(@PathVariable(name = "postId") long id,
                                  @RequestPart(value = "requestDto") EditRequestDto requestDto,
                                  @RequestPart(value = "image") MultipartFile multipartFile,
                                  @AuthenticationPrincipal PrincipleDetails principleDetails) throws IOException{
        return postService.edit(id, requestDto, multipartFile, principleDetails);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> saveComment(@PathVariable(name = "postId") long id,
                                         @RequestBody CommentRequestDto requestDto,
                                         @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return postService.saveComment(id, requestDto, principleDetails);
    }
}
