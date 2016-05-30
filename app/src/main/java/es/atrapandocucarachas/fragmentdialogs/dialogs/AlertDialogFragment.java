package es.atrapandocucarachas.fragmentdialogs.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import es.atrapandocucarachas.fragmentdialogs.R;

/**
 * @author Alejandro Platas Mallo
 * @version X.XX
 * @since 30/5/16
 */

public class AlertDialogFragment extends DialogFragment {

    private final static String EXTRA_TITLE = "title";
    private final static String EXTRA_MESSAGE = "message";
    private OnClickListener listener;

    public static AlertDialogFragment newInstance(int title, int message) {
        AlertDialogFragment frag = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_TITLE, title);
        args.putInt(EXTRA_MESSAGE, message);
        frag.setArguments(args);
        return frag;
    }

    public void addListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (listener == null) {
            try {
                listener = (OnClickListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " you must implement OnClickListener or call the method addListener");
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(EXTRA_TITLE);
        int message = getArguments().getInt(EXTRA_MESSAGE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (listener != null) {
                                    listener.onPositiveClick();
                                }
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (listener != null) {
                                    listener.onNegativeClick();
                                }
                            }
                        }
                )
                .create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setPadding(32,32,32,32);
    }

    public interface OnClickListener {
        void onPositiveClick();

        void onNegativeClick();
    }

}
