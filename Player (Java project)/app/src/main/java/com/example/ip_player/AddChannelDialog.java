package com.example.ip_player;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class AddChannelDialog extends AppCompatDialogFragment {
    public EditText editTextName;
    public EditText editTextUrl;
    public AddChannelDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add a channel")
                .setNegativeButton("cancel", null)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString();
                        String stringUri = editTextUrl.getText().toString();
                        listener.applyData(name, stringUri);
                    }
                });

        editTextName = view.findViewById(R.id.edit_channelName);
        editTextUrl = view.findViewById(R.id.edit_channelUrl);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddChannelDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AddChannelDialogListener");
        }
    }

    public interface AddChannelDialogListener {
        void applyData(String name, String stringUri);
    }
}
