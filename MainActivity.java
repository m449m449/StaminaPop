package jp.ac.teu.st.m0112449.staminapop;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private static final String pref_key = "setting";
	static TextView textv_name,residue,stamina;
	static TextView	residue2,residue3,residue4,residue5,residue6,residue7,residue8,residue9,residue10;
	static TextView textv_name2,textv_name3,textv_name4,textv_name5,textv_name6,textv_name7,textv_name8,textv_name9,textv_name10;
	static Button	 button1,button2,button3,button4,button5,button6,button7,button8,button9,button10;
	static int flg;
	
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	
	void inital(){
		for(int i = 0;i < 10;i++){
        	PreActivity.key_title[i] = "title" +Integer.toString(i);
        	PreActivity.key_recovery[i] = "recovery" + Integer.toString(i);
        	PreActivity.key_notice[i] = "notice" + Integer.toString(i);
        }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pref = getSharedPreferences(pref_key,Activity.MODE_PRIVATE);
		if(flg == 0){
		inital();
		flg = 1;
		}
		
		textv_name = (TextView)findViewById(R.id.textv_name);
		textv_name2 = (TextView)findViewById(R.id.textv_name2);
		textv_name3 = (TextView)findViewById(R.id.textv_name3);
		textv_name4 = (TextView)findViewById(R.id.textv_name4);
		textv_name5 = (TextView)findViewById(R.id.textv_name5);
		textv_name6 = (TextView)findViewById(R.id.textv_name6);
		textv_name7 = (TextView)findViewById(R.id.textv_name7);
		textv_name8 = (TextView)findViewById(R.id.textv_name8);
		textv_name9 = (TextView)findViewById(R.id.textv_name9);
		textv_name10 = (TextView)findViewById(R.id.textv_name10);
		
		textv_name.setText(pref.getString(PreActivity.key_title[0], "名称未設定"));
		textv_name2.setText(pref.getString(PreActivity.key_title[1], "名称未設定"));
		textv_name3.setText(pref.getString(PreActivity.key_title[2], "名称未設定"));
		textv_name4.setText(pref.getString(PreActivity.key_title[3], "名称未設定"));
		textv_name5.setText(pref.getString(PreActivity.key_title[4], "名称未設定"));
		textv_name6.setText(pref.getString(PreActivity.key_title[5], "名称未設定"));
		textv_name7.setText(pref.getString(PreActivity.key_title[6], "名称未設定"));
		textv_name8.setText(pref.getString(PreActivity.key_title[7], "名称未設定"));
		textv_name9.setText(pref.getString(PreActivity.key_title[8], "名称未設定"));
		textv_name10.setText(pref.getString(PreActivity.key_title[9], "名称未設定"));
		
		residue	= (TextView)findViewById(R.id.residue);
		residue2 = (TextView)findViewById(R.id.residue2);
		residue3 = (TextView)findViewById(R.id.residue3);
		residue4 = (TextView)findViewById(R.id.residue4);
		residue5 = (TextView)findViewById(R.id.residue5);
		residue6 = (TextView)findViewById(R.id.residue6);
		residue7 = (TextView)findViewById(R.id.residue7);
		residue8 = (TextView)findViewById(R.id.residue8);
		residue9 = (TextView)findViewById(R.id.residue9);
		residue10 = (TextView)findViewById(R.id.residue10);
		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button6 = (Button)findViewById(R.id.button6);
		button7 = (Button)findViewById(R.id.button7);
		button8 = (Button)findViewById(R.id.button8);
		button9 = (Button)findViewById(R.id.button9);
		button10 = (Button)findViewById(R.id.button10);
		
		
			button1.setOnClickListener(this);
			button2.setOnClickListener(this);
			button3.setOnClickListener(this);
			button4.setOnClickListener(this);
			button5.setOnClickListener(this);
			button6.setOnClickListener(this);
			button7.setOnClickListener(this);
			button8.setOnClickListener(this);
			button9.setOnClickListener(this);
			button10.setOnClickListener(this);
			
			
	}
	
	public void onClick(View v){
    	if(v.getId()==R.id.button1){
    		PreActivity.Ackey = 0;
    	}else if(v.getId()==R.id.button2){
    		PreActivity.Ackey = 1;
    	}else if(v.getId()==R.id.button3){
    		PreActivity.Ackey = 2;
    	}else if(v.getId()==R.id.button4){
    		PreActivity.Ackey = 3;
    	}else if(v.getId()==R.id.button5){
    		PreActivity.Ackey = 4;
    	}else if(v.getId()==R.id.button6){
    		PreActivity.Ackey = 5;
    	}else if(v.getId()==R.id.button7){
    		PreActivity.Ackey = 6;
    	}else if(v.getId()==R.id.button8){
    		PreActivity.Ackey = 7;
    	}else if(v.getId()==R.id.button9){
    		PreActivity.Ackey = 8;
    	}else if(v.getId()==R.id.button10){
    		PreActivity.Ackey = 9;
    	}
    	
    	Intent intent = new Intent(MainActivity.this,PreActivity.class);
		startActivityForResult(intent,0);
	}
    	
	public void timerset(String timer,int flg){
		
		if(flg == 0){
			residue.setText(timer);
		}else if(flg == 1){
			residue2.setText(timer);
		}else if(flg == 2){
			residue3.setText(timer);
		}else if(flg == 3){
			residue4.setText(timer);
		}else if(flg == 4){
			residue5.setText(timer);
		}else if(flg == 5){
			residue6.setText(timer);
		}else if(flg == 6){
			residue7.setText(timer);
		}else if(flg == 7){
			residue8.setText(timer);
		}else if(flg == 8){
			residue9.setText(timer);
		}else if(flg == 9){
			residue10.setText(timer);
		}
		
	}
	
	
	 
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 //メンテ等するなら配列で組みなおす。
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ScrollView scr_view = (ScrollView)findViewById(R.id.scr_view);
        //配列で作成
        LinearLayout ll = new LinearLayout(this);
        one_line.setOrientation(one_line.VERTICAL);
        Button[] button_list = new Button[10];
        for(int i = 0; i < 10; i++){
        	
            button[i] = new Button(this);
            button[i].setText("設定");
            button[i].setWidth(fill_parent);
            button[i].setHeight(wrap_content);
            //クリックリスナー
            button[i].setOnClickListener(this);
            ll.addView(button[i]);
 
        }
        //スクロールに追加
        scr_view.addView(ll);
    }
	 */

}



