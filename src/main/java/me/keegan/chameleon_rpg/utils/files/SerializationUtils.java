package me.keegan.chameleon_rpg.utils.files;

import javax.annotation.Nullable;
import java.io.*;
import java.util.Base64;

public final class SerializationUtils {
    public static String encodeToBase64(Serializable serializable) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();

            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static <T> T decodeFromBase64(String encodedBase64, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(encodedBase64));
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

            Object deserializedObject = objectInputStream.readObject();

            // clazz.cast safely casts the deserializedObject
            return clazz.isInstance(deserializedObject) ? clazz.cast(deserializedObject) : null;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
