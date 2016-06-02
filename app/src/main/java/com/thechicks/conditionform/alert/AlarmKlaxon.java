package com.thechicks.conditionform.alert;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import com.thechicks.conditionform.R;
import com.thechicks.conditionform.data.model.AlarmInstance;

import java.io.IOException;

/**
 * Created by opklnm102 on 2016-04-28.
 */
//알람소리, 진동 울리는 역할
//Todo: 핸드폰이 진동, 무음일 경우 처리
public class AlarmKlaxon {

    public static final String TAG = AlarmKlaxon.class.getSimpleName();

    // (지연시간, 진동, 쉼, 진동, 쉼, ...)
    private static final long[] sVibratePattern = new long[]{0, 2000, 200, 2000, 200};  //진동 패턴

    private static final float IN_CALL_VOLUME = 0.125f;

    private static AudioAttributes VIBRATION_ATTRIBUTES;

    private static boolean startedAlarm = false;
    private static boolean startedVibrator = false;

    private static MediaPlayer sMediaPlayer = null;

    public static void stop(Context context) {
        Log.d(TAG, " stop()");

        if (startedAlarm) {
            startedAlarm = false;

            // stop audio playing
            if (sMediaPlayer != null) {
                sMediaPlayer.stop();
                AudioManager audioManager = (AudioManager)
                        context.getSystemService(Context.AUDIO_SERVICE);
                audioManager.abandonAudioFocus(null);
                sMediaPlayer.release();
                sMediaPlayer = null;
            }

            // stop vibrator
            stopVibrator(context);
        }
    }

    // inTelephoneCall - 전화중인지 판단
    public static void start(final Context context, AlarmInstance instance, boolean inTelephoneCall) {
        Log.d(TAG, " start()");

        // Make sure we are stop before starting
        stop(context);

        //default alarm
        Uri alarmNoise = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Log.d(TAG, " Using default alarm" + alarmNoise.toString());

        sMediaPlayer = new MediaPlayer();
        sMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "AlarmKlaxon mediaPlayer error");
                AlarmKlaxon.stop(context);
                return true;
            }
        });

        try {
            if (inTelephoneCall) {
                Log.v(TAG, " Using the in-call alarm");
                sMediaPlayer.setVolume(IN_CALL_VOLUME, IN_CALL_VOLUME);
                setDataSourceFromResource(context, sMediaPlayer, R.raw.chicks);
            } else {
                sMediaPlayer.setDataSource(context, alarmNoise);
            }
            startAlarm(context, sMediaPlayer);

        } catch (Exception e) {
            Log.d(TAG, " using the fallback ringtone");

            try {
                sMediaPlayer.reset();
                setDataSourceFromResource(context, sMediaPlayer, R.raw.chicks);
                startAlarm(context, sMediaPlayer);
            } catch (Exception e2) {
                Log.e(TAG, " failed to play fallback ringtone", e2);
            }
        }

        //진동 울리는가
        startVibrator(context, true);
//        startedVibrator(context, instance.mVibrate);

        startedAlarm = true;
    }

    // 진동
    public static void startVibrator(Context context, boolean isVibrator) {

        //Todo: 오디오 모드에 따른 분기 처리
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        int audioMode = audioManager.getRingerMode();
        switch (audioMode){
            case AudioManager.RINGER_MODE_NORMAL:  //벨소리 모드
                Log.d(TAG, " 벨소리");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:  //진동 모드
                Log.d(TAG, " 진동");
                break;
            case AudioManager.RINGER_MODE_SILENT:  //무음 모드
                Log.d(TAG, " 무음");
                break;
        }


        if (isVibrator) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                VIBRATION_ATTRIBUTES = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build();

                vibrator.vibrate(sVibratePattern, 0, VIBRATION_ATTRIBUTES);
            } else {
                vibrator.vibrate(sVibratePattern, 0);
            }

            startedVibrator = true;
        }
    }

    public static void startVibrator(Context context, long millisecond){
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(millisecond);
    }

    public static void stopVibrator(Context context) {

        if (startedVibrator) {
            // stop vibrator
            ((Vibrator) (context.getSystemService(Context.VIBRATOR_SERVICE))).cancel();
            startedVibrator = false;
        }
    }

    private static void startAlarm(Context context, MediaPlayer player) throws IOException {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        //do not play alarms if stream volume is 0 (typically because ringer mode is silent)
        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_ALARM);
            player.setLooping(true);
            player.prepare();
            audioManager.requestAudioFocus(null, AudioManager.STREAM_ALARM, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            player.start();
        }
    }

    // 소리 파일 load
    private static void setDataSourceFromResource(Context context, MediaPlayer player, int res) throws IOException {

        AssetFileDescriptor afd = context.getResources().openRawResourceFd(res);
        if (afd != null) {
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
        }
    }
}
