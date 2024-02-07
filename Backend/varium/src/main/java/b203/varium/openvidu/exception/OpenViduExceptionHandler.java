package b203.varium.openvidu.exception;

import b203.varium.openvidu.controller.OpenViduController;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = OpenViduController.class)
public class OpenViduExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(OpenViduJavaClientException.class)
    public ResponseEntity<ErrorResult> openViduJavaClientExceptionHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("OpenViduJavaClientException", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(OpenViduHttpException.class)
    public ResponseEntity<ErrorResult> openViduHttpExceptionHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("OpenViduHttpException", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

}
