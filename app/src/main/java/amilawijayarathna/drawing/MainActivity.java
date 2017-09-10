package amilawijayarathna.drawing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b_red, b_blu, b_green;
    private Button b_reset, b_plus, b_minus;
    private TextView tv_dotsize;
    private static int DOT_SIZE_INCREMENT = 5;
    private Draw_view v_drawing_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        b_red = (Button)findViewById(R.id.btn_red);
        b_blu = (Button)findViewById(R.id.btn_blu);
        b_green = (Button)findViewById(R.id.btn_green);
        b_reset = (Button)findViewById(R.id.btn_reset);
        b_minus = (Button)findViewById(R.id.btn_minus);
        b_plus = (Button)findViewById(R.id.btn_plus);

        v_drawing_view = (Draw_view)findViewById(R.id.drawig_view);
        tv_dotsize = (TextView) findViewById(R.id.v_dotsize);
        tv_dotsize.setText("Dot Size = "+ v_drawing_view.getDotSize());

        b_red.setOnClickListener(this);
        b_blu.setOnClickListener(this);
        b_green.setOnClickListener(this);
        b_reset.setOnClickListener(this);
        b_plus.setOnClickListener(this);
        b_minus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button _b = (Button)findViewById(view.getId());

        switch (view.getId()){
            case R.id.btn_red: v_drawing_view.setColor(Color.RED);
                Log.d("Button :",_b.getText()+"");
                break;
            case R.id.btn_blu: v_drawing_view.setColor(Color.BLUE);
                Log.d("Button :",_b.getText()+"");
                break;
            case R.id.btn_green: v_drawing_view.setColor(Color.GREEN);
                Log.d("Button :",_b.getText()+"");
                break;
            case R.id.btn_reset: v_drawing_view.reset();
                tv_dotsize.setText("Dot Size = "+ v_drawing_view.getDotSize());
                Log.d("Button :",_b.getText()+"");
                break;
            case R.id.btn_minus: v_drawing_view.changeDotSize(-DOT_SIZE_INCREMENT);
                tv_dotsize.setText("Dot Size = "+ v_drawing_view.getDotSize());
                Log.d("Button :",_b.getText()+"");
                break;
            case R.id.btn_plus: v_drawing_view.changeDotSize(+DOT_SIZE_INCREMENT);
                tv_dotsize.setText("Dot Size = "+ v_drawing_view.getDotSize());
                Log.d("Button :",_b.getText()+"");
                break;



        }

    }
}
