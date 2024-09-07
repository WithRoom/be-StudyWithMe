package project.study_with_me.s3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.study_with_me.s3.dto.ImageUrlResponseDto;
import project.study_with_me.s3.service.S3Service;

@Tag(name = "Image", description = "Image Upload, Update, Delete")
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class S3Controller {

    private final S3Service s3Service;

    @Operation(summary = "이미지 업로드")
    @PostMapping(value = "/upload/study", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUrlResponseDto> studyImageUpload(@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok(s3Service.studyImageUpload(file));
    }
}
