package me.keegan.chameleon_rpg.utils.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.keegan.chameleon_rpg.ChameleonRPG;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtils {
    /**
     * Adds the plugin's dataFolderPath to the recipient string and returns it
     */
    private static String addDataFolderPath(String recipient) {
        return ChameleonRPG.getPlugin().getDataFolder().getPath() + "/" + recipient;
    }

    /**
     * @param filePath The path of the file relative to the plugin's data folder
     * @param fileName The file name with the type of file included in its name
     * @param content String of content being saved in the file
     * @param append Append the content with the existing file content if there is any
     */
    public static void saveAsFile(@Nonnull String filePath, @Nullable String fileName, @Nullable String content, boolean append) {
        File fileDirectory = new File(addDataFolderPath(filePath));

        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }

        if (fileName == null) {
            return;
        }

        File file = new File(fileDirectory, fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (content == null) {
            return;
        }

        // began writing to file
        try (FileWriter fileWriter = new FileWriter(file, append);
             FileReader fileReader = new FileReader(file)) {
            fileWriter.write((fileReader.read() != -1 ? System.lineSeparator() : "") + content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveAsJson(String filePath, String jsonName, Object content) {
        jsonName = jsonName.endsWith(".json") ? jsonName : jsonName + ".json";

        // create dirs
        saveAsFile(filePath, jsonName, null, false);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter fileWriter = new FileWriter(new File(addDataFolderPath(filePath), jsonName))) {
            fileWriter.write(gson.toJson(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param filePath The path of the file relative to the plugin's data folder
     * @param fileName The file name with the type of file included in its name
     * @return The content from the file in a string; null when the file does not exist
     */
    @Nullable
    public static String getFromFile(String filePath, String fileName) {
        File file = new File(addDataFolderPath(filePath), fileName);

        if (!file.exists()) {
            return null;
        }

        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
