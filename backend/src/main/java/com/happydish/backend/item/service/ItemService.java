package com.happydish.backend.item.service;

import com.happydish.backend.global.auth.PrincipleDetails;
import com.happydish.backend.global.util.S3Uploader;
import com.happydish.backend.item.dto.EditRequestDto;
import com.happydish.backend.item.dto.SaveRequestDto;
import com.happydish.backend.item.model.Item;
import com.happydish.backend.item.model.Status;
import com.happydish.backend.item.repository.ItemRepository;
import com.happydish.backend.user.model.Role;
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
public class ItemService {
    private final ItemRepository itemRepository;
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
        if (!user.getRole().equals(Role.ROLE_ADMIN)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }
        Item item = itemRepository.save(Item.builder()
                                .title(requestDto.getTitle())
                                .content(requestDto.getContent())
                                .url(url)
                                .build());

        return ResponseEntity.ok(item.toPostDto());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> search(String title, Pageable pageable) {
        if (title.equals("")) {
            return ResponseEntity.ok(itemRepository.findAllByStatus(Status.ACTIVE, pageable).map(Item::toPostDto));
        }
        return ResponseEntity.ok(itemRepository.findAllByTitleContainingAndStatus(title, Status.ACTIVE, pageable).map(Item::toPostDto));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getDetail(long id, PrincipleDetails principleDetails) {
        Optional<Item> optionalPost = itemRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }

        Item item = optionalPost.get();

        return ResponseEntity.ok(item.toDetailDtl());
    }

    @Transactional
    public ResponseEntity<?> delete(long id, PrincipleDetails principleDetails) {
        Optional<Item> optionalPost = itemRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }
        Item item = optionalPost.get();
        User loginUser = principleDetails.getUser();

        if (loginUser.canNotControl(item)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        item.deletedBy(loginUser.getRole());

        return ResponseEntity.ok("SUCCESS");
    }

    @Transactional
    public ResponseEntity<?> edit(long id, EditRequestDto requestDto,MultipartFile multipartFile, PrincipleDetails principleDetails) throws IOException{
        Optional<Item> optionalPost = itemRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.badRequest().body("Post Not Found");
        }
        Item item = optionalPost.get();
        User loginUser = principleDetails.getUser();

        if (loginUser.canNotControl(item)) {
            return ResponseEntity.badRequest().body("Not Permitted");
        }

        if (!multipartFile.isEmpty()) {
            String url = s3Uploader.uploadFiles(multipartFile, "/static/posts");
            requestDto.setImageUrl(url);
        }

        item.edit(requestDto);

        return ResponseEntity.ok(item.toPostDto());
    }

}
