package com.happydish.backend.item.controller;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.item.dto.EditRequestDto;
import com.happydish.backend.item.dto.SaveRequestDto;
import com.happydish.backend.item.service.ItemService;
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
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestPart(value = "requestDto") SaveRequestDto requestDto,
                                  @RequestPart(value = "image")MultipartFile multipartFile,
                                  @AuthenticationPrincipal PrincipleDetails principleDetails) throws IOException {
        return itemService.save(requestDto, multipartFile, principleDetails);
    }

    @GetMapping()
    public ResponseEntity<?> search(@RequestParam String title,
                                    @PageableDefault(sort = "createdAt",direction = Sort.Direction.DESC)Pageable pageable) {
        return itemService.search(title, pageable);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getDetail(@PathVariable(name = "postId") long id,
                                       @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return itemService.getDetail(id, principleDetails);
    }

    @PostMapping("/{postId}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "postId") long id,
                                    @AuthenticationPrincipal PrincipleDetails principleDetails) {
        return itemService.delete(id, principleDetails);
    }

    @PostMapping("/{postId}/edit")
    public ResponseEntity<?> edit(@PathVariable(name = "postId") long id,
                                  @RequestPart(value = "requestDto") EditRequestDto requestDto,
                                  @RequestPart(value = "image") MultipartFile multipartFile,
                                  @AuthenticationPrincipal PrincipleDetails principleDetails) throws IOException{
        return itemService.edit(id, requestDto, multipartFile, principleDetails);
    }

}
