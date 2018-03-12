package nackademin.frolund.fredrik.calculatorapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import nackademin.frolund.fredrik.calculatorapplication.controller.CalculatorController;
import nackademin.frolund.fredrik.calculatorapplication.model.CalculatorModel;
import nackademin.frolund.fredrik.calculatorapplication.model.state.CalculatorModelState;
import nackademin.frolund.fredrik.calculatorapplication.view.CalculatorView;

public class CalculatorApplication extends AppCompatActivity{

    private CalculatorView calculatorView;
    private CalculatorModel calculatorModel;
    private CalculatorController calculatorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorModel = new CalculatorModel();
        calculatorView = new CalculatorView(getTextViews());
        calculatorController = new CalculatorController(calculatorView, calculatorModel, getButtons());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("calc_result", calculatorView.getResultString());
        outState.putString("calc_info", calculatorView.getInfoString());
        outState.putSerializable("calculator_action", calculatorModel.getState());
        outState.putDouble("calc_val1", calculatorModel.getVal1());
        outState.putDouble("calc_val2", calculatorModel.getVal2());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorView.setResultText(savedInstanceState.getString("calc_result"));
        calculatorView.setInfoText(savedInstanceState.getString("calc_info"));
        calculatorModel.setVal1(savedInstanceState.getDouble("calc_val1"));
        calculatorModel.setVal2(savedInstanceState.getDouble("calc_val2"));
        calculatorModel.setState((CalculatorModelState) savedInstanceState.getSerializable("calculator_action"));
    }
    public View getViewById(int id){
        return findViewById(id);
    }
    public List<View> getButtons(){
        return Arrays.asList(getViewById(R.id.btn_0), getViewById(R.id.btn_1), getViewById(R.id.btn_2),
                getViewById(R.id.btn_3), getViewById(R.id.btn_4), getViewById(R.id.btn_5), getViewById(R.id.btn_6),
                getViewById(R.id.btn_7), getViewById(R.id.btn_8), getViewById(R.id.btn_9), getViewById(R.id.btn_addera),
                getViewById(R.id.btn_subbtrahera), getViewById(R.id.btn_multiplicera), getViewById(R.id.btn_dividera),
                getViewById(R.id.btn_i_lika_med), getViewById(R.id.btn_clear), getViewById(R.id.btn_dot));
    }
   public List<TextView> getTextViews(){
        return Arrays.asList((TextView) findViewById(R.id.view_result),(TextView) findViewById(R.id.view_calculation));
   }
}