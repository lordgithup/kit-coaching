package it.kit.handler;



import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.kit.exception.BlogNotFoundException;
import it.kit.exception.EnquiryNotFoundException;
import it.kit.exception.EventNotFoundException;
import it.kit.exception.NotificationNotFoundException;


@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleBlogNotFoundException(BlogNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo("BLOG_NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnquiryNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEnquiryNotFoundException(EnquiryNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo("Enquiry_NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
      
    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotificationNotFoundException(NotificationNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo("Notification_NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler( EventNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEventNotFoundException( EventNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo("Event_NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
      
    
   
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGenericException(Exception ex) {
        ErrorInfo errorInfo = new ErrorInfo("INTERNAL_ERROR", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
