package de.volkswagen.f73.backend.exceptionsHandlerApi;

import de.volkswagen.f73.backend.animal.NoSuchAnimalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionController implements ProblemHandling {

  private static final String KEY_ERROR_CODE = "error_code";
  private static final String KEY_PARAMS = "params";

  @ExceptionHandler(value = {NoSuchAnimalException.class})
  public ResponseEntity<Object> handleNoSuchAnimalException(NoSuchAnimalException exception) {
    // 1. Create payloud containing exception details
    HttpStatus badRequest = BAD_REQUEST;
    CustomApiException apiException =
        new CustomApiException(
            exception.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
    return new ResponseEntity<>(apiException, badRequest);
  }


  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>>handleInvalidArgument(MethodArgumentNotValidException exception) {
    Map<String, String> errorMap = new HashMap<>();
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(errorMap,BAD_REQUEST);
  }
}

//    @ExceptionHandler
//    public ResponseEntity<Problem> handleIllegalArgumentException(final NativeWebRequest request,
//                                                                  IllegalArgumentException
// exception) {
//        return createException(request, new HttpStatusAdapter(BAD_REQUEST),
// BAD_REQUEST.getReasonPhrase(),
//                               exception.getLocalizedMessage(), null, Collections.emptyList(),
// exception);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<Problem> handleNoSuchAccountException(final NativeWebRequest request,
//                                                                NoSuchAnimalException exception) {
//        return createException(request, new HttpStatusAdapter(NOT_FOUND),
// NOT_FOUND.getReasonPhrase(),
//                               exception.getLocalizedMessage(), null, Collections.emptyList(),
// exception);
//    }
//
//    private ResponseEntity<Problem> createException(final NativeWebRequest request, final
// StatusType statusType,
//                                                    final String title, final String detail, final
// Integer errorCode,
//                                                    final List<String> params, final Throwable
// throwable) {
//
//        ProblemBuilder builder = Problem.builder()
//                .withTitle(title)
//                .withStatus(statusType)
//                .with(KEY_ERROR_CODE, errorCode);
//        final ThrowableProblem problem = builder.build();
//        return create(throwable, problem, request);
//    }

 /*   @ExceptionHandler(MethodArgumentNotValidException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ResponseEntity<?> processValidationError(MethodArgumentNotValidException exception) {
         return ResponseEntity
                 .status(HttpStatus.BAD_REQUEST)
                 .body(exception.getMessage());
     }

     @ExceptionHandler(HttpMessageNotReadableException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ResponseEntity<?> processValidationError(HttpMessageNotReadableException exception) {
         return ResponseEntity
                 .status(HttpStatus.BAD_REQUEST)
                 .body(exception.getMessage());
     }
 */
