package karpuzoglu.enes.com.fastdialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by ENES on 7.12.2018.
 */
public class FastDialog{
    private static Dialog dialog;

    public FastDialog(FastDialogBuilder builder) {
        dialog = builder.getDialog();
    }

    public static FastDialogBuilder i(Context context){
        FastDialogBuilder dialogBuilder = new FastDialogBuilder(context,0);
        dialog = dialogBuilder.getDialog();
        return dialogBuilder;
    }
    public static FastDialogBuilder e(Context context){
        FastDialogBuilder dialogBuilder = new FastDialogBuilder(context,1);
        dialog = dialogBuilder.getDialog();
        return dialogBuilder;
    }
    public static FastDialogBuilder w(Context context){
        FastDialogBuilder dialogBuilder = new FastDialogBuilder(context,2);
        dialog = dialogBuilder.getDialog();
        return dialogBuilder;
    }
    public static FastDialogBuilder d(Context context){
        FastDialogBuilder dialogBuilder = new FastDialogBuilder(context,3);
        dialog = dialogBuilder.getDialog();
        return dialogBuilder;
    }
    public static FastDialogBuilder p(Context context){
        FastDialogBuilder dialogBuilder = new FastDialogBuilder(context,4);
        dialog = dialogBuilder.getDialog();
        return dialogBuilder;
    }
    public void show(){
        if (dialog.isShowing()) return;
        dialog.show();
    }
    public void dismiss(){
        if (dialog.isShowing()) dialog.dismiss();
    }
    public String getInputText(){
        EditText etWarning = dialog.findViewById(R.id.warning_dialog_et);
        EditText etWarningDecimal = dialog.findViewById(R.id.warning_dialog_et_decimal);
        String text = "";
        if (!etWarning.getText().toString().trim().equals("")){
            text = etWarning.getText().toString().trim();
        }else if(!etWarningDecimal.getText().toString().trim().equals("")){
            text =  etWarningDecimal.getText().toString().trim();
        }
        return text;
    }
}
