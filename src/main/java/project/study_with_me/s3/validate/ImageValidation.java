package project.study_with_me.s3.validate;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import project.study_with_me.s3.exception.ExceptionTexts;
import project.study_with_me.s3.exception.S3Exception;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ImageValidation {

    public void validateImageFileExtention(MultipartFile file) {

        if(file.isEmpty() || Objects.isNull(file.getOriginalFilename())){   // 파일이 존재하는지 검증
            throw new S3Exception(ExceptionTexts.EMPTY_FILE_EXCEPTION);
        }

        String fileName = file.getOriginalFilename();

        int lastDotIndex = fileName.lastIndexOf(".");   // 파일 확장자 존재 유무 검증
        if (lastDotIndex == -1) {
            throw new S3Exception(ExceptionTexts.NO_FILE_EXTENTION);
        }

        String extention = fileName.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");

        if (!allowedExtentionList.contains(extention)) {     // 파일 확장자 검증
            throw new S3Exception(ExceptionTexts.INVALID_FILE_EXTENTION);
        }
    }
}
