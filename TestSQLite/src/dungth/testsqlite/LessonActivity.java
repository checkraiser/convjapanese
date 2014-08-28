package dungth.testsqlite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import dungth.testsqlite.model.Context;
import dungth.testsqlite.model.Lesson;

public class LessonActivity extends Activity {
	
	Button mButton;
	TextView mQuestion;
	RadioButton mRadioBtn1, mRadioBtn2, mRadioBtn3, mRadioBtn4;
	RadioGroup mGroup;
	String currentQuestion = "", choice1, choice2, choice3, choice4, currentAnswer;
	Lesson currentlesson;
	HashMap<String, String> answers = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lesson);
		mButton = (Button)findViewById(R.id.button1);
		mGroup = (RadioGroup)findViewById(R.id.radioAnswer);
		mRadioBtn1 = (RadioButton)findViewById(R.id.radioButton1);
		mRadioBtn2 = (RadioButton)findViewById(R.id.radioButton2);
		mRadioBtn3 = (RadioButton)findViewById(R.id.radioButton3);
		mRadioBtn4 = (RadioButton)findViewById(R.id.radioButton4);
		mQuestion = (TextView)findViewById(R.id.question);
		currentlesson  = Lesson.findByName(getIntent().getExtras().getString("lesson_name"));
		generateQuestion();
	}
	
	public void onBtnClick(View view)
	{
		int selectedId = mGroup.getCheckedRadioButtonId();
		 
	    RadioButton rButton = (RadioButton) findViewById(selectedId);
	    if (rButton.getText().equals(currentAnswer)) {
	    	final Dialog dialog = new Dialog(this);

	        dialog.setContentView(R.layout.popup_layout);
	        dialog.setTitle("Result");

	        final TextView content = (TextView)dialog.findViewById(R.id.content);
	        content.setText("Correct");
	        Button btnClose = (Button)dialog.findViewById(R.id.close);
	        btnClose.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View arg0) {
	            // TODO Auto-generated method stub
	            	dialog.dismiss();
	            	generateQuestion();
	            }
	       });
	        
	        dialog.show();	    	
	    	
	    } else {
	    	final Dialog dialog = new Dialog(this);

	        dialog.setContentView(R.layout.popup_layout);
	        dialog.setTitle("Result");

	        final TextView content = (TextView)dialog.findViewById(R.id.content);
	        content.setText("Wrong, please check the Guide to answer");
	        Button btnClose = (Button)dialog.findViewById(R.id.close);
	        btnClose.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View arg0) {
	            // TODO Auto-generated method stub
	            	dialog.dismiss();
	            }
	       });
	        
	        dialog.show();
	    }
		
	}
	
	private List<String> generateChoices()
	{
		List<String> original = new ArrayList<String>();
		List<String> meanings = new ArrayList<String>();
		List<Context> ctx = currentlesson.contexts();
		
		for (Context c : ctx) 
		{
			original.add(c.original);
			meanings.add(c.meaning);
			answers.put(c.original, c.meaning);
		}
		Random randomizer = new Random();
		currentQuestion = original.get(randomizer.nextInt(original.size()));
		currentAnswer = answers.get(currentQuestion);
		Collections.shuffle(meanings);
		List<String> tmp = meanings.subList(0, 4);
		if (!tmp.contains(currentAnswer)) {
			tmp.set(0, currentAnswer);
		}
		Collections.shuffle(tmp);
		return tmp;
	}
	
	
	private void generateQuestion()
	{	
		List<String> tmp = generateChoices();
		mRadioBtn1.setText(tmp.get(0));
		mRadioBtn2.setText(tmp.get(1));
		mRadioBtn3.setText(tmp.get(2));
		mRadioBtn4.setText(tmp.get(3));
		mQuestion.setText(currentQuestion);
		
	}
	
	public void onBack(View v)
	{
		Intent intent = new Intent(this, MainActivity.class);		
		startActivity(intent);
	}
	
	public void onGuide(View v)
	{
		final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.popup_layout);
        dialog.setTitle("Guide");

        final TextView content = (TextView)dialog.findViewById(R.id.content);
        content.setText(currentlesson.guide);
        Button btnClose = (Button)dialog.findViewById(R.id.close);
        btnClose.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            // TODO Auto-generated method stub
            	dialog.dismiss();
            }
       });
        
        dialog.show();
	}
	
}
