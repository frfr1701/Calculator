package nackademin.frolund.fredrik.calculatorapplication.model;


import nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState;
import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.*;


public class CalculatorModel {

    private CalculatorModelState state;
    private double val1;
    private double val2;

    public CalculatorModel() {
        this.state = DEFAULT;
        this.val1 = Double.NaN;
        this.val2 = Double.NaN;
    }

    public void calculatePreviousNumbers(String infoText){
        if(!Double.isNaN(val1)){
            this.val2 = Double.parseDouble(infoText);
            switch (state){
                case ADDITION:
                    val1 += val2;
                    break;
                case SUBTRACTION:
                    val1 -= val2;
                    break;
                case MULTIPLICATION:
                    val1 *= val2;
                    break;
                case DIVISION:
                    val1 /= val2;
                    break;
                case DEFAULT:
                    break;
            }
        }else{
            this.val1 = Double.parseDouble(infoText);
        }
    }

    public CalculatorModelState getState() {
        return state;
    }

    public void setState(CalculatorModelState state) {
        this.state = state;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }


    public void setVal2(double val2) {
        this.val2 = val2;
    }

    public void OppositeVal1(){
        val1=-val1;
    }

    public double getVal1() {
        return val1;
    }

    public double getVal2() {
        return val2;
    }
}
