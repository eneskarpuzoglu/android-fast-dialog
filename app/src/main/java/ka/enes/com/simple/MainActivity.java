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
        dialog = new FastDialogBuilder(this)
                .progressDialog("Please Wait!").create();
        dialog.show();
    }
    public void warn(View view){
        dialog = new FastDialogBuilder(this)
                .setText("Warning")
                .withIcon()
                .create();
        dialog.show();
    }
    public void text(View view){
        dialog = new FastDialogBuilder(this)
                .setTitle("Warning")
                .setText("Warning Text")
                .changeColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent),
                        ContextCompat.getColor(getApplicationContext(),R.color.colorSecondaryText),
                        ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryText))
                .setHint("please enter text")
                .setAnimation(Animations.GROW_IN)
                .negativeText("Cancel")
                .create();
        dialog.show();
    }
    public void number(View view){
        dialog = new FastDialogBuilder(this)
                .setTitle("Warning")
                .setText("Warning Text")
                .setHint("please enter number")
                .decimalEditText(true)
                .setAnimation(Animations.FADE_IN)
                .possitiveText("Ok")
                .negativeText("Cancel")
                .create();
        dialog.show();
    }
    public void bottomAnim(View view){
        dialog = new FastDialogBuilder(this)
                .setTitle("Warning")
                .setText("Warning Text")
                .possitiveText("Ok")
                .negativeText("Cancel")
                .withIcon()
                .setAnimation(Animations.SLIDE_BOTTOM)
                .setPosition(Positions.BOTTOM)
                .possitiveClickListener(new PossitiveClick() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Ok Pressed",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
    public void topAnim(View view){
        dialog = new FastDialogBuilder(this)
                .setTitle("Warning")
                .setText("Warning Text")
                .possitiveText("Ok")
                .negativeText("Cancel")
                .setHint("please enter your name")
                .withIcon()
                .setAnimation(Animations.SLIDE_TOP)
                .setPosition(Positions.TOP)
                .possitiveClickListener(new PossitiveClick() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Ok Pressed",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
}
