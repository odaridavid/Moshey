package com.android.team.moshey.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
public final class ConstantUtils {
    public static final String TICKETS_DATABASE_LABEL = "tickets";
    public static final String COLLECTION_TICKETS = "tickets";
    public static final String COLLECTION_BOUGHT = "bought";
    public static final String FIELD_VALUE_REMAINING_TICKETS = "left";
    public static final String MAIN_VIEW_PHOTO_URL = "https://firebasestorage.googleapis.com/v0/b/" +
            "assets-c2287.appspot.com/o/railroad-railroad-track-railway-325200.jpg?alt=media&token=87e7ba7a-ca3c-48f4-98e2-4093149a65fe";

    private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateTicketId(final int sizeOfTicketId) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfTicketId);
        for (int i = 0; i < sizeOfTicketId; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static String buildCurrentDateString(Calendar calendar) {
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date).replace("/", "-");
    }
}
