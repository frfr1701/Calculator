package nackademin.frolund.fredrik.calculatorapplication.view;

import android.widget.TextView;

import java.util.List;

public class CalculatorView{

    private TextView info;
    private TextView result;

    public CalculatorView(List <TextView> views){
        this.result = views.get(0);
        this.info = views.get(1);
    }

    public TextView getInfo() {
        return info;
    }

    public void setInfo(TextView info) {
        this.info = info;
    }

    public void appendInfoString(CharSequence input) {
        this.info.append(input);
    }

    public void setResultText(CharSequence input) {
        this.result.setText(input);
    }

    public void setResultText(int input) {
        this.result.setText(input);
    }

    public void setInfoText(String input) {
        this.info.setText(input);
    }

    public String getInfoString() {
        return info.getText().toString();
    }

    public String getResultString() {
        return result.getText().toString();
    }

    public void setTextForOperator(String input, String value){
        result.setText(String.format("%s%s", value, input));
        clearInfo();
    }
    public void clearInfo(){
        info.setText(null);
    }

    public void clearResult(){
        result.setText(null);
    }

    public boolean IsResultNotContainingEquals(){
        return !result.getText().toString().contains("=");
    }

    public boolean isInfoNotEmpty(){
        return info.getText().length() > 0;
    }


    public boolean isResultNotEmpty(){
        return result.getText().length() > 0;
    }

    public boolean isResultNotInfinity(){
        return !getResultString().equals("You can't divide by 0!");
    }

    public boolean isLastCharOfIndexNotADot(){
        return info.getText().charAt(info.getText().toString().length()-1) != '.';
    }

}
