package ru.vealex.mymessenger;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.content.Intent;

import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    //Вызвать onSendMessage при щелчке по кнопке
    public void onSendMessage(View view) {
        EditText messageView = (EditText) findViewById(R.id.message);
        String messageText = messageView.getText().toString();
        /*Intent intent = new Intent(this, ReceiveMessageActivity.class);
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, messageText);*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        String intentTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, intentTitle);
//        startActivity(intent);
        startActivity(chosenIntent);
    }
}