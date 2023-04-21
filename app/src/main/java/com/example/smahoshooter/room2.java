package com.example.smahoshooter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class room2 extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("PF2/sample");
    private DocumentReference DRF = FirebaseFirestore.getInstance().document("Player/name");
    private DocumentReference mDocRef2 = FirebaseFirestore.getInstance().document("intent/intent2");

    private String androidID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room2);
    }
    protected void onStart() {
        super.onStart();  /// 親クラスの同じメソッドを実行しておく。

        /// Firestoreの保存データに変更がある場合に，onEventメソッドが実行される。
        mDocRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("PF2", "onEventを実行");
                if (documentSnapshot == null) return;

                /// データが存在する場合に処理を実行。
                if (documentSnapshot.exists()) {

                    if(documentSnapshot.getString("ID") != null && documentSnapshot.getString("ID").equals(androidID)){
                        Log.d("PF2","androidIDが一致した。",e);
                        return;
                    }

                    String s = documentSnapshot.getString("Playername1"); //変数名Playername1の値を取得
                    ((EditText) findViewById(R.id.editText1)).setText(s);//editText1にPlayername1を表示
                } else if (e != null) {
                    Log.d("PF2", "onEventでエラー", e);
                }
            }
        });

        DRF.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("Player", "onEventを実行");
                if (documentSnapshot == null) return;

                /// データが存在する場合に処理を実行。
                if (documentSnapshot.exists()) {

                    if(documentSnapshot.getString("ID") != null && documentSnapshot.getString("ID").equals(androidID)){
                        Log.d("Player","androidIDが一致した。",e);
                        return;
                    }

                    String e2 = documentSnapshot.getString("Playername2");// 変数名Player2の値を取得
                    ((EditText) findViewById(R.id.editText2)).setText(e2);//editText2にPlayer2を表示
                } else if (e != null) {
                    Log.d("Player", "onEventでエラー", e);
                }
            }
        });

        mDocRef2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("intent", "onEventを実行");
                if (documentSnapshot == null) return;

                /// データが存在する場合に処理を実行。
                if (documentSnapshot.exists()) {

                    if(documentSnapshot.getString("ID") != null && documentSnapshot.getString("ID").equals(androidID)){
                        Log.d("intent","androidIDが一致した。",e);
                        return;
                    }

                    if (documentSnapshot.getString("intent3").equals("開始")) {
                        Intent intent = new Intent(room2.this, camera3.class);
                        startActivity(intent);
                    }

                } else if (e != null) {
                    Log.d("Player", "onEventでエラー", e);
                }
            }
        });


    }
}