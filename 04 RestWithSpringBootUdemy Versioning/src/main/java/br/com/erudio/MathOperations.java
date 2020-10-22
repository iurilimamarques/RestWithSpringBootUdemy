package br.com.erudio;

public class MathOperations {

    public Double sum(String numberOne, String numberTwo) {
        return (Validators.convertToDouble(numberOne) + Validators.convertToDouble(numberTwo));
    }

    public Double subtraction(String numberOne, String numberTwo) {
        return (Validators.convertToDouble(numberOne) - Validators.convertToDouble(numberTwo));
    }

    public Double multiplication(String numberOne, String numberTwo) {
        return (Validators.convertToDouble(numberOne) * Validators.convertToDouble(numberTwo));
    }

    public Double division(String numberOne, String numberTwo) {
        return (Validators.convertToDouble(numberOne) / Validators.convertToDouble(numberTwo));
    }

    public Double average(String numberOne, String numberTwo) {
        return (Validators.convertToDouble(numberOne) + Validators.convertToDouble(numberTwo)) / 2;
    }

    public Double squareRoot(String numberOne) {
        return Math.sqrt(Validators.convertToDouble(numberOne));
    }
}
