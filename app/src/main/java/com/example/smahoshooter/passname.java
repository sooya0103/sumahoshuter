package com.example.smahoshooter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smahoshooter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class passname extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("PF2/sample");

    private String androidID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passname);

        androidID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("PF2", "onClickを実行");

                /// 完了ボタンが押されたら実行されるメソッド。データをFirestoreに保存する。
                EditText etSend = (EditText) findViewById(R.id.editText2);
                String s = etSend.getText().toString(); /// 送信文を格納。
                EditText etSend2 = (EditText) findViewById(R.id.editText1);
                String e = etSend2.getText().toString();


                /// 送信文が空だったら，何もせずに終わる。
                if (s.isEmpty()) return;

                /// 変数名（String型）と，値（Object型）をセットで保存するデータ構造Mapを使う。
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("Message", s);
                dataToSave.put("Playername1", e);/// 変数名Message,nameと，送信文をセットで保存。

                dataToSave.put("ID", androidID);

                //roomクラスに画面遷移
                Intent intent = new Intent(passname.this, room.class);
                startActivity(intent);

                /// データを保存するとともに，保存が完了した場合に，onCompleteメソッドを実行する。
                mDocRef.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            /// 保存が成功した場合，Logcatの「PF2」欄に表示。
                            Log.d("PF2", "onCompleteで成功");
                        } else {
                            /// 保存が失敗した場合，Logcatの「PF2」欄に表示。例外の詳細も表示。
                            Log.d("PF2", "onCompleteで失敗", task.getException());
                        }

                    }

                });


            }
        });
    }
}




