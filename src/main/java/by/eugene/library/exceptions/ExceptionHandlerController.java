package by.eugene.library.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(PersonNotFoundException.class)
    public String handleException(PersonNotFoundException exception, Model model) {
        model.addAttribute("errorText", exception.getMessage());
        return "exceptions/404";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String handleException(BookNotFoundException exception, Model model) {
        model.addAttribute("errorText", exception.getMessage());
        return "exceptions/404";
    }

    @ExceptionHandler(BookOwnerNotFoundException.class)
    public String handleException(BookOwnerNotFoundException exception, Model model) {
        model.addAttribute("errorText", exception.getMessage());
        return "exceptions/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        throw new RuntimeException(exception);
    }
}
