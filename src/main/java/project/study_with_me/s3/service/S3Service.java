package project.study_with_me.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.study_with_me.s3.dto.ImageUrlResponseDto;
import project.study_with_me.s3.exception.ExceptionTexts;
import project.study_with_me.s3.exception.S3Exception;
import project.study_with_me.s3.validate.ImageValidation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final ImageValidation imageValidation;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Transactional
    public ImageUrlResponseDto studyImageUpload(MultipartFile file) {
        imageValidation.validateImageFileExtention(file);   // 검증

        try {
            return uploadImageToS3(file);
        } catch (IOException e) {
            throw new S3Exception(ExceptionTexts.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }

    }

    private ImageUrlResponseDto uploadImageToS3(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); //파일 명
        String extention = fileName.substring(fileName.lastIndexOf(".")); //확장자 명

        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + fileName; //변경된 파일 명

        InputStream is = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is); //image를 byte[]로 변환

        ObjectMetadata metadata = new ObjectMetadata(); // metadata 생성
        metadata.setContentType("file/" + extention);
        metadata.setContentLength(bytes.length);

        //S3에 요청할 때 사용할 byteInputStream 생성
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try{
            //S3로 putObject 할 때 사용할 요청 객체
            //생성자 : bucket 이름, 파일 명, byteInputStream, metadata
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);
            // 실제로 S3 에 데이터를 넣는 부분
            amazonS3.putObject(putObjectRequest); // put image to S3
        }catch (Exception e){
            throw new S3Exception(ExceptionTexts.PUT_OBJECT_EXCEPTION);
        }finally {
            byteArrayInputStream.close();
            is.close();
        }

        return new ImageUrlResponseDto(amazonS3.getUrl(bucketName, s3FileName).toString());
    }
}


