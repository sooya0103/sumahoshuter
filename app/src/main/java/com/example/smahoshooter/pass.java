package com.example.smahoshooter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class pass extends AppCompatActivity {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("PF2/sample");


    private String androidID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);



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

                    if (documentSnapshot.getString("ID") != null && documentSnapshot.getString("ID").equals(androidID)) {
                        Log.d("PF2", "androidIDが一致した。", e);
                        return;
                    }


                } else if (e != null) {
                    Log.d("PF2", "onEventでエラー", e);
                }
                findViewById(R.id.button1).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText et1 = (EditText) findViewById(R.id.editText2);
                                String et1Str = et1.getText().toString();
                                //取得したパスワードと入力したパスワードを比較
                                if (documentSnapshot.getString("Message").equals(et1Str)){
                                    Intent intent = new Intent(pass.this,name.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(pass.this, "パスワードが違います", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });
    }
}