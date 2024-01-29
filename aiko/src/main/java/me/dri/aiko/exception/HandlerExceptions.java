package me.dri.aiko.exception;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.aiko.exception.entities.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptions {

    private final String ERROR_MESSAGE_REQUEST = "Failed request";

    private Thrower throwerException = new Thrower();

    public HandlerExceptions() {

    }

    @ExceptionHandler(InvalidFormatEquipmentInput.class)
    public ResponseEntity<ExceptionEntity> failedFormatNameEquipment(HttpServletRequest req, InvalidFormatEquipmentInput e) {
        return this.throwerException.throwException(e,  req, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundMEquipmentModel.class)
    public ResponseEntity<ExceptionEntity> notFoundEquipmentModel(HttpServletRequest req, NotFoundMEquipmentModel e) {
        return this.throwerException.throwException(e,  req, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NotFoundEquipment.class)
    public ResponseEntity<ExceptionEntity> notFoundEquipment(HttpServletRequest req, NotFoundEquipment e) {
        return this.throwerException.throwException(e,  req, HttpStatus.NOT_FOUND);
    }
}
