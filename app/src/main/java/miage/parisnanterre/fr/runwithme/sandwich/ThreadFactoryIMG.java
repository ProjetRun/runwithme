package miage.parisnanterre.fr.runwithme.sandwich;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryIMG implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(5); // ou tout autre acte de configuration
        return t;
    }
}