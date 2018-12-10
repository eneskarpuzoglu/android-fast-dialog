package karpuzoglu.enes.com.fastdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by ENES on 7.12.2018.
 */
public class FastDialogBuilder {
    private Dialog dialog;
    private Context context;
    private TextView tvTitle;
    private TextView tvProgress;
    private LottieAnimationView lawWarning;
    private TextView tvWarning;
    private EditText etWarning;
    private EditText etWarningDecimal;
    private Button btCancel;
    private Button btOk;
    private PossitiveClick possitiveClick;
    private NegativeClick negativeClick;

    public FastDialogBuilder(@NonNull Context context){
        this.context=context;
        dialog =new Dialog(context);
        dialog.setContentView(R.layout.warning_dialog);
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(getDialog().getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lWindowParams);
        tvTitle = dialog.findViewById(R.id.warning_dialog_title);
        lawWarning = dialog.findViewById(R.id.warning_dialog_animation);
        tvWarning = dialog.findViewById(R.id.warning_dialog_text);
        etWarning = dialog.findViewById(R.id.warning_dialog_et);
        etWarningDecimal = dialog.findViewById(R.id.warning_dialog_et_decimal);
        btCancel = dialog.findViewById(R.id.warning_dialog_cancel_bt);
        btOk = dialog.findViewById(R.id.warning_dialog_ok_bt);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(negativeClick != null)
                    negativeClick.onClick(v);
                dialog.dismiss();
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(possitiveClick != null)
                    possitiveClick.onClick(v);
            }
        });
        tvTitle.setVisibility(View.GONE);
        lawWarning.setVisibility(View.GONE);
        tvWarning.setVisibility(View.GONE);
        etWarning.setVisibility(View.GONE);
        etWarningDecimal.setVisibility(View.GONE);
        btCancel.setVisibility(View.GONE);
        btOk.setVisibility(View.GONE);
    }
    public FastDialogBuilder progressDialog(String progressString){
        dialog.setContentView(R.layout.progress_dialog);
        tvProgress = dialog.findViewById(R.id.wait_text);
        tvProgress.setText(progressString);
        return this;
    }
    public FastDialogBuilder setAnimation(Animations animation){
        if (animation == Animations.SLIDE_LEFT){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_slide_left;
        }else if(animation == Animations.SLIDE_RIGHT){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_slide_right;
        }else if(animation == Animations.SLIDE_TOP){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_slide_top;
        }else if(animation == Animations.SLIDE_BOTTOM){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_slide_bottom;
        }else if(animation == Animations.FADE_IN){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_fade_in;
        }else if(animation == Animations.GROW_IN){
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_grow_in;
        }
        return this;
    }
    public FastDialogBuilder setPosition(Positions position){
        if (position == Positions.CENTER){
            dialog.getWindow().setGravity(Gravity.CENTER);
        }else if (position == Positions.LEFT){
            dialog.getWindow().setGravity(Gravity.START);
        }else if (position == Positions.RIGHT){
            dialog.getWindow().setGravity(Gravity.END);
        }else if (position == Positions.TOP){
            dialog.getWindow().setGravity(Gravity.TOP);
        }else if (position == Positions.BOTTOM){
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        return this;
    }
    public FastDialogBuilder setTitle(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        return this;
    }
    public FastDialogBuilder withIcon(){
        lawWarning.setVisibility(View.VISIBLE);
        return this;
    }
    public FastDialogBuilder setText(String text){
        tvWarning.setVisibility(View.VISIBLE);
        tvWarning.setText(text);
        return this;
    }
    public FastDialogBuilder setHint(String hint){
        etWarning.setVisibility(View.VISIBLE);
        etWarning.setHint(hint);
        etWarningDecimal.setHint(hint);
        return this;
    }
    public FastDialogBuilder negativeText(String negative){
        btCancel.setVisibility(View.VISIBLE);
        btCancel.setText(negative);
        return this;
    }
    public FastDialogBuilder possitiveText(String possitive){
        btOk.setVisibility(View.VISIBLE);
        btOk.setText(possitive);
        return this;
    }
    public FastDialogBuilder decimalEditText(boolean bool){
        if(bool){
            etWarning.setVisibility(View.GONE);
            etWarningDecimal.setVisibility(View.VISIBLE);
        }else{
            etWarning.setVisibility(View.VISIBLE);
            etWarningDecimal.setVisibility(View.GONE);
        }
        return this;
    }
    public FastDialogBuilder possitiveClickListener(PossitiveClick click){
        btOk.setVisibility(View.VISIBLE);
        possitiveClick = click;
        return this;
    }
    public  FastDialogBuilder negativeClickListener(NegativeClick click){
        btCancel.setVisibility(View.VISIBLE);
        negativeClick = click;
        return this;
    }
    public FastDialogBuilder cancelable(boolean bool){
        dialog.setCancelable(bool);
        return this;
    }
    public FastDialog create(){
        return new FastDialog(this);
    }
    public Dialog getDialog(){
        return dialog;
    }


}
