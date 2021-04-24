package data;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Coder {

    public static String encodingRUS(String line)
    {
        return new String(getRightAmountOfBytes(line), StandardCharsets.UTF_8);
    }

    private static byte[] getRightAmountOfBytes(String line)
    {
        byte[] bytes = null;
        try
        {
            bytes = line.getBytes("Cp1251");
            int bytesLength = bytes.length;

            for (int i = 0; i < bytesLength; i++)
            {
                if (bytes[i] == -48 && bytes[i + 1] == 63)
                {
                    bytes[i] = (byte) 208;
                    bytes[i + 1] = (byte) 152;
                }
            }

        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return bytes;
    }
}
