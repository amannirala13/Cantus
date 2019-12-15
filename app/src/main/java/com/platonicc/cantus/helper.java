package com.platonicc.cantus;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class helper {

    private Context context;

    private SoundPool soundPool;
    private int BUTTON_CLICK_SOUND;
    private Vibrator vibrator;


    helper(Context context){
        this.context =context; }


    public void clickEffect() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build();

        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(57, VibrationEffect.DEFAULT_AMPLITUDE));
                }else{
                    vibrator.vibrate(57);
                }
                soundPool.play(BUTTON_CLICK_SOUND, 0.7f, 0.7f, 0, 0, 1f);
            }
        });

        BUTTON_CLICK_SOUND = soundPool.load(context, R.raw.button_touch, 1);
    }


    public void releaseAllEffects(){
        soundPool.release();
        soundPool = null; }

}
