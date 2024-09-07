package project.study_with_me.s3.exception;

import org.webjars.NotFoundException;

public class S3Exception extends NotFoundException {

    public S3Exception(ExceptionTexts exceptionTexts) {
        super(exceptionTexts.getText());
    }
}
