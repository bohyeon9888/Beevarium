package b203.varium.relation.exception;

import b203.varium.relation.controller.RelationController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = RelationController.class)
public class RelationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(UserMissingException.class)
    public ResponseEntity<ErrorResult> userMissingExceptionHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("UserMissingException", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(BroadcastStationMissingException.class)
    public ResponseEntity<ErrorResult> broadcastStationMissingExceptionHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("BroadcastStationMissingException", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // 로그 기록, 에러 메시지 생성 등
        String errorMessage = "요청 데이터의 형식이 올바르지 않습니다.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "잘못된 파라미터 타입입니다.";

        // ex.getCause()와 같은 메서드를 사용하여 추가 정보를 로깅할 수 있습니다.
        // 로그에 예외의 상세한 정보를 기록
        log.error(errorMessage, ex);

        // 클라이언트에게 반환할 응답
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
