package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PasswordHashUtil {
    @Nullable
    public static String md5(@NotNull final String md5) {
        try {
            @NotNull final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
