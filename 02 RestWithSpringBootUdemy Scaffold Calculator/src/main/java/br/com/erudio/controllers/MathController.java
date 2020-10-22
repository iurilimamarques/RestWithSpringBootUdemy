package br.com.erudio.controllers;

import br.com.erudio.MathOperations;
import br.com.erudio.Validators;
import br.com.erudio.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    private MathOperations mathOperations = new MathOperations();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!Validators.isNumeric(numberOne) || !Validators.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!Validators.isNumeric(numberOne) || !Validators.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!Validators.isNumeric(numberOne) || !Validators.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.multiplication(numberOne, numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable("numberOne") String numberOne,
                                 @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!Validators.isNumeric(numberOne) || !Validators.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.division(numberOne, numberTwo);
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!Validators.isNumeric(numberOne) || !Validators.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.average(numberOne, numberTwo);
    }

    @RequestMapping(value = "/square-root/{numberOne}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable("numberOne") String numberOne) throws Exception {
        if (!Validators.isNumeric(numberOne)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        return mathOperations.squareRoot(numberOne);
    }
}
