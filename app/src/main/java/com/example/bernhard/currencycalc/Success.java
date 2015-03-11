package com.example.bernhard.currencycalc;

/**
 * Created by Bernhard on 07.03.2015.
 */


        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;


public class Success  extends Activity implements OnClickListener{
    private Context context;
    private Button btn_back;
    private TextView tw;

    protected void onCreate(Bundle savedInstanceState) {

        String msg = MainActivity.end_msg;


        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.success);
        tw = (TextView) findViewById(R.id.textView1);
        tw.setText(msg);
        btn_back = (Button) findViewById(R.id.button1);
        btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v == btn_back){
            finish();
        }

    }
}
