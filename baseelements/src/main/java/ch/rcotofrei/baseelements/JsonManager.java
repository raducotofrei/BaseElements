package ch.rcotofrei.baseelements;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonManager {

    public static <T> T read(@NonNull Context context, String filename, Class<T> tClass) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream ims = assetManager.open(filename);

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);

            return gson.fromJson(reader, tClass);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
