package karpuzoglu.enes.com.fastdialog;

import android.app.Dialog;

/**
 * Created by ENES on 7.12.2018.
 */
public class FastDialog{
    private static Dialog dialog;

    public FastDialog(FastDialogBuilder builder) {
        dialog = builder.getDialog();
    }

    public void show(){
        if (dialog.isShowing()) return;
        dialog.show();
    }
    public void dismiss(){
        if (dialog.isShowing()) dialog.dismiss();
    }
}
