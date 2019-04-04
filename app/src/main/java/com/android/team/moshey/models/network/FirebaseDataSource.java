package com.android.team.moshey.models.network;

import android.util.Log;

import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.entities.MyTicket;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import static com.android.team.moshey.utils.ConstantUtils.COLLECTION_TICKETS;
import static com.android.team.moshey.utils.ConstantUtils.FIELD_VALUE_REMAINING_TICKETS;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class FirebaseDataSource {

    private static final Object LOCK = new Object();
    private static FirebaseDataSource sFirebaseDataSource;
    private FirebaseFirestore mFirebaseFirestoreDb;

    private FirebaseDataSource() {
        mFirebaseFirestoreDb = FirebaseFirestore.getInstance();
    }

    public synchronized static FirebaseDataSource getInstance() {
        if (sFirebaseDataSource == null) {
            synchronized (LOCK) {
                sFirebaseDataSource = new FirebaseDataSource();
            }
        }
        return sFirebaseDataSource;
    }

    /**
     * @return {@link FirestoreRecyclerOptions<>} Query to be observed by adapter for changes
     */
    public FirestoreRecyclerOptions<AvailableTicket> availableTicketQuery() {
        Query lQuery = mFirebaseFirestoreDb.collection(COLLECTION_TICKETS);
        return new FirestoreRecyclerOptions.Builder<AvailableTicket>()
                .setQuery(lQuery, AvailableTicket.class)
                .build();
    }

    /**
     * Book a ticket for a specific destination and decrement from tickets available on firestore
     * and then add to bought tickets
     *
     * @param myTicket ticket that has been booked
     */
    public void bookTicket(MyTicket myTicket) {
        DocumentReference destinationsRef = mFirebaseFirestoreDb
                .collection(COLLECTION_TICKETS)
                .document(myTicket.getFrom().concat("-").concat(myTicket.getTo()));
        mFirebaseFirestoreDb
                .runTransaction(transaction -> {
                    DocumentSnapshot snapshot = transaction.get(destinationsRef);
                    if (snapshot.getLong(FIELD_VALUE_REMAINING_TICKETS) <= 0)
                        throw new FirebaseFirestoreException("No Tickets Left",
                                FirebaseFirestoreException.Code.ABORTED);
                    long leftTickets = snapshot.getLong(FIELD_VALUE_REMAINING_TICKETS) - 1;
                    transaction.update(destinationsRef, FIELD_VALUE_REMAINING_TICKETS, leftTickets);
                    return leftTickets;
                })
                .addOnSuccessListener(aLong -> Log.d("Transaction Success", String.valueOf(aLong)))
                .addOnFailureListener(e -> Log.e("Transactional Error", e.getMessage()));
    }

    /**
     * Registers Ticket to sold tickets by saving ticket id
     *
     * @param ticketId Unique Ticket Id
     */
    public void registerTicket(String ticketId) {
            //TODO Add bought tickets
    }
}
