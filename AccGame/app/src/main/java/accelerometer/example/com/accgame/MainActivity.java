package accelerometer.example.com.accgame;

import android.os.PowerManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "accelerometer.example.com.accgame.MainActivity";
    private PowerManager.WakeLock mWakeLock;
    private SimulationView simulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        simulationView = new SimulationView(this);
        setContentView(simulationView);
        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
        simulationView.startSimulation();
        // Acquire WakeLock
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWakeLock.release();
        simulationView.stopSimulation();
        // Release WakeLock
    }
}
