package com.happydish.backend.user.service;

import com.happydish.backend.cart.entity.Cart;
import com.happydish.backend.cart.respository.CartRepository;
import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.global.util.S3Uploader;
import com.happydish.backend.review.repository.ReviewRepository;
import com.happydish.backend.user.dto.EditRequestDto;
import com.happydish.backend.user.dto.MypageResponseDto;
import com.happydish.backend.user.dto.SignupRequestDto;
import com.happydish.backend.user.entity.Role;
import com.happydish.backend.user.entity.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final S3Uploader s3Uploader;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ResponseEntity<?> save(SignupRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("중복된 회원");
        }

        User user = User.builder()
                        .email(requestDto.getEmail())
                        .name(requestDto.getName())
                        .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                        .profileUrl("https://bondyu.s3.ap-northeast-2.amazonaws.com/static/user/aa690dcd-eb44-46a6-843e-22b530b98918asdf.jpg")
                        .build();

        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        return ResponseEntity.ok("ok");
    }

    @Transactional
    public ResponseEntity<?> edit(EditRequestDto requestDto,
                                  MultipartFile multipartFile,
                                  PrincipleDetails principleDetails) throws IOException {
        User loginUser = principleDetails.getUser();

        if (!multipartFile.isEmpty()) {
            String url = s3Uploader.uploadFiles(multipartFile,"static/user");
            loginUser.editImage(url);
        }

        if (!requestDto.getPassword().isEmpty()) {
            loginUser.editPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));
        }

        return ResponseEntity.ok(loginUser);
    }

    @Transactional
    public ResponseEntity<?> delete(long id, PrincipleDetails principleDetails) {
        User loginUser = principleDetails.getUser();

        Optional<User> optionalTargetUser = userRepository.findById(id);
        if (optionalTargetUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Target Not Found");
        }
        User targetUser = optionalTargetUser.get();

        if (loginUser.canNotControl(targetUser)) {
            return ResponseEntity.badRequest().body("LoginUser Not Permitted");
        }

        loginUser.deletedBy(Role.ROLE_USER);

        return ResponseEntity.ok("SUCCESS");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMyPage(PrincipleDetails principleDetails) {
        User loginUser = principleDetails.getUser();

        long reviewNum = reviewRepository.countAllByUser(loginUser);

        return ResponseEntity.ok(MypageResponseDto.builder()
                                            .user(loginUser.toUserDto())
                                            .reviewNum(reviewNum)
                                            .build());
    }
}
