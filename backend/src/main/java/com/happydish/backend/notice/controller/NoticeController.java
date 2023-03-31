package com.happydish.backend.notice.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.notice.dto.NoticeRequestDto;
import com.happydish.backend.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNotice(@RequestBody NoticeRequestDto requestDto,
                                        @AuthenticationPrincipal PrincipleDetails principleDetails) {

        return noticeService.saveNotice(requestDto, principleDetails);
    }

    @GetMapping()
    public ResponseEntity<?> getNotice(@PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC) Pageable pageable){
        return noticeService.getNotice(pageable);
    }


    @PostMapping("/{noticeId}/edit")
    public ResponseEntity<?> editNotice(@RequestBody NoticeRequestDto requestDto,
                                        @PathVariable(name = "noticeId") long id,
                                        @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return noticeService.editNotice(id, requestDto, principleDetails);
    }
}
