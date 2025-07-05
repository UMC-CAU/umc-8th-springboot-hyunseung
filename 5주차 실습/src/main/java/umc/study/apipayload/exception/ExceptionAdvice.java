package umc.study.apipayload.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.study.apipayload.ApiResponse;
import umc.study.apipayload.code.ErrorReasonDTO;
import umc.study.apipayload.code.status.ErrorStatus;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Object body = null;
        // LocalDate 파싱 오류인지 확인
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException ife)
            if (ife.getTargetType() == LocalDate.class)
                body = ApiResponse.onFailure(ErrorStatus._BAD_REQUEST.getCode(),
                        ErrorStatus._BAD_REQUEST.getMessage(),
                        "날짜 형식이 올바르지 않습니다. 형식은 yyyy-MM-dd 이어야 합니다.");

        // 기타 JSON 파싱 오류는 일반적인 메시지로 처리
        if (body == null)
            body = ApiResponse.onFailure(ErrorStatus._BAD_REQUEST.getCode(),
                    ErrorStatus._BAD_REQUEST.getMessage(),
                    "요청 본문의 형식이 잘못되었습니다.");

        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(InvalidPageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleInvalidPageException(InvalidPageException e) {
        return ApiResponse.onFailure(ErrorStatus.INCORRECT_PAGE.getCode(),
                "잘못된 요청입니다.", ErrorStatus.INCORRECT_PAGE.getMessage());
    }

//    @Override
//    public ResponseEntity<Object> handleHandlerMethodValidationException(
//            HandlerMethodValidationException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        e.printStackTrace();
//
//        return super.handleExceptionInternal(
//                e,
//                ApiResponse.onFailure(ErrorStatus._BAD_REQUEST.getCode(), "잘못된 요청입니다.","reason.getMessage()"),
//                headers,
//                status,
//                request
//        );
//    }

    @ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        return handleExceptionInternalConstraint(e,
                ErrorStatus.valueOf(e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException
                                ("ConstraintViolationException 추출 도중 에러 발생"))), //errorCommonStatus
                HttpHeaders.EMPTY, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {


        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage())
                            .orElse("");
                    errors.merge(fieldName, errorMessage,
                            (existingErrorMessage, newErrorMessage) ->
                                    existingErrorMessage + ", " + newErrorMessage);
                });

        return handleExceptionInternalArgs(e, HttpHeaders.EMPTY,
                ErrorStatus._BAD_REQUEST, request, errors);
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();

        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR,
                HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),
                request, e.getMessage());
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<Object> onThrowException(GeneralException generalException,
                                                   HttpServletRequest request) {
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();
        return handleExceptionInternal(generalException,errorReasonHttpStatus,null,request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
                                                           HttpHeaders headers, HttpServletRequest request) {
        return super.handleExceptionInternal(
                e,
                ApiResponse.onFailure(reason.getCode(),reason.getMessage(),null),
                headers,
                reason.getHttpStatus(),
                new ServletWebRequest(request)
        );
    }

    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e,
                                                                ErrorStatus errorCommonStatus,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request,
                                                                String errorPoint) {
        return super.handleExceptionInternal(
                e,
                ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(),
                        errorPoint),
                headers,
                status,
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers,
                                                               ErrorStatus errorCommonStatus,
                                                               WebRequest request,
                                                               Map<String, String> errorArgs) {
        return super.handleExceptionInternal(
                e,
                ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(),
                        errorArgs),
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e,
                                                                     ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers,
                                                                     WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(),
                        null),
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }
}