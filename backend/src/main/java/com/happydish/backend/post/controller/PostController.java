package com.happydish.backend.post.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.post.dto.SaveRequestDto;
import com.happydish.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
}
