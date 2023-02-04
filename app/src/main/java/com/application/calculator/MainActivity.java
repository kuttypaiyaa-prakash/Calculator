package com.application.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,solution;

    MaterialButton ButtonC, ButtonOpenCurly,ButtonCloseCurly;
    MaterialButton ButtonDivide,ButtonX,ButtonAdd,ButtonSub,ButtonEqual;
    MaterialButton Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9;
    MaterialButton Buttondot,ButtonAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);
        assignId(ButtonC,R.id.ButtonC);
        assignId(ButtonOpenCurly,R.id.ButtonOpenCurly);
        assignId(ButtonCloseCurly,R.id.ButtonCloseCurly);
        assignId(ButtonDivide,R.id.ButtonDivide);
        assignId(ButtonX,R.id.ButtonX);
        assignId(ButtonAdd,R.id.ButtonAdd);
        assignId(ButtonSub,R.id.ButtonSub);
        assignId(ButtonEqual,R.id.ButtonEqual);
        assignId(Buttondot,R.id.Buttondot);
        assignId(ButtonAC,R.id.ButtonAC);
        assignId(Button0,R.id.Button0);
        assignId(Button1,R.id.Button1);
        assignId(Button2,R.id.Button2);
        assignId(Button3,R.id.Button3);
        assignId(Button4,R.id.Button4);
        assignId(Button5,R.id.Button5);
        assignId(Button6,R.id.Button6);
        assignId(Button7,R.id.Button7);
        assignId(Button8,R.id.Button8);
        assignId(Button9,R.id.Button9);


    }

        void assignId(MaterialButton btn,int id)
        {
            btn= findViewById(id);
            btn.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
       String dataToCalculate = solution.getText().toString();

       if(buttonText.equals("AC"))
       {
           solution.setText(" ");
           result.setText("0");
           return;
       }

       if(buttonText.equals("="))
       {
           solution.setText(result.getText());
           return;
       }

        if(buttonText.equals("C"))
        {
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else
        {
            dataToCalculate = dataToCalculate + buttonText;
        }

       solution.setText(dataToCalculate);
        String finalResult= getResult( dataToCalculate);

        if(!(finalResult.equals("Err")))
        {
            result.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try
        {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null ).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult= finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "Err";
        }
    }

}