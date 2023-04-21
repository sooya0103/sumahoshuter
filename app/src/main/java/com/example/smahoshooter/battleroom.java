package com.example.smahoshooter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.smahoshooter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class battleroom extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("PF2/sample");
    private DocumentReference DRF = FirebaseFirestore.getInstance().document("Player/name");
    private DocumentReference mDocRef2 = FirebaseFirestore.getInstance().document("intent/intent2");
    private DocumentReference mDocRef3 = FirebaseFirestore.getInstance().document("camera3/2");
    private DocumentReference Camera = FirebaseFirestore.getInstance().document("PF3/1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battleroom);

        findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("PF2", "onClickを実行");
                        Log.d("Player", "onClickを実行");
                        Log.d("intent", "onClickを実行");

                        String s = "";//空白をsに代入

                        //パスワード、プレイヤーネーム1、プレイヤーネーム2をリセット
                        Map<String, Object> dataToSave = new HashMap<String, Object>();
                        dataToSave.put("Playername1", s);/// 変数Playername1と，送信文をセットで保存。
                        dataToSave.put("Playername2", s);/// 変数Playername2と，送信文をセットで保存。
                        dataToSave.put("Message", s);/// 変数Messageと，送信文をセットで保存。
                        dataToSave.put("intent3", s);/// 変数intent3と，送信文をセットで保存。
                        dataToSave.put("camera", s);/// 変数Messageと，送信文をセットで保存。
                        dataToSave.put("camera3", s);/// 変数intent3と，送信文をセットで保存。


                        //passnameクラスに遷移
                        Intent intent = new Intent(battleroom.this, passname.class);
                        startActivity(intent);

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
                        mDocRef2.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    /// 保存が成功した場合，Logcatの「intent」欄に表示。
                                    Log.d("intent", "onCompleteで成功");
                                } else {
                                    /// 保存が失敗した場合，Logcatの「intent」欄に表示。例外の詳細も表示。
                                    Log.d("intent", "onCompleteで失敗", task.getException());
                                }

                            }

                        });
                        mDocRef3.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    /// 保存が成功した場合，Logcatの「intent」欄に表示。
                                    Log.d("intent", "onCompleteで成功");
                                } else {
                                    /// 保存が失敗した場合，Logcatの「intent」欄に表示。例外の詳細も表示。
                                    Log.d("intent", "onCompleteで失敗", task.getException());
                                }

                            }

                        });
                        Camera.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    /// 保存が成功した場合，Logcatの「intent」欄に表示。
                                    Log.d("intent", "onCompleteで成功");
                                } else {
                                    /// 保存が失敗した場合，Logcatの「intent」欄に表示。例外の詳細も表示。
                                    Log.d("intent", "onCompleteで失敗", task.getException());
                                }

                            }

                        });
                    }

                }
        );

        findViewById(R.id.button2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //passクラスに遷移
                        Intent intent = new Intent(battleroom.this, pass.class);
                        startActivity(intent);
                    }
                }
        );


    }
}