package is.uncommon.slimcommons;

import android.content.Context;
import android.net.ConnectivityManager;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;

public class NetworkUtils {

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    public static ConnectionQuality connectionQuality() {
        return ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
    }

    public static void watchConnection(ConnectionClassManager.ConnectionClassStateChangeListener listener) {
        ConnectionClassManager.getInstance().register(listener);
    }

    public static void unwatchConnection(ConnectionClassManager.ConnectionClassStateChangeListener listener) {
        ConnectionClassManager.getInstance().remove(listener);
    }

}
