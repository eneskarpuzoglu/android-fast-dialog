# android-fast-dialog
Android Fast Dialog 

Provides you easily create various pop-up dialogs that you can use.

[![](https://jitpack.io/v/eneskarpuzoglu/android-fast-dialog.svg)](https://jitpack.io/#eneskarpuzoglu/android-fast-dialog)

## Installation

### Step 1. Add the JitPack repository to your build file

- **Add it in your root build.gradle at the end of repositories:**

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. Add the dependency

```gradle
	dependencies {
	    implementation 'com.github.eneskarpuzoglu:android-fast-dialog:($LastVersion)'
	}
```

## How to use

> Create `FastDialog` use to `FastDialogBuilder`
```java
	FastDialog dialog = new FastDialogBuilder(Context contex,Type type)
```
> Dialog Types
-  INFO
-  ERROR
-  WARNING
-  DIALOG
-  PROGRESS
-  LOGIN
-  FOLDER
-  NUMBER_PICKER
> Gravity Types
-  CENTER
-  START
-  END
	
> FastDialogBuilder Functions
```java
	 progressText(String progressString) // set progress dialog text
	 setTitleText(String title) // set fast-dialog title
	 hideTitle() //hide title
	 hideIcon() // hide to danger gif on fast-dialog
	 setText(String text) // set fast-dialog TextView
	 setTextGravity(GravityType type) // set fast-dialog warn text gravity 
	 setHint(String hint) // set fast-dialog EditText's hint
	 negativeText(String negative) // set negative button on fast-dialog and set button text
	 positiveText(String positive) // set positive button on fast-dialog and set button text
	 cancelable(boolean bool) // set cancelable to fast-dialog
	 decimalEditText() // set EditText to decimalEditText
	 numberEditText() // set EditText to numberEditText
	 numberUsername() // set Username edittext input type to number
	 numberPassword() // set Password edittext input type to number
	 setTextMaxLength(int length) // set EditText max length
	 setMinValue(int min) // set number picker minimum value
	 setMaxValue(int max) // set number picker maximum value
	 setDefaultValue(int value) // set number picker default value
	 setWrapSelectorWheel(boolean bool) // set number picker selector's wheel
	 setFullScreen(boolean bool) // set fullscreen to dialog window. default true
	 changeColor(int colorButtonsAndTitle,int colorButtonsAndTitleText,int colorPrimaryText) // change fast-dialog colors
     setInputText(String str) // set EditText input
	 privateEditText() // set the edittext type to password
		//Animations
	 setAnimation(Animations animation)
		// Animations.SLIDE_LEFT
		// Animations.SLIDE_RIGHT
		// Animations.SLIDE_TOP
		// Animations.SLIDE_BOTTOM
		// Animations.FADE_IN
		// Animations.GROW_IN
		
		//Positions
	setPosition(Positions position)
		// Positions.CENTER
		// Positions.LEFT
		// Positions.RIGHT
		// Positions.TOP
		// Positions.BOTTOM
	 create() // create fast-dialog
```

> FastDialog Functions

```java
	i(Context contex) // create information dialog
	e(Context contex) // create error dialog
	w(Context contex) // create warning dialog
	d(Context contex) // create normal dialog
	p(Context contex) // create progress dialog
	l(Context contex) // create login dialog
	f(Context contex) // create button folder dialog
	n(Context contex) // create number picker dialog
	positiveClickListener(PositiveClick click) // set listener to positive button
	negativeClickListener(NegativeClick click) // set listener to negative button	
	dismissListener(DismissListener dismissListener) // set dismiss listener to fast-dialog
	isShowing() // return is fast-dialog showing
	show() //show dialog
	dismiss() //dismiss dialog
	getInputText() // get dialog EditText's text (String)
	getNumberValue() // get number picker dialog's value (int)
	getUsernameOrEmail() //get Username or Email from login dialog (String)
	getPassword() // get Password from login dialog (String)
	setProgressText(String str) // set Progress dialog text
	 
```
## Examples
> Simple Progress Dialog
```java
	 FastDialog.p(this).progressText("Please Wait!").create().show();
```
<img width="300px" src="images/progress.gif" align="center"/>

>  Simple Error Dialog
```java
	FastDialog.e(this)
		.setText("Error Dialog")
		.hideTitle()
		.create()
		.show();
```
<img width="300px" src="images/error.gif" align="center"/>

>  Login Dialog
```java
	FastDialog dialog = new FastDialogBuilder(this,Type.LOGIN)
		//.loginWithEmail()
		.setTitleText("Login")
		.create();
	dialog.positiveClickListener(new PositiveClick() {
		@Override
		public void onClick(View view) {
			String string = dialog.getUsernameOrEmail() +" - "+dialog.getPassword();
			Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
		}
	});
	dialog.show();
```
<img width="300px" src="images/login.gif" align="center"/>

>  Warning Dialog position center, grown in animation with EditText
```java
	FastDialog.w(this)
		.setTitleText("Warning")
		.setText("Warning Text")
		.setHint("please enter text")
		.privateEditText()
		.setAnimation(Animations.GROW_IN)
		.positiveText("Accept")
		.create()
		.show();
```
<img width="300px" src="images/warning.gif" align="center"/>

>  Normal Dialog position center, fade in animation with DecimalEditText
```java
	FastDialog.d(this)
		.setTitleText("Dialog")
		.setText("Dialog Text")
		.setHint("please enter number")
		//.decimalEditText()
		.numberEditText()
		.setAnimation(Animations.FADE_IN)
		.positiveText("Ok")
		.negativeText("Cancel")
		.setInputText("55")
		.setTextMaxLenght(16)
		.cancelable(false)
		.create()
		.show();
```
<img width="300px" src="images/input_number.gif" align="center"/>

>  Number Picker Dialog position center, slide top animation
```java
	FastDialog dialog = new FastDialogBuilder(this,Type.NUMBER_PICKER)
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
```
<img width="300px" src="images/number_picker.gif" align="center"/>

>  Information Dialog position bottom, slide bottom animation and listener positive button and dismiss listener
```java
	FastDialog dialog = new FastDialogBuilder(this,Type.INFO)
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
			FastDialog.i(MainActivity.this).setText("Closed").hideTitle().create().show();
		}
	});
	dialog.show();
```
<img width="300px" src="images/bottom_dialog.gif" align="center"/>

>  Warning Colored Dialog position top, slide top animation and listener positive button
```java
	FastDialog dialog= new FastDialogBuilder(this,Type.DIALOG)
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
```
<img width="300px" src="images/top_dialog.gif" align="center"/>

>  Button Folder Dialog position bottom, grow in animation and listener click buttons
```java
	FolderButton button = new FolderButton("1", "one", 0, R.drawable.other_white);
	FolderButton button2 = new FolderButton("2", "two", 1, R.drawable.other_white2);
	List<FolderButton> buttons = new ArrayList<>();
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
```
<img width="300px" src="images/folder.gif" align="center"/>

## License

- Copyright 2018 © Enes Karpuzoğlu.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.




