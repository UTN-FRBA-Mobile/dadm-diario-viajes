package utn_frba_mobile.dadm_diario_viajes.storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by toiacabrera on 7/1/17.
 */

public class ImageLoader {
    public final static ImageLoader instance = new ImageLoader();

    private Executor executor = Executors.newFixedThreadPool(2);
    private Handler handler = new Handler(Looper.getMainLooper());
    private Map<ImageView, URL> loadMap = new HashMap<>();
    private Map<URL, Bitmap> bitmaps = new HashMap<>();
    private Set<URL> loading = new HashSet<>();

    private ImageLoader() {
    }

    public void loadImage(final String urlString, ImageView photo) {
        try {
            if(urlString != null) {
                loadImage(new URL(urlString), photo);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadImage(final URL url, ImageView photo) {
        loadMap.put(photo, url);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (!loadMap.containsValue(url)) {
                    return;
                }
                try {
                    final Bitmap bitmap;
                    if (bitmaps.containsKey(url)) {
                        bitmap = bitmaps.get(url);
                    }
                    else if (!loading.contains(url)) {
                        loading.add(url);
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        bitmaps.put(url, bitmap);
                        loading.remove(url);
                    }
                    else {
                        // Se resuelve en otro thread
                        return;
                    }
                    final Set<Map.Entry<ImageView, URL>> entrySet = new HashSet<>(loadMap.entrySet());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (Map.Entry<ImageView, URL> entry : entrySet) {
                                if (url.equals(entry.getValue())) {
                                    entry.getKey().setImageBitmap(bitmap);
                                    loadMap.remove(entry.getKey());
                                }
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
