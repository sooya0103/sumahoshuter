package com.example.smahoshooter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.smahoshooter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.zxing.Result;


import java.util.HashMap;
import java.util.Map;


public class camera extends AppCompatActivity {
    int count = 0;
    int i = 1;
    String s;
    int zandan=0;
    


    private CodeScanner mCodeScanner;
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("camera3/2");
    private DocumentReference Camera = FirebaseFirestore.getInstance().document("PF3/1");
private boolean init_flg = true;
//    private String androidID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(camera.this, scannerView);
        Button btn = findViewById(R.id.button1);
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.heart);
//        androidID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mCodeScanner.startPreview();
                zandan++;
                if (zandan==1){
                    mCodeScanner.startPreview();
                    findViewById(R.id.zandan1).setAlpha(0.25f);
                }
                if (zandan==2){
                    mCodeScanner.startPreview();
                    findViewById(R.id.zandan2).setAlpha(0.25f);
                }
                if (zandan==3){
                    mCodeScanner.startPreview();
                    findViewById(R.id.zandan3).setAlpha(0.25f);
                }
                if (zandan==4){
                    mCodeScanner.startPreview();
                    findViewById(R.id.zandan4).setAlpha(0.25f);
                }
                if (zandan==5){
                    mCodeScanner.startPreview();
                    findViewById(R.id.zandan5).setAlpha(0.25f);
                    Toast.makeText(camera.this, "弾がなくなった！リロードしよう！", Toast.LENGTH_LONG).show();
                }
                if (zandan==5 && i==5){
                    Intent intent = new Intent(camera.this,kekka.class);
                    startActivity(intent);
                }
            }
        });

        mCodeScanner.setDecodeCallback(new DecodeCallback() {

            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("PF2", "実行");

                        /// 送信ボタンが押されたら実行されるメソッド。データをFirestoreに保存する。
                        String s = result.getText().toString(); /// 送信文を格納。
                        Log.d("e","送信分格納" + s);

                        /// 送信文が空だったら，何もせずに終わる。
                        if (s.isEmpty()) return;

                        /// 変数名（String型）と，値（Object型）をセットで保存するデータ構造Mapを使う。
                        Map<String, Object> dataToSave = new HashMap<String, Object>();
                        if (i<=999){
                            dataToSave.put("camera", s+i);/// 変数名Messageと，送信文をセットで保存。

                            Log.d("e","送信分格納を保存" + s);
                            i++;
                        }
                        /// データを保存するとともに，保存が完了した場合に，onCompleteメソッドを実行する。
                        Camera.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
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




//                                         scannerView.setOnClickListener(new View.OnClickListener() {
//                                                   @Override
//                                                   public void onClick(View view) {
//                                                       mCodeScanner.startPreview();
//                                                   }
//                                               });
//                                           }
//                                       });
        });
    }


    /// onCreateの後で自動的に実行されるメソッド。
    /// MainActivityが別の画面により背面に移動後に，再度最前面の表示になった場合にも実行される。
    @Override
    protected void onStart() {
        super.onStart();  /// 親クラスの同じメソッドを実行しておく。

        /// Firestoreの保存データに変更がある場合に，onEventメソッドが実行される。
        mDocRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d("PF2", "onEventを実行");
                if (documentSnapshot == null) return;

               // データが存在する場合に処理を実行。
                if (init_flg == true){
                    init_flg = false;
                    return;
                }
                if (documentSnapshot.exists()) {
                    String s = documentSnapshot.getString("camera3");/// 変数名Messageの値を取得
                Log.d("e","値を取得" + s);
//                if (s == "B " + i) {
//                    Log.d("e","ハートの数を減らす");
                    count++;
                        if (count == 1) {
                            findViewById(R.id.heart1).setAlpha(0.25f);
                            Toast.makeText(camera.this, "攻撃をくらった！残りHP4", Toast.LENGTH_LONG).show();
                        }
                        if (count == 2) {
                            findViewById(R.id.heart2).setAlpha(0.25f);
                            Toast.makeText(camera.this, "攻撃をくらった！残りHP3", Toast.LENGTH_LONG).show();
                        }
                        if (count == 3) {
                            findViewById(R.id.heart3).setAlpha(0.25f);
                            Toast.makeText(camera.this, "攻撃をくらった！残りHP2", Toast.LENGTH_LONG).show();
                        }
                        if (count == 4) {
                            findViewById(R.id.heart4).setAlpha(0.25f);
                            Toast.makeText(camera.this, "攻撃をくらった！残りHP1", Toast.LENGTH_LONG).show();
                        }
                        if (count == 5) {
                            findViewById(R.id.heart5).setAlpha(0.25f);
                            Toast.makeText(camera.this, "ゲームオーバー", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(camera.this,kekka.class);
                            startActivity(intent);
                        }
                    } else if (e != null) {
                        Log.d("PF2", "onEventでエラー", e);
                    }
               //  }

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
//        mCodeScanner.startPreview();
    }


//    @Override
//    protected void onPause() {
//        mCodeScanner.releaseResources();
//        super.onPause();
//    }
}

