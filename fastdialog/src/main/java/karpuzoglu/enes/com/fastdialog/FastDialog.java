package karpuzoglu.enes.com.fastdialog;

import android.content.Context;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ENES on 7.12.2018.
 */
public class FastDialog{
    private FastDialogBuilder builder;

    FastDialog(FastDialogBuilder builder) {
        this.builder = builder;
    }

    public static FastDialogBuilder i(Context context){
        return new FastDialogBuilder(context,Type.INFO);
    }
    public static FastDialogBuilder e(Context context){
        return new FastDialogBuilder(context,Type.ERROR);
    }
    public static FastDialogBuilder w(Context context){
        return new FastDialogBuilder(context,Type.WARNING);
    }
    public static FastDialogBuilder d(Context context){
        return new FastDialogBuilder(context,Type.DIALOG);
    }
    public static FastDialogBuilder p(Context context){
        return new FastDialogBuilder(context,Type.PROGRESS);
    }
    public void show(){
        if (builder.getDialog().isShowing()) return;
        builder.getDialog().show();
    }
    public void dismiss(){
        if (builder.getDialog().isShowing()) builder.getDialog().dismiss();
    }
    public String getInputText(){
        ClearableEditText etWarning = builder.getDialog().findViewById(R.id.warning_dialog_et);
        ClearableEditText etWarningDecimal = builder.getDialog().findViewById(R.id.warning_dialog_et_decimal);
        String text = "";
        if (!etWarning.getText().toString().trim().equals("")){
            text = etWarning.getText().toString().trim();
        }else if(!etWarningDecimal.getText().toString().trim().equals("")){
            text =  etWarningDecimal.getText().toString().trim();
        }
        return text;
    }
    public void positiveClickListener(PositiveClick click){
        builder.positiveClickListener(click);
    }
    public void negativeClickListener(NegativeClick click){
        builder.negativeClickListener(click);
    }
    public void dismissListener(DismissListener dismissListener){builder.setDismissListener(dismissListener);}
    public boolean isShowing(){
        return builder.getDialog().isShowing();
    }
}
