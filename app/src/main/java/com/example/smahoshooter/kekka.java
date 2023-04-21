package com.example.smahoshooter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class kekka extends AppCompatActivity {
    private DocumentReference Camera = FirebaseFirestore.getInstance().document("PF3/1");
    private DocumentReference mDocRef2 = FirebaseFirestore.getInstance().document("PF2/sample");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kekka);

        findViewById(R.id.menu1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(kekka.this,start.class);
                        startActivity(intent);
                    }
                }
        );
    }


    protected void onStart() {
        super.onStart();  /// 親クラスの同じメソッドを実行しておく。

        mDocRef2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("PF2", "onEventを実行");
                if (documentSnapshot == null) return;

                /// データが存在する場合に処理を実行。
                if (documentSnapshot.exists()) {
                    String e2 = documentSnapshot.getString("Playername1");// 変数名Player2の値を取得
                    ((EditText) findViewById(R.id.Edit1)).setText(e2);


                } else if (e != null) {
                    Log.d("PF2", "onEventでエラー", e);
                }
            }
        });


        Camera.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("Player", "onEventを実行");
                if (documentSnapshot == null) return;

                /// データが存在する場合に処理を実行。
                if (documentSnapshot.exists()) {
                    if (documentSnapshot.getString("camera").equals("1ダメージ！5")) {
                        ((TextView) findViewById(R.id.text1)).setText("Winner");
                    } else {
                        ((TextView) findViewById(R.id.text1)).setText("Loser");//editText2にPlayer2を表示
                    }


                } else if (e != null) {
                    Log.d("Player", "onEventでエラー", e);
                }
            }
        });
    }
}

