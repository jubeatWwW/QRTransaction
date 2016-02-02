package com.example.eric.hackthon_decoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class Next extends Activity{

    private TextView myTextView1,myTextView2,myTextView3;
    private Button Confirm, Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView1 = (TextView) findViewById(R.id.exampleTextView1);
        myTextView2 = (TextView) findViewById(R.id.exampleTextView2);
        myTextView3 = (TextView) findViewById(R.id.exampleTextView3);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("message");
        //myTextView.setText(msg);
        String username = null,bank_code = null, amount = null;
        try {
            username = new JSONObject(msg).getString("username");
            bank_code = new JSONObject(msg).getString("bank_code");
            amount = new JSONObject(msg).getString("amount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myTextView1.setText("username : "+username);
        myTextView2.setText("bank code : "+bank_code);
        myTextView3.setText("amount : " + amount);
        Confirm = (Button)findViewById(R.id.dialogConfirm);
        Cancel = (Button)findViewById(R.id.dialogCancel);
        final AlertDialog alertDialog = getAlertDialog("請再次確認資訊","上述的個人相關資訊正確嗎?");
        Confirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //顯示對話框
                alertDialog.show();
            }
        });
        Cancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //顯示對話框
                Next.this.finish();
            }
        });
    }

    private AlertDialog getAlertDialog(String title,String message){
        //產生一個Builder物件
        Builder builder = new AlertDialog.Builder(Next.this);
        //設定Dialog的標題
        builder.setTitle(title);
        //設定Dialog的內容
        builder.setMessage(message);
        //設定Positive按鈕資料
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
                Toast.makeText(Next.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        //設定Negative按鈕資料
        builder.setNegativeButton("不正確", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
                Toast.makeText(Next.this, "您按下Cancel按鈕", Toast.LENGTH_SHORT).show();
                Next.this.finish();
            }
        });
        //利用Builder物件建立AlertDialog
        return builder.create();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}