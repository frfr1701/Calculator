package nackademin.frolund.fredrik.calculatorapplication.controller;
import android.view.View;
import android.widget.Button;

import java.util.List;
import nackademin.frolund.fredrik.calculatorapplication.R;
import nackademin.frolund.fredrik.calculatorapplication.model.CalculatorModel;
import nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState;
import nackademin.frolund.fredrik.calculatorapplication.view.CalculatorView;

import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.ADDITION;
import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.DEFAULT;
import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.DIVISION;
import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.MULTIPLICATION;
import static nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState.SUBTRACTION;

public class CalculatorController implements View.OnClickListener {

    private CalculatorView calculatorView;
    private CalculatorModel calculatorModel;

    public CalculatorController(CalculatorView calculatorView, CalculatorModel calculatorModel, List<View> buttons){
        this.calculatorView = calculatorView;
        this.calculatorModel = calculatorModel;
        calculatorView.clearInfo();
        calculatorView.clearResult();
        setupClickListeners(buttons);
    }

    private void setupClickListeners(List<View> buttons){
        for(int index=0;index<buttons.size();index++){
            buttons.get(index).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                AppendNumber((Button) view);
                break;
            case R.id.btn_addera:
                SetOperator(R.id.btn_addera);
                break;
            case R.id.btn_subbtrahera:
                SetOperator(R.id.btn_subbtrahera);
                break;
            case R.id.btn_dividera:
                SetOperator(R.id.btn_dividera);
                break;
            case R.id.btn_multiplicera:
                SetOperator(R.id.btn_multiplicera);
                break;
            case R.id.btn_dot:
                if(calculatorView.isInfoNotEmpty() && calculatorView.isResultNotInfinity() &&
                        calculatorView.IsResultNotContainingEquals() && calculatorView.isLastCharOfIndexNotADot()){
                    calculatorView.appendInfoString(".");
                }
                break;
            case R.id.btn_clear:
                if(calculatorView.isInfoNotEmpty() && calculatorView.IsResultNotContainingEquals()){
                    calculatorView.clearInfo();
                } else if(calculatorView.isResultNotEmpty()){
                    calculatorModel.setVal1(Double.NaN);
                    calculatorModel.setVal2(Double.NaN);
                    calculatorModel.setState(DEFAULT);
                    calculatorView.clearInfo();
                    calculatorView.clearResult();
                }
                break;
            case R.id.btn_i_lika_med:
                if(calculatorView.IsResultNotContainingEquals() && calculatorView.isInfoNotEmpty()) {
                    if(Double.isNaN(calculatorModel.getVal1()) && Double.isNaN(calculatorModel.getVal2())) {
                        calculatorModel.setVal1(Double.parseDouble(calculatorView.getInfoString()));
                        calculatorView.setInfoText(isItAInteger(Double.toString(calculatorModel.getVal1())));
                        calculatorView.setResultText(String.format("%s=%s", isItAInteger(Double.toString(calculatorModel.getVal1())), isItAInteger(Double.toString(calculatorModel.getVal1()))));
                    }else {
                        calculatorModel.calculatePreviousNumbers(calculatorView.getInfoString());
                        calculatorModel.setState(CalculatorModelState.DEFAULT);
                        if (Double.isInfinite(calculatorModel.getVal1())) {
                            calculatorView.setResultText(R.string.InfinityText);
                            calculatorView.clearInfo();
                        } else {
                            calculatorView.setResultText(String.format("%s%s=%s", calculatorView.getResultString(), isItAInteger(Double.toString(calculatorModel.getVal2())), isItAInteger(Double.toString(calculatorModel.getVal1()))));
                            calculatorView.setInfoText(String.format("%s", isItAInteger(Double.toString(calculatorModel.getVal1()))));
                        }
                    }
                }
                break;
            case R.id.btn_backspace:
                if(calculatorView.IsResultNotContainingEquals() && calculatorView.isInfoNotEmpty()){
                    calculatorView.setInfoText(calculatorView.getInfoString().substring(0,calculatorView.getInfoString().length()-1));
                }
                break;
            case R.id.btn_conventional:
                if(calculatorView.isInfoNotEmpty()) {
                    if (calculatorView.IsResultNotContainingEquals()) {
                        if(calculatorView.getInfoString().charAt(0) == '-'){
                            calculatorView.setInfoText(calculatorView.getInfoString().substring(1));
                            calculatorView.setResultText(calculatorView.getInfoString());
                        }else{
                         calculatorView.setInfoText("-" + calculatorView.getInfoString());
                        }
                    } else {
                        calculatorModel.OppositeVal1();
                        calculatorView.setInfoText(isItAInteger(Double.toString(calculatorModel.getVal1())));
                        calculatorView.setResultText(isItAInteger(isItAInteger(Double.toString(calculatorModel.getVal1())) + "=" + isItAInteger(Double.toString(calculatorModel.getVal1()))));
                    }
                }
                break;
        }
    }
    private void AppendNumber(Button button){
        if(calculatorView.IsResultNotContainingEquals() && calculatorView.isResultNotInfinity()) {
            calculatorView.appendInfoString(button.getText());
        }
    }
    private void SetOperator(int id){
        if(calculatorView.isInfoNotEmpty()) {
            calculatorModel.calculatePreviousNumbers(calculatorView.getInfoString());
            switch(id){
                case R.id.btn_multiplicera:
                    calculatorView.setTextForOperator("*", isItAInteger(Double.toString(calculatorModel.getVal1())));
                    calculatorModel.setState(MULTIPLICATION);
                    break;
                case R.id.btn_dividera:
                    calculatorView.setTextForOperator("/", isItAInteger(Double.toString(calculatorModel.getVal1())));
                    calculatorModel.setState(DIVISION);
                    break;
                case R.id.btn_addera:
                    calculatorView.setTextForOperator("+", isItAInteger(Double.toString(calculatorModel.getVal1())));
                    calculatorModel.setState(ADDITION);
                    break;
                case R.id.btn_subbtrahera:
                    calculatorView.setTextForOperator("-", isItAInteger(Double.toString(calculatorModel.getVal1())));
                    calculatorModel.setState(SUBTRACTION);
                    break;
            }

        }
    }
    private String isItAInteger(String value){
        if(value.substring(value.length()-2).equals(".0") || value.substring(value.length()-1).equals(".")){
            return Integer.toString((int)Double.parseDouble(value));
        }else{
            return value;
        }
    }
}