package com.example.smahoshooter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class room extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("PF2/sample");
    private DocumentReference DRF = FirebaseFirestore.getInstance().document("Player/name");
    private DocumentReference mDocRef2 = FirebaseFirestore.getInstance().document("intent/intent2");

    private String androidID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("intent", "onClickを実行");

                /// 完了ボタンが押されたら実行されるメソッド。データをFirestoreに保存する。
                String s = "開始";


                /// 変数名（String型）と，値（Object型）をセットで保存するデータ構造Mapを使う。
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put("intent3", s);



                EditText et1 = (EditText) findViewById(R.id.editText2);
                String et2 = et1.getText().toString();
                //プレイヤー２がいなかったら
                if((et2).equals("")){
                    Toast.makeText(room.this, "プレイヤー2がいません", Toast.LENGTH_SHORT).show();
                }else{
                    //roomクラスに画面遷移
                    Intent intent = new Intent(room.this, camera.class);
                    startActivity(intent);
                }

                /// データを保存するとともに，保存が完了した場合に，onCompleteメソッドを実行する。
                mDocRef2.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            /// 保存が成功した場合，Logcatの「intent3」欄に表示。
                            Log.d("intent", "onCompleteで成功");
                        } else {
                            /// 保存が失敗した場合，Logcatの「intent3」欄に表示。例外の詳細も表示。
                            Log.d("intent", "onCompleteで失敗", task.getException());
                        }

                    }

                });

            }
        });



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

                    String s = documentSnapshot.getString("Playername1"); // 変数名Playername1の値を取得
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
                    String e2 = documentSnapshot.getString("Playername2"); // 変数名Player2の値を取得
                    ((EditText) findViewById(R.id.editText2)).setText(e2);//editText2にPlayer2を表示
                } else if (e != null) {
                    Log.d("Player", "onEventでエラー", e);
                }
            }
        });


    }
}
