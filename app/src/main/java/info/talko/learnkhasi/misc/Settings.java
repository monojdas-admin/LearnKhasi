package info.talko.learnkhasi.misc;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Settings {

    public static String getAPI_KEY() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.getDefault());
        String text = df.format(c);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] textBytes = text.getBytes("iso-8859-1");
            md.update(textBytes, 0, textBytes.length);
            byte[] sha1hash = md.digest();

            StringBuilder buf = new StringBuilder();
            for (byte b : sha1hash) {
                int halfbyte = (b >>> 4) & 0x0F;
                int two_halfs = 0;
                do {
                    buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                    halfbyte = b & 0x0F;
                } while (two_halfs++ < 1);
            }
            return buf.toString();
        }catch (Exception e){
            return "";
        }
    }
}
