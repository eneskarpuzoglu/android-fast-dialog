package karpuzoglu.enes.com.fastdialog;

import android.content.Context;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    public static FastDialogBuilder l(Context context){
        return new FastDialogBuilder(context,Type.LOGIN);
    }
    public static FastDialogBuilder f(Context context){
        return new FastDialogBuilder(context,Type.FOLDER);
    }
    public void show(){
        if (builder.getDialog().isShowing()) return;
        builder.getDialog().show();
    }
    public void dismiss(){
        if (builder != null && builder.getDialog() != null && builder.getDialog().isShowing()) builder.getDialog().dismiss();
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
    public void setProgressText(String str){
        if (builder.getType() == Type.PROGRESS){
            TextView textView = builder.getDialog().findViewById(R.id.wait_text);
            textView.setText(str);
        }
    }
    public void positiveClickListener(PositiveClick click){
        builder.positiveClickListener(click);
    }
    public void negativeClickListener(NegativeClick click){
        builder.negativeClickListener(click);
    }
    public void dismissListener(DismissListener dismissListener){builder.setDismissListener(dismissListener);}
    public boolean isShowing(){
        if (builder != null && builder.getDialog() != null){
            return builder.getDialog().isShowing();
        }else{
            return false;
        }

    }
    public String getUsernameOrEmail(){
        String text = null;
        if (builder.getType() == Type.LOGIN){
            ClearableEditText etUsername = builder.getDialog().findViewById(R.id.login_dialog_username);
            text = etUsername.getText().toString().trim();
        }
        return text;
    }
    public String getPassword(){
        String text = null;
        if (builder.getType() == Type.LOGIN){
            ClearableEditText etPassword = builder.getDialog().findViewById(R.id.login_dialog_password);
            text = etPassword.getText().toString().trim();
        }
        return text;
    }
}
