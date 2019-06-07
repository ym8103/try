import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

import java.util.logging.Level;

public class BroadcastBatt extends BroadcastReceiver {

    private boolean msgFlag;
    public BroadcastBatt(){
        msgFlag = false;
    }




    @Override
    public void onReceive(Context context, Intent intent) {
        int level = 20;
        int BattLevel=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int BattStat=intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (!msgFlag && BattLevel <= level && BattStat!=BatteryManager.BATTERY_STATUS_CHARGING){
            msgFlag=true;
            Toast.makeText(context,"Low battery: "+BattLevel+"&", Toast.LENGTH_LONG).show();
        }

    }
}
