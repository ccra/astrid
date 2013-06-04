/**
 * Creates a window whenever a Task is due
 * @author christi
 */
package com.todoroo.astrid.reminders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.timsu.astrid.R;
import com.todoroo.andlib.service.ContextManager;
import com.todoroo.andlib.utility.AndroidUtilities;
import com.todoroo.astrid.activity.ShortcutActivity;


public class ReminderPopupActivity extends Activity {
    /**
     * Listener to close the window
     * Implements OnClickListener
     *@see android.view.View.OnClickListener
     *
     */
    private final OnClickListener dismissListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            AndroidUtilities.callOverridePendingTransition(ReminderPopupActivity.this, 0, android.R.anim.fade_out);
        }
    };

    /**
     * Listener to open Astrid application
     * Implements OnClickListener
     * @see android.view.View.OnClickListener
     */
    private final OnClickListener taskOpenListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Context context=ContextManager.getContext();

            Intent intent = new Intent(context, ShortcutActivity.class);
            context.startActivity(intent);
        }
    };

    private TextView goToTaskButton;
    private TextView closeButton;
    private View dismissButton;

    private String taskTitle;
    @Override
    /**
     * Creates the popup window
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminder_popup);

        Intent intent=getIntent();
        taskTitle=intent.getStringExtra(Notifications.EXTRAS_TITLE);

        goToTaskButton=(TextView) findViewById(R.id.goto_task);
        closeButton=(TextView) findViewById(R.id.close_window);
        dismissButton=findViewById(R.id.dismiss_RPA);

        ((TextView) findViewById(R.id.RPA_title)).setText(getString(R.string.RPA_title, taskTitle));
        addListeners();



    }

    /**
     * Bind listeners to the buttons
     */
    private void addListeners()
    {
        dismissButton.setOnClickListener(dismissListener);
        closeButton.setOnClickListener(dismissListener);
        goToTaskButton.setOnClickListener(taskOpenListener);
    }

    @Override
    /**
     * Creates the window menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.reminder_popup, menu);
        return true;
    }

}
