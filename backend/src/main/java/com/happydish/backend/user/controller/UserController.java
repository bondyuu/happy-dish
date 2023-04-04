package com.happydish.backend.user.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.user.dto.EditRequestDto;
import com.happydish.backend.user.dto.SignupRequestDto;
import com.happydish.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SignupRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestPart(value = "requestDto") EditRequestDto requestDto,
                                  @RequestPart(value = "image") MultipartFile multipartFile,
                                  @AuthenticationPrincipal PrincipleDetails principleDetails) throws IOException {
        return userService.edit(requestDto, multipartFile, principleDetails);
    }

    @PostMapping("/{userId}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "userId") long id,
                                    @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return userService.delete(id, principleDetails);
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> getMyPage(@AuthenticationPrincipal PrincipleDetails principleDetails) {
        return userService.getMyPage(principleDetails);
    }
}
