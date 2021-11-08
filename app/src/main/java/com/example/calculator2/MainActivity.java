package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity  {
    private Button btn_0;//0数字按钮
    private Button btn_1;//1数字按钮
    private Button btn_2;//2数字按钮
    private Button btn_3;//3数字按钮
    private Button btn_4;//4数字按钮
    private Button btn_5;//5数字按钮
    private Button btn_6;//6数字按钮
    private Button btn_7;//7数字按钮
    private Button btn_8;//8数字按钮
    private Button btn_9;//9数字按钮
    private Button btn_point;//小数点按钮
    private Button btn_clear;//clear按钮
    private Button btn_del;//del按钮
    private Button btn_plus;//+按钮
    private Button btn_minus;//-按钮
    private Button btn_multply;//*按钮
    private Button btn_divide;//除号按钮
    private Button btn_equal;//=按钮
    private Button btn_per;//%按钮
    private Button btn_change;//+/-按钮
    private Button btn_radical;//根号按钮
    private TextView editText; //输入框
    private TextView outText;  //输出框

    boolean clear_flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        MyViewModel myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_point = findViewById(R.id.btn_point);
        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multply = findViewById(R.id.btn_multply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_equal = findViewById(R.id.btn_equal);
        btn_per = findViewById(R.id.btn_per);
        btn_change = findViewById(R.id.btn_change);
        btn_radical = findViewById(R.id.btn_radical);
        editText = findViewById(R.id.et_input);
        outText = findViewById(R.id.et_output);


            btn_0.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_0.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_1.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_2.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_3.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_4.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_4.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_5.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_5.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_6.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_7.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_7.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_8.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_8.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_9.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.numberChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_9.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_clear.setOnClickListener(new OnClickListener() {//清空操作，全部置空；
                @Override
                public void onClick(View v) {
                    myViewModel.setInput("");
                    editText.setText("");
                    outText.setText("");
                    myViewModel.isRadical=false;

                }
            });
            btn_del.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!myViewModel.getInput().isEmpty()) {
                        if (myViewModel.getInput().charAt(myViewModel.getInput().length() - 1) == ')')
                            myViewModel.setInput( myViewModel.getInput().substring(0, myViewModel.getInput().length() - 3));
                        else
                            myViewModel.setInput( myViewModel.getInput().substring(0, myViewModel.getInput().length() - 1));
                    }
                    if(myViewModel.isRadical){
                        editText.setText("√"+myViewModel.getInput());
                        if(editText.getText().length()==1){
                            myViewModel.isRadical=false;
                            editText.setText("");
                        }
                    }else
                        editText.setText(myViewModel.getInput());
                }
            });
            btn_plus.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_plus.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_minus.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_minus.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_multply.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_multply.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_divide.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_divide.getText());
                    editText.setText(myViewModel.getInput());

                }
            });
            btn_per.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+btn_per.getText());
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_point.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    myViewModel.setInput(myViewModel.getInput()+ btn_point.getText());
                    // 当前一个操作数为负数时不允许输入小数点
                    if(myViewModel.getInput().length()>=3){
                    if (myViewModel.getInput().charAt(myViewModel.getInput().length() - 2) == ')')
                        myViewModel.setInput( myViewModel.getInput().substring(0, myViewModel.getInput().length() - 1));
                    }
                    editText.setText(myViewModel.getInput());
                }
            });
            btn_radical.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.isRadical=true;
                    editText.setText(btn_radical.getText());
//                    String result = myViewModel.calculate();
//                    if (result.charAt(0) == '(')
//                        result = result.substring(1, result.length() - 1);
//                    if (Double.parseDouble(result) < 0) {
//                        Toast.makeText(MainActivity.this, "负数不能开平方根", Toast.LENGTH_SHORT).show();
//                        result = "error";
//                    } else {
//                        double resultDouble = Double.parseDouble(result);
//                        result = String.valueOf(Math.sqrt(resultDouble));
//                    }
//                    if (result != "error")
//                        myViewModel.setInput( result);
//                    outText.setText(result);
                }
            });
            btn_change.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewModel.operatorChange();
                    if (!myViewModel.getInput().isEmpty()) {
                        System.out.println(myViewModel.getInput());
                        myViewModel.Reverse();
                        editText.setText(myViewModel.getInput());
                    }
                }
            });
            btn_equal.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //当没有输入时，不进行运算操作
                    if (myViewModel.getInput().isEmpty()){
                        System.out.println(myViewModel.getInput());
                        return ;}

                    if(myViewModel.isRadical){
                        editText.setText("√"+myViewModel.getInput());
                    }

                    //当最后输入的一个字符为运算符时，删去该运算符
                    if (myViewModel.isOperator(myViewModel.getInput().charAt(myViewModel.getInput().length() - 1))
                            &&myViewModel.getInput().charAt(myViewModel.getInput().length()-1)!='%') {
                        myViewModel.setInput(myViewModel.getInput().substring(0, myViewModel.getInput().length() - 1));
                        editText.setText(myViewModel.getInput());
                    }

                    String result = myViewModel.calculate();
                    for(int i=0;i<editText.getText().length();i++){
                        if(editText.getText().charAt(i)=='√'){
                            break;
                        }else if(i==editText.getText().length()-1){
                            myViewModel.isRadical=false;
                        }
                    }
                    if (result != "error"&&result!="根号内错误")
                        myViewModel.setInput(result);
                    if(result=="根号内错误"){
                        editText.setText("");
                        myViewModel.isRadical=false;
                        Toast.makeText(getApplicationContext(),"根号内不能带符号或根号内为负数",Toast.LENGTH_LONG).show();
                    }

                    outText.setText(result);


                }
            });
        }


}
