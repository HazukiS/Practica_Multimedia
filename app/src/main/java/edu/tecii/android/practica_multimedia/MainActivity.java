package edu.tecii.android.practica_multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  Button grabar, reproducir, detener;
    private  MediaRecorder mediaRecorder;
    private  MediaPlayer mediaPlayer;
    private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
    private static final String LOG_TAG = "Grabadora";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grabar = (Button)findViewById(R.id.grabar);
        reproducir = (Button)findViewById(R.id.reproducir);
        detener = (Button)findViewById(R.id.detener);
        detener.setEnabled(false);
        reproducir.setEnabled(false);
    }
    public void rec (View v){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOutputFile(fichero);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        }catch (Exception e){
            Log.e(LOG_TAG, "Fallo en la grabacion");
        }
        mediaRecorder.start();
        grabar.setEnabled(false);
        detener.setEnabled(true);
        reproducir.setEnabled(false);
    }
    public void play (View v){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fichero);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            Log.e(LOG_TAG, "Error en la Reproduccion");
        }
    }
    public void stop (View v){
        mediaRecorder.stop();
        mediaRecorder.release();
        grabar.setEnabled(true);
        detener.setEnabled(false);
        reproducir.setEnabled(true);
    }
}
