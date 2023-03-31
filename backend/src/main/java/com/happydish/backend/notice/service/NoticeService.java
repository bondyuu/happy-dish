package com.happydish.backend.notice.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.notice.dto.NoticeRequestDto;
import com.happydish.backend.notice.model.Notice;
import com.happydish.backend.notice.repository.NoticeRepository;
import com.happydish.backend.user.model.User;
import com.happydish.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> saveNotice(NoticeRequestDto requestDto, PrincipleDetails principleDetails) {
        Optional<User> optionalLoginUser = userRepository.findById(principleDetails.getUser().getId());
        if (optionalLoginUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        User loginUser = optionalLoginUser.get();
        if (!loginUser.isAdmin()) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        Notice notice = noticeRepository.save(requestDto.toEntity());

        return ResponseEntity.ok(notice.toResponseDto());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getNotice(Pageable pageable) {
        Page<Notice> noticeList = noticeRepository.findAll(pageable);

        return ResponseEntity.ok(noticeList);
    }
}
