package com.android.team.moshey.utils;

import java.util.Random;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
public final class ConstantUtils {
    public static final String TICKETS_DATABASE_LABEL = "tickets";
    public static final String COLLECTION_TICKETS = "tickets";
    public static final String TICKETS_SYNC_JOB_TAG = "ticket_sync_job";

    private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateTicketId(final int sizeOfTicketId) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfTicketId);
        for (int i = 0; i < sizeOfTicketId; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
