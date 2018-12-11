package ka.enes.com.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import karpuzoglu.enes.com.fastdialog.Animations;
import karpuzoglu.enes.com.fastdialog.DismissListener;
import karpuzoglu.enes.com.fastdialog.FastDialog;
import karpuzoglu.enes.com.fastdialog.FastDialogBuilder;
import karpuzoglu.enes.com.fastdialog.Positions;
import karpuzoglu.enes.com.fastdialog.PositiveClick;
import karpuzoglu.enes.com.fastdialog.Type;


public class MainActivity extends AppCompatActivity {
    FastDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void progress(View view){
        FastDialog.p(this).progressText("Please Wait!").create().show();
    }
    public void err(View view){
        FastDialog.e(this)
                .setText("Error Dialog")
                .hideTitle()
                .create()
                .show();
    }
    public void error2(View view){
        FastDialog.e(this)
                .setText("Error Message")
                .hideIcon()
                .positiveText("OK")
                .create()
                .show();
    }
    public void text(View view){
        FastDialog.w(this)
                .setTitleText("Warning")
                .setText("Warning Text")
                .setHint("please enter text")
                .setAnimation(Animations.GROW_IN)
                .positiveText("Accept")
                .create()
                .show();
    }
    public void number(View view){
        FastDialog.d(this)
                .setTitleText("Dialog")
                .setText("Dialog Text")
                .setHint("please enter number")
                .decimalEditText()
                .setAnimation(Animations.FADE_IN)
                .positiveText("Ok")
                .negativeText("Cancel")
                .setInputText("55")
                .cancelable(false)
                .create()
                .show();
    }
    public void bottomAnim(View view){
        dialog = new FastDialogBuilder(this,Type.INFO)
                .setTitleText("Information")
                .setText("Information Text")
                .positiveText("Ok")
                .setAnimation(Animations.SLIDE_BOTTOM)
                .setPosition(Positions.BOTTOM)
                .create();
        dialog.positiveClickListener(new PositiveClick() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Ok Pressed",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.dismissListener(new DismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                FastDialog.i(MainActivity.this).setText("Closed").create().show();
            }
        });
        dialog.show();
    }
    public void topAnim(View view){
        dialog= new FastDialogBuilder(this,Type.DIALOG)
                .setTitleText("Warning")
                .setText("Warning Text")
                .positiveText("Ok")
                .negativeText("Cancel")
                .changeColor(ContextCompat.getColor(getApplicationContext(),R.color.different),
                        ContextCompat.getColor(getApplicationContext(),R.color.text2),
                        ContextCompat.getColor(getApplicationContext(),R.color.text))
                .setHint("please enter your name")
                .setAnimation(Animations.SLIDE_TOP)
                .setPosition(Positions.TOP)
                .create();
        dialog.positiveClickListener(new PositiveClick() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,dialog.getInputText().equals("")?"EditText is Empty":dialog.getInputText(),Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
