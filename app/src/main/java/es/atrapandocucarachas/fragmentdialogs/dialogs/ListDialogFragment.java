package es.atrapandocucarachas.fragmentdialogs.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * @author Alejandro Platas Mallo
 * @version 1.00
 * @since 11/9/15
 */

public class ListDialogFragment extends DialogFragment {

    private static final String LOG_TAG = ListDialogFragment.class.getSimpleName();
    private final static String EXTRA_TITLE = "title";
    private final static String EXTRA_ITEMS = "mItems";
    private OnItemClickListener listener;

    private String[] mItems;
    private int title;

    public static ListDialogFragment newInstance(String[] mItems, int title) {
        ListDialogFragment dialogFragment = new ListDialogFragment();

        Bundle args = new Bundle();
        args.putStringArray(EXTRA_ITEMS, mItems);
        args.putInt(EXTRA_TITLE, title);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (listener == null) {
            try {
                listener = (OnItemClickListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " you must implement OnClickListener or call the method addListener");
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItems = getArguments().getStringArray(EXTRA_ITEMS);
            title = getArguments().getInt(EXTRA_TITLE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setItems(mItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(LOG_TAG, String.valueOf(which));
                if (listener != null) {
                    listener.onItemClick(which);
                }
            }
        });

        return builder.create();
    }

    public interface OnItemClickListener {
        void onItemClick(int which);
    }
}
