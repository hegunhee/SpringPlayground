package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "error.bad") // 어노테이션 찾은 후에 response.sendError() 를 반환
public class BadRequestException extends RuntimeException {
}
