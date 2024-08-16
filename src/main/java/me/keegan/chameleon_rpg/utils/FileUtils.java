package me.keegan.chameleon_rpg.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.keegan.chameleon_rpg.ChameleonRPG;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtils {
    /**
     *
     * @param filePath The path of the file relative to the plugin's data folder
     * @param fileName The file name with the type of file included in its name
     * @param content Content being saved as a JSON
     */
    public static void saveInFile(String filePath, String fileName, Object content, boolean append) {
        File fileDirectory = new File(ChameleonRPG.getPlugin().getDataFolder().getPath() + "/" + filePath);

        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }

        File file = new File(fileDirectory, fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // began writing to file
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            fileWriter.write(new Gson().toJson(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static JsonObject getFromFile(String filePath) {
        //TODO
        return null;
    }
}
