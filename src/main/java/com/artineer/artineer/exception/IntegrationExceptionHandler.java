package com.artineer.artineer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class IntegrationExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handlerMessageIllegalEx(Exception ex) {
        Map<HttpStatus, String> returnStat = new HashMap<>();
        returnStat.put(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<>(returnStat, HttpStatus.OK);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Object> handlerMessageMessagingEx(Exception ex) {
        Map<HttpStatus, String> returnStat = new HashMap<>();
        returnStat.put(HttpStatus.BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<>(returnStat, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotMatchedException.class)
    public void handlerMessageUserMatchedEx(Exception ex, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + ex.getMessage() + "');</script>");
        out.println("<script>history.go(-1);</script>");
        out.flush();
    }
}