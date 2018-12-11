package ka.enes.com.simple;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import karpuzoglu.enes.com.fastdialog.Animations;
import karpuzoglu.enes.com.fastdialog.FastDialog;
import karpuzoglu.enes.com.fastdialog.FastDialogBuilder;
import karpuzoglu.enes.com.fastdialog.Positions;
import karpuzoglu.enes.com.fastdialog.PossitiveClick;


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
    public void warn(View view){
        FastDialog.e(this)
                .setText("Error Dialog")
                .hideTitle()
                .create()
                .show();
    }
    public void text(View view){
        FastDialog.w(this)
                .setTitleText("Warning")
                .setText("Warning Text")
                .changeColor(ContextCompat.getColor(getApplicationContext(),R.color.warning),
                        ContextCompat.getColor(getApplicationContext(),R.color.text2),
                        ContextCompat.getColor(getApplicationContext(),R.color.text))
                .setHint("please enter text")
                .setAnimation(Animations.GROW_IN)
                .possitiveText("Accept")
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
                .possitiveText("Ok")
                .negativeText("Cancel")
                .create()
                .show();
    }
    public void bottomAnim(View view){
        dialog.i(this)
                .setTitleText("Information")
                .setText("Information Text")
                .possitiveText("Ok")
                .negativeText("Cancel")
                .setAnimation(Animations.SLIDE_BOTTOM)
                .setPosition(Positions.BOTTOM)
                .possitiveClickListener(new PossitiveClick() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Ok Pressed",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
    public void topAnim(View view){
        dialog.w(this)
                .setTitleText("Warning")
                .setText("Warning Text")
                .possitiveText("Ok")
                .negativeText("Cancel")
                .setHint("please enter your name")
                .setAnimation(Animations.SLIDE_TOP)
                .setPosition(Positions.TOP)
                .possitiveClickListener(new PossitiveClick() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,dialog.getInputText().equals("")?"EditText is Empty":dialog.getInputText(),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
