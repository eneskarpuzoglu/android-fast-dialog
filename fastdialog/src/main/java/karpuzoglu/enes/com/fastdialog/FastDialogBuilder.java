package karpuzoglu.enes.com.fastdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Objects;

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
    private ClearableEditText etWarning;
    private ClearableEditText etWarningDecimal;
    private ClearableEditText etWarningNumber;
    private ClearableEditText etUsername;
    private NumberPicker numberPicker;
    private ClearableEditText etPassword;
    private Button btCancel;
    private Button btOk;
    private PositiveClick positiveClick;
    private NegativeClick negativeClick;
    private DismissListener dismissListener;

    private FolderAdapter folderAdapter;
    private LinearLayout folderLayout;
    private RecyclerView rvFolder;

    private boolean isDecimal = false;
    private boolean isNumber = false;
    private boolean fullScreen = true;
    private Type type;


    public FastDialogBuilder(@NonNull Context context,@NonNull Type dialogType){
        this.context = context;
        this.type = dialogType;
        createDialog(dialogType);
    }
    public FastDialogBuilder setFullScreen(boolean set){
        fullScreen = set;
        return this;
    }
    private FastDialogBuilder createDialog(Type type){
        dialog =new Dialog(context);
        if (type == Type.PROGRESS){
            dialog.setContentView(R.layout.progress_dialog);
            tvProgress = dialog.findViewById(R.id.wait_text);
        }else if(type == Type.LOGIN) {
            dialog.setContentView(R.layout.login_dialog);
            tvTitle = dialog.findViewById(R.id.login_dialog_title);
            etUsername = dialog.findViewById(R.id.login_dialog_username);
            etPassword = dialog.findViewById(R.id.login_dialog_password);
            tvWarning = dialog.findViewById(R.id.login_dialog_text);
            Drawable imgPassword = context.getResources().getDrawable(R.drawable.ic_password_black);
            imgPassword.setBounds(0, 0, 45, 45);
            etPassword.setCompoundDrawables(imgPassword, null, null, null);
            Drawable imgUser = context.getResources().getDrawable(R.drawable.ic_user_black);
            imgUser.setBounds(0, 0, 45, 45);
            etUsername.setCompoundDrawables(imgUser, null, null, null);
            btCancel = dialog.findViewById(R.id.warning_dialog_cancel_bt);
            btOk = dialog.findViewById(R.id.warning_dialog_ok_bt);
            btCancel.setOnClickListener(v -> {
                if (negativeClick != null)
                    negativeClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setOnClickListener(v -> {
                if (positiveClick != null)
                    positiveClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setBackground(getShape());
            btCancel.setBackground(getShape());
        }else if(type == Type.FOLDER){
            dialog.setContentView(R.layout.folder_dialog);
            folderLayout = dialog.findViewById(R.id.folder_layout);
            rvFolder = dialog.findViewById(R.id.folder_recylerview);
            folderAdapter = new FolderAdapter(context,fullScreen);
            rvFolder.setLayoutManager(new GridLayoutManager(context,5));
            rvFolder.setAdapter(folderAdapter);

        }else if (type == Type.NUMBER_PICKER){
            dialog.setContentView(R.layout.number_picker_dialog);
            tvTitle = dialog.findViewById(R.id.dialog_title);
            tvWarning = dialog.findViewById(R.id.picker_text);
            numberPicker = dialog.findViewById(R.id.number_picker);
            btCancel = dialog.findViewById(R.id.warning_dialog_cancel_bt);
            btOk = dialog.findViewById(R.id.warning_dialog_ok_bt);
            btCancel.setOnClickListener(v -> {
                if(negativeClick != null)
                    negativeClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setOnClickListener(v -> {
                if(positiveClick != null)
                    positiveClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setBackground(getShape());
            btCancel.setBackground(getShape());
        } else{
            dialog.setContentView(R.layout.warning_dialog);
            tvTitle = dialog.findViewById(R.id.warning_dialog_title);
            lawWarning = dialog.findViewById(R.id.warning_dialog_animation);
            tvWarning = dialog.findViewById(R.id.warning_dialog_text);
            etWarning = dialog.findViewById(R.id.warning_dialog_et);
            etWarningDecimal = dialog.findViewById(R.id.warning_dialog_et_decimal);
            etWarningNumber = dialog.findViewById(R.id.warning_dialog_et_number);
            btCancel = dialog.findViewById(R.id.warning_dialog_cancel_bt);
            btOk = dialog.findViewById(R.id.warning_dialog_ok_bt);
            btCancel.setOnClickListener(v -> {
                if(negativeClick != null)
                    negativeClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setOnClickListener(v -> {
                if(positiveClick != null)
                    positiveClick.onClick(v);
                else dialog.dismiss();
            });
            btOk.setBackground(getShape());
            btCancel.setBackground(getShape());

            tvTitle.setVisibility(View.VISIBLE);
            lawWarning.setVisibility(View.VISIBLE);
            tvWarning.setVisibility(View.GONE);
            etWarning.setVisibility(View.GONE);
            etWarningDecimal.setVisibility(View.GONE);
            etWarningNumber.setVisibility(View.GONE);
            btCancel.setVisibility(View.GONE);
            btOk.setVisibility(View.GONE);
        }
        return this;
    }

    private GradientDrawable getShape(){
        GradientDrawable shape =  new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        //shape.setCornerRadii(new float[] { 20,20,20,20,20,20,20,20 });
        shape.setCornerRadius(20);
        if (type == Type.INFO){
            tvTitle.setBackgroundColor(ContextCompat.getColor(context,R.color.info));
            tvTitle.setText(context.getResources().getString(R.string.info));
            shape.setColor(ContextCompat.getColor(context,R.color.info));
            shape.setStroke(3, ContextCompat.getColor(context,R.color.info));
        }else if (type == Type.ERROR){
            tvTitle.setBackgroundColor(ContextCompat.getColor(context,R.color.error));
            tvTitle.setText(context.getResources().getString(R.string.error));
            shape.setColor(ContextCompat.getColor(context,R.color.error));
            shape.setStroke(3, ContextCompat.getColor(context,R.color.error));
        }else  if (type == Type.WARNING){
            tvTitle.setBackgroundColor(ContextCompat.getColor(context,R.color.warning));
            tvTitle.setText(context.getResources().getString(R.string.warning));
            shape.setColor(ContextCompat.getColor(context,R.color.warning));
            shape.setStroke(3, ContextCompat.getColor(context,R.color.warning));
        }else{
            tvTitle.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
            tvTitle.setText(context.getResources().getString(R.string.dialog));
            shape.setColor(ContextCompat.getColor(context,R.color.colorPrimary));
            shape.setStroke(3, ContextCompat.getColor(context,R.color.colorPrimary));
       }

        return shape;
    }
    public FastDialogBuilder changeColor(int colorItem,int colorItemText,int colorText){
        if (folderLayout != null) {
            folderLayout.setBackgroundColor(colorItem);
        }else{
            tvTitle.setBackgroundColor(colorItem);
            tvTitle.setTextColor(colorItemText);
            if (type != Type.LOGIN && type != Type.FOLDER){
                tvWarning.setTextColor(colorText);
            }
            if (type == Type.NUMBER_PICKER && numberPicker != null){
                numberPicker.setSelectedTextColor(colorItem);
                numberPicker.setDividerColor(colorItem);
            }
            GradientDrawable shape =  new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            //shape.setCornerRadii(new float[] { 20,20,20,20,20,20,20,20 });
            shape.setCornerRadius(20);
            shape.setColor(colorItem);
            shape.setStroke(3, colorItem);

            btCancel.setBackground(shape);
            btOk.setBackground(shape);
            btCancel.setTextColor(colorItemText);
            btOk.setTextColor(colorItemText);
        }
        return this;
    }
    public FastDialogBuilder setActiveButtons(ArrayList<FolderButton> buttonList){
        folderAdapter.setActiveButtons(buttonList);
        folderAdapter.notifyDataSetChanged();
        return this;
    }
    public FastDialogBuilder onClickListener(CustomItemClickListener listener){
        folderAdapter.setClickListener(listener);
        folderAdapter.notifyDataSetChanged();
        return this;
    }
    public FastDialogBuilder progressText(String progressString){
        tvProgress.setText(progressString);
        return this;
    }
    public FastDialogBuilder setAnimation(Animations animation){
        if (animation == Animations.SLIDE_LEFT){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_slide_left;
        }else if(animation == Animations.SLIDE_RIGHT){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_slide_right;
        }else if(animation == Animations.SLIDE_TOP){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_slide_top;
        }else if(animation == Animations.SLIDE_BOTTOM){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_slide_bottom;
        }else if(animation == Animations.FADE_IN){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_fade_in;
        }else if(animation == Animations.GROW_IN){
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.dialog_grow_in;
        }
        return this;
    }
    public FastDialogBuilder setPosition(Positions position){
        if (position == Positions.CENTER){
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
        }else if (position == Positions.LEFT){
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.START);
        }else if (position == Positions.RIGHT){
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.END);
        }else if (position == Positions.TOP){
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.TOP);
        }else if (position == Positions.BOTTOM){
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.BOTTOM);
        }
        return this;
    }
    public FastDialogBuilder setTitleText(String title){
        tvTitle.setText(title);
        return this;
    }
    public FastDialogBuilder hideTitle(){
        tvTitle.setVisibility(View.GONE);
        return this;
    }
    public FastDialogBuilder hideIcon(){
        lawWarning.setVisibility(View.GONE);
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
        etWarningNumber.setHint(hint);
        return this;
    }
    public FastDialogBuilder loginWithEmail(){
        etUsername.setHint(context.getResources().getString(R.string.email));
        Drawable img = context.getResources().getDrawable( R.drawable.ic_email_black );
        img.setBounds( 0, 0, 45, 45 );
        etUsername.setCompoundDrawables(img,null,null,null);
        return this;
    }
    public FastDialogBuilder numberPassword(){
        etPassword.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        return this;
    }
    public FastDialogBuilder numberUsername(){
        etUsername.setInputType(InputType.TYPE_CLASS_NUMBER);
        return this;
    }
    public FastDialogBuilder negativeText(String negative){
        btCancel.setVisibility(View.VISIBLE);
        btCancel.setText(negative);
        return this;
    }
    public FastDialogBuilder positiveText(String positive){
        btOk.setVisibility(View.VISIBLE);
        btOk.setText(positive);
        return this;
    }
    public FastDialogBuilder decimalEditText(){
        etWarning.setVisibility(View.GONE);
        etWarning.setText("");
        etWarningNumber.setVisibility(View.GONE);
        etWarningNumber.setText("");
        etWarningDecimal.setVisibility(View.VISIBLE);
        isDecimal = true;
        isNumber = false;
        return this;
    }
    public TextView getTvWarning(){
        return tvWarning;
    }
    public FastDialogBuilder numberEditText(){
        etWarning.setVisibility(View.GONE);
        etWarning.setText("");
        etWarningDecimal.setVisibility(View.GONE);
        etWarningDecimal.setText("");
        etWarningNumber.setVisibility(View.VISIBLE);
        isDecimal = false;
        isNumber = true;
        return this;
    }
    void positiveClickListener(PositiveClick click){
        btOk.setVisibility(View.VISIBLE);
        positiveClick = click;
    }
    void negativeClickListener(NegativeClick click){
        btCancel.setVisibility(View.VISIBLE);
        negativeClick = click;
    }
    void setDismissListener(final DismissListener dismissListener){
        dialog.setOnDismissListener(dismissListener::onDismiss);
    }
    public FastDialogBuilder cancelable(boolean bool){
        dialog.setCancelable(bool);
        return this;
    }

    public FastDialogBuilder privateEditText(){
        etWarning.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etWarningDecimal.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etWarningNumber.setTransformationMethod(PasswordTransformationMethod.getInstance());
        return this;
    }
    public FastDialogBuilder setTextGravity(GravityType gravity){
        if (gravity == GravityType.CENTER) {
            tvWarning.setGravity(Gravity.CENTER);
        }else if (gravity == GravityType.START){
            tvWarning.setGravity(Gravity.START);
        }else if (gravity == GravityType.END){
            tvWarning.setGravity(Gravity.END);
        }
        return this;
    }
    public FastDialogBuilder setInputText(String str){
        if (isDecimal){
            etWarningDecimal.setText(str);
            etWarningDecimal.setSelection(etWarningDecimal.getText().length());
        }else if (isNumber){
            etWarningNumber.setText(str);
            etWarningNumber.setSelection(etWarningNumber.getText().length());
        }
        else{
            etWarning.setText(str);
            etWarning.setSelection(etWarning.getText().length());
        }

        return this;
    }

    public FastDialogBuilder setTextMaxLength(int length){
        etWarning.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
        etWarningDecimal.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
        etWarningNumber.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
        return this;
    }

    public FastDialogBuilder setMinValue(int min){
        if (numberPicker != null)
            numberPicker.setMinValue(min);
        return this;
    }

    public FastDialogBuilder setMaxValue(int max){
        if (numberPicker != null)
            numberPicker.setMaxValue(max);
        return this;
    }

    public FastDialogBuilder setDefaultValue(int value){
        if (numberPicker != null)
            numberPicker.setValue(value);
        return this;
    }

    public FastDialogBuilder setWrapSelectorWheel(boolean bool){
        if (numberPicker != null)
            numberPicker.setWrapSelectorWheel(bool);
        return this;
    }


    public FastDialog create(){
        if (fullScreen){
            WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
            lWindowParams.copyFrom(Objects.requireNonNull(getDialog().getWindow()).getAttributes());
            lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(dialog.getWindow()).setAttributes(lWindowParams);
        }
        return new FastDialog(this);
    }
    Dialog getDialog(){
        return dialog;
    }

    Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
