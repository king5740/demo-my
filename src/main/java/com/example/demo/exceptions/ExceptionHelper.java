package com.example.demo.exceptions;

import com.example.demo.payload.ApiResult;
import com.example.demo.payload.ErrorData;
import com.example.demo.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHelper {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String[] codes = error.getCodes();
            assert codes != null;
            String code = codes[codes.length - 1];
            FieldError fieldError = (FieldError) error;
            errors.add(new ErrorData(error.getDefaultMessage(), 422, fieldError.getField()));
        });
        return new ResponseEntity<>(ApiResult.errorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TypeMismatchException.class})
    public ResponseEntity<?> handleException(TypeMismatchException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<?> handleException(DateTimeParseException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleException(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<?> handleException(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ServletRequestBindingException.class})
    public ResponseEntity<?> handleException(ServletRequestBindingException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<?> handleException(MissingServletRequestPartException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<?> handleException(BindException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> handleException(AccessDeniedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(MessageConstants.YOU_HAVE_NOT_PERMISSION, 403), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = {MissingPathVariableException.class})
    public ResponseEntity<?> handleException(MissingPathVariableException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<?> handleException(NoHandlerFoundException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }


    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(MessageConstants.METHOD_ERROR, 405), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<?> handleException(HttpMediaTypeNotAcceptableException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(MessageConstants.NO_ACCEPTABLE, 406), HttpStatus.NOT_ACCEPTABLE);
    }

    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<?> handleException(HttpMediaTypeNotSupportedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(MessageConstants.METHOD_ERROR, 415), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {ConversionNotSupportedException.class})
    public ResponseEntity<?> handleException(ConversionNotSupportedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {HttpMessageNotWritableException.class})
    public ResponseEntity<?> handleException(HttpMessageNotWritableException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(MessageConstants.INTERNAL_SERVER_ERROR, 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AsyncRequestTimeoutException.class})
    public ResponseEntity<?> handleException(AsyncRequestTimeoutException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), 503), HttpStatus.SERVICE_UNAVAILABLE);
    }


    // FOYDALANUVCHI TOMONIDAN XATO SODIR BO'LGANDA
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(RestException ex) {
        ex.printStackTrace();
        //AGAR RESOURSE TOPILMAGANLIGI XATOSI BO'LSA CLIENTGA QAYSI TABLEDA NIMA TOPILMAGANLIGI HAQIDA XABAR QAYTADI
        if (ex.getFieldName() != null)
            return new ResponseEntity<>(ApiResult.errorResponse(ex.getUserMsg(), ex.getStatus().value()), ex.getStatus());
        //AKS HOLDA DOIMIY EXCEPTIONLAR ISHLAYVERADI
        if (ex.getErrors() != null)
            return new ResponseEntity<>(ApiResult.errorResponse(ex.getErrors()), ex.getStatus());
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getUserMsg(), ex.getStatus().value()), ex.getStatus());
    }

    // FOYDALANUVCHI TOMONIDAN XATO SODIR BO'LGANDA
    @ExceptionHandler(value = {AuthRequestsException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(AuthRequestsException ex) {

        ex.printStackTrace();
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);

    }


}
