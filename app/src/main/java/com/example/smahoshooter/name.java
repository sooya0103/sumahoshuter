package com.example.smahoshooter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.smahoshooter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class name extends AppCompatActivity {
    private DocumentReference DRF = FirebaseFirestore.getInstance().document("Player/name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Player", "onClickを実行");

                /// 完了ボタンが押されたら実行されるメソッド。データをFirestoreに保存する。
                EditText etSend2 = (EditText) findViewById(R.id.editText1);
                String e = etSend2.getText().toString();


                /// 送信文が空だったら，何もせずに終わる。
                if (e.isEmpty()) return;

                /// 変数名（String型）と，値（Object型）をセットで保存するデータ構造Mapを使う。
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("Playername2", e);/// 変数Playername2と，送信文をセットで保存。

                //ルーム２に画面遷移
                Intent intent = new Intent(name.this,room2.class);
                startActivity(intent);


                /// データを保存するとともに，保存が完了した場合に，onCompleteメソッドを実行する。
                DRF.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            /// 保存が成功した場合，Logcatの「Player」欄に表示。
                            Log.d("Player", "onCompleteで成功");
                        } else {
                            /// 保存が失敗した場合，Logcatの「Player」欄に表示。例外の詳細も表示。
                            Log.d("Player", "onCompleteで失敗", task.getException());
                        }

                    }

                });


            }
        });



    }
}