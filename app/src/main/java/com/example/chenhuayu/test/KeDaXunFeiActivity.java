package com.example.chenhuayu.test;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhuayu.test.util.AmrFileDecoder;
import com.example.chenhuayu.test.util.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class KeDaXunFeiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = "AudioRecordTest";
    //语音文件保存路径
    private String FileName = null;

    private SpeechRecognizer speechRecognizer;
    private AmrFileDecoder amrFileDecoder;
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private TextView contentView;
    private RelativeLayout cancel;

    private static final int VOICE_TRANSEFER_STATE = 0x1000;
    private static final int VOICE_TRANSEFER_CANCEL = 0x1001;

    private boolean isTransferSuccess = false;
    private ProgressBar progressBar;

    //语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;

    private Button startRecord;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VOICE_TRANSEFER_STATE:
                    String message = (String) msg.obj;
                    contentView.setText(message);
                    isTransferSuccess = true;
                    cancel.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
//                    mHandler.sendEmptyMessageDelayed(VOICE_TRANSEFER_CANCEL, 3000);
                    break;
                case VOICE_TRANSEFER_CANCEL:
                    KeDaXunFeiActivity.this.finish();
                    speechRecognizer.destroy();
                    break;
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_da_xun_fei);

        SpeechUtility a = SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID+"=59a5101a");

        contentView = (TextView) findViewById(R.id.voice_transfer_content);

        contentView.setText("正在转换...");
        cancel = (RelativeLayout) findViewById(R.id.cancel_transfer_layout);
        cancel.setOnClickListener(this);

        progressBar = (ProgressBar)findViewById(R.id.voice_transfer_progress);

        startRecord = (Button) findViewById(R.id.btn_record);
        startRecord.setOnClickListener(this);
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName += "/audiorecordtest.3gp";

        initSpeechRecognizer();

    }
    private void startDecode(String path) {
        try {
            amrFileDecoder.start(new FileInputStream(path), speechRecognizer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopDecode() {
        speechRecognizer.stopListening();
        amrFileDecoder.stop();
    }

    private void initSpeechRecognizer() {
        amrFileDecoder = new AmrFileDecoder();
        speechRecognizer = SpeechRecognizer.createRecognizer(this, null);
        speechRecognizer.setParameter(SpeechConstant.PARAMS, null);
        speechRecognizer.setParameter(SpeechConstant.DOMAIN, "iat");
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin");
        speechRecognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        speechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        speechRecognizer.setParameter(SpeechConstant.VAD_BOS, "4000");
        speechRecognizer.setParameter(SpeechConstant.VAD_EOS, "4000");
        speechRecognizer.setParameter(SpeechConstant.ASR_PTT, "1");
        speechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");
        speechRecognizer.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
        int res = speechRecognizer.startListening(mRecoListener);

        String amrPath = FileName;

        if(res == ErrorCode.SUCCESS) {
            startDecode(amrPath);
        }
    }

    private RecognizerListener mRecoListener = new RecognizerListener() {
        //听写结果回调接口(返回Json格式结果,用户可参见附录13.1);
        // 一般情况下会通过onResults接口多次返回结果,完整的识别内容是多次结果的累加;
        // 关于解析Json的代码可参见Demo中JsonParser类;
        //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            printResult(results);
        }

        public void onError(SpeechError error) {
            Toast.makeText(KeDaXunFeiActivity.this, error.getErrorDescription(), Toast.LENGTH_SHORT).show();
            finish();
        }

        public void onBeginOfSpeech() {
        }

        public void onVolumeChanged(int volume, byte[] data) {
        }
        public void onEndOfSpeech() {
        }

        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_transfer_layout:
                stopDecode();
                KeDaXunFeiActivity.this.finish();
                break;
            case R.id.btn_record:
                new startRecordListener();
                break;


        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isTransferSuccess) {
            speechRecognizer.destroy();
            KeDaXunFeiActivity.this.finish();
        }
        return super.onTouchEvent(event);
    }

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        Message message = new Message();
        message.what = VOICE_TRANSEFER_STATE;
        message.obj = resultBuffer.toString();
        mHandler.sendMessage(message);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopDecode();
    }

    @Override
    protected void onStop() {
        super.onStop();
        speechRecognizer.destroy();
    }


    //开始录音
    class startRecordListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(FileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
            mRecorder.start();
        }

    }
    //停止录音
    class stopRecordListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }

    }
    //播放录音
    class startPlayListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mPlayer = new MediaPlayer();
            try{
                mPlayer.setDataSource(FileName);
                mPlayer.prepare();
                mPlayer.start();
            }catch(IOException e){
                Log.e(LOG_TAG,"播放失败");
            }
        }

    }
    //停止播放录音
    class stopPlayListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mPlayer.release();
            mPlayer = null;
        }

    }
}
