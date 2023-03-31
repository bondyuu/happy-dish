package com.happydish.backend.notice.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.notice.dto.NoticeRequestDto;
import com.happydish.backend.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    public ResponseEntity<?> saveNotice(@RequestBody NoticeRequestDto requestDto,
                                        @AuthenticationPrincipal PrincipleDetails principleDetails) {

        return noticeService.saveNotice(requestDto, principleDetails);
    }
}
