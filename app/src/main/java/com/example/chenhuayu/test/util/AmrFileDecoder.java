package com.example.chenhuayu.test.util;

import com.iflytek.cloud.SpeechRecognizer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.kvh.media.amr.AmrDecoder;


/**
 * Created by zhangdongyuan on 16/4/5.
 */
public class AmrFileDecoder implements Runnable {

    private Thread mDecodeThread;

    private InputStream mInputStream;
    private long mDecoderState;

    byte[] readBuffer;
    byte[] readBufferWithoutCompress;

    int playerBufferSize = 0;
    // 8 k * 16bit * 1 = 8k shorts
    static final int SAMPLE_RATE = 8000;
    // 20 ms second
    // 0.02 x 8000 x 2 = 320;160 short
    static final int PCM_FRAME_SIZE = 160;
    static final int AMR_FRAME_SIZE = 32;
    final static int BUFFER_SIZE = 4096;

    boolean isRunning;

    private SpeechRecognizer speechRecognizer;


    public void start(InputStream inputStream, SpeechRecognizer speechRecognizer) {
        this.speechRecognizer = speechRecognizer;
        if (isRunning) {
            return;
        }

        isRunning = true;

        mDecoderState = AmrDecoder.init();
        readBuffer = new byte[AMR_FRAME_SIZE];
        readBufferWithoutCompress = new byte[PCM_FRAME_SIZE];

        mInputStream = inputStream;

        try {
            mInputStream.skip(6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mDecodeThread = new Thread(this);
        mDecodeThread.start();
    }

    public void stop() {
        if (!isRunning)
            return;

        mDecodeThread.interrupt();

        AmrDecoder.exit(mDecoderState);

        isRunning = false;

    }

    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }

    @Override
    public void run() {
        try {
            while (isRunning && mInputStream.read(readBuffer) != -1) {
                // amr frame 32 bytes
                byte[] amrFrame = readBuffer.clone();
                // pcm frame 160 shorts
                short[] pcmFrame = new short[PCM_FRAME_SIZE];
                AmrDecoder.decode(mDecoderState, amrFrame, pcmFrame);

                byte[] pcmData = BytesTransUtil.getInstance().Shorts2Bytes(pcmFrame);
                int res2 = speechRecognizer.writeAudio(pcmData, 0, pcmData.length);
            }
            speechRecognizer.stopListening();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
