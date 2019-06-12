package ka.enes.com.sample;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import karpuzoglu.enes.com.fastdialog.Animations;
import karpuzoglu.enes.com.fastdialog.FastDialog;
import karpuzoglu.enes.com.fastdialog.FastDialogBuilder;
import karpuzoglu.enes.com.fastdialog.FolderButton;
import karpuzoglu.enes.com.fastdialog.Positions;
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
                .setFullScreen(false)
                .create()
                .show();
    }
    public void login(View view){
        dialog = new FastDialogBuilder(this,Type.LOGIN)
                //.loginWithEmail()
                .setTitleText("Login")
                .changeColor(ContextCompat.getColor(getApplicationContext(),R.color.different),
                        ContextCompat.getColor(getApplicationContext(),R.color.text2),
                        ContextCompat.getColor(getApplicationContext(),R.color.text))
                .create();
        dialog.positiveClickListener(view1 -> {
            String string = dialog.getUsernameOrEmail() +" - "+dialog.getPassword();
            Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    }
    public void text(View view){
        FastDialog.w(this)
                .setTitleText("Warning")
                .setText("Warning Text")
                .setHint("please enter text")
                .privateEditText()
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
                .setTextMaxLength(16)
                .cancelable(false)
                .create()
                .show();
    }
    public void picker(View view){
        dialog = new FastDialogBuilder(this,Type.NUMBER_PICKER)
                .setText("Choice Number")
                .setAnimation(Animations.SLIDE_TOP)
                .changeColor(ContextCompat.getColor(getApplicationContext(),R.color.different),
                        ContextCompat.getColor(getApplicationContext(),R.color.text2),
                        ContextCompat.getColor(getApplicationContext(),R.color.text))
                .positiveText("Ok")
                .negativeText("Cancel")
                .setMaxValue(15)
                .setMinValue(1)
                .setDefaultValue(5)
                .setWrapSelectorWheel(false)
                .cancelable(false)
                .create();
        dialog.positiveClickListener(view1 -> {
            Toast.makeText(MainActivity.this,dialog.getNumberValue()+"",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        dialog.show();
    }
    public void bottomAnim(View view){
        dialog = new FastDialogBuilder(this,Type.INFO)
                .setTitleText("Information")
                .setText("Information Text")
                .positiveText("Ok")
                .setAnimation(Animations.SLIDE_BOTTOM)
                .setPosition(Positions.BOTTOM)
                .create();
        dialog.positiveClickListener(view1 -> {
            Toast.makeText(MainActivity.this,"Ok Pressed",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        dialog.dismissListener(dialog -> FastDialog.i(MainActivity.this).setText("Closed").setFullScreen(false).create().show());
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
        dialog.positiveClickListener(view1 -> {
            dialog.dismiss();
            Toast.makeText(MainActivity.this,dialog.getInputText().equals("")?"EditText is Empty":dialog.getInputText(),Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    }
    public void buttonFolder(View view){
        FolderButton button = new FolderButton("1", "one", 0, R.drawable.other_white);
        FolderButton button2 = new FolderButton("2", "two", 1, R.drawable.other_white2);
        ArrayList<FolderButton> buttons = new ArrayList<>();
        buttons.add(button);
        buttons.add(button2);
        FastDialog.f(this)
                .setActiveButtons(buttons)
                .onClickListener((v, position) -> {
                    Toast.makeText(MainActivity.this,v.getTag()+"",Toast.LENGTH_SHORT).show();
                })
                .setAnimation(Animations.GROW_IN)
                .setPosition(Positions.BOTTOM)
                .create()
                .show();
    }
}
