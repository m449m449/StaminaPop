package jp.ac.teu.st.m0112449.staminapop;

import android.os.Bundle;
import android.os.PowerManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;



public class PreActivity extends Activity implements OnClickListener{
	private static final String pref_key = "setting";
	static final String[] key_title = new String[10];
	static final String[] key_recovery = new String[10];
	static final String[] key_notice = new String[10];
	static final int[] cdflg = new int[10];
	static final int[] cdkey = new int[10];
	static final int[] rkey = new int[10];
	String title,recovery,notice;
	String vtitle,vrecovery,vnotice;
	String timer = "�c��F0:00";
	String totitle;
	EditText edit_title,edit_recovery,edit_notice;
	static TextView view_title,view_recovery,view_notice,vtimer;
	CheckBox sound,vibrate,pop_up;
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	Button count_start,count_cancel;
	long reco,noti,full;
	int sflg,pflg;
	
	static int Ackey;
	
	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        
       
       pref = getSharedPreferences(pref_key,Activity.MODE_PRIVATE);
       edit_title = (EditText)findViewById(R.id.edit_title);
       edit_recovery = (EditText)findViewById(R.id.edit_recovery);
       edit_notice = (EditText)findViewById(R.id.edit_notice);
       
        
        count_start = (Button)findViewById(R.id.count_start);
        count_start.setOnClickListener(this);
        if(cdflg[Ackey] == 1){
        	count_start.setText("  �J�E���g��  ");
        }
        
        count_cancel = (Button)findViewById(R.id.count_cancel);
        count_cancel.setOnClickListener(this);
        
        
        view_title = (TextView)findViewById(R.id.view_title);
        view_recovery = (TextView)findViewById(R.id.view_recovery);
        view_notice = (TextView)findViewById(R.id.view_notice);
        vtimer = (TextView)findViewById(R.id.vtimer);
        
        sound = (CheckBox)findViewById(R.id.sound);
        sound.setOnClickListener(this);
        vibrate = (CheckBox)findViewById(R.id.vibrate);
        vibrate.setOnClickListener(this);
        pop_up = (CheckBox)findViewById(R.id.pop_up);
        pop_up.setOnClickListener(this);
        
        
        
        view_title.setText(pref.getString(key_title[Ackey], "���̖��ݒ�"));
        view_recovery.setText(pref.getString(key_recovery[Ackey], "������"));
        view_notice.setText(pref.getString(key_notice[Ackey], "������"));
        vtitle = view_title.getText().toString();
        vrecovery = view_recovery.getText().toString();
        vnotice = view_notice.getText().toString();
        
    }
    	
        public void onClick(View v){
        	if(v.getId()==R.id.sound){
        		if(sound.isChecked() == true){
        			sflg += 10;
        		}else{
        			sflg -= 10;
        		}
        	}else if(v.getId() == R.id.vibrate){
        		if(vibrate.isChecked() == true){
        			sflg++;
        		}else{
        			sflg--;
        		}
        			
        	}else if(v.getId() == R.id.pop_up){
        		if(pflg == 0){
        			pflg = 1;
        		}else{
        			pflg = 0;
        		}
        	}else{
        		//�G�f�B�^�[�̍X�V
        		title = edit_title.getText().toString();
        		recovery = edit_recovery.getText().toString();
        		notice = edit_notice.getText().toString();
        		//���̂��V�K���͂��ꂽ���ǂ���
        		if(title.equals("")){
        			totitle = vtitle;
        		}else{
        			totitle = title;
        			editor = pref.edit();
        			editor.putString(key_title[Ackey], title);
        			editor.commit();
        		}
        		if(recovery.equals("")){
        			if(vrecovery.equals("������")){
        				reco = 0;
        			}else{
        				reco = Long.parseLong(vrecovery);
        			}
        			
        		}else{
        			reco = Long.parseLong(recovery);
        			editor = pref.edit();
        			editor.putString(key_recovery[Ackey], recovery);
        			editor.commit();
        		}
        		if(notice.equals("")){
        			if(vnotice.equals("������")){
        				noti = 0;
        			}else{
        				noti = Long.parseLong(vnotice);
        			}
        		}else{
        			noti = Long.parseLong(notice);
        			editor = pref.edit();
        			editor.putString(key_notice[Ackey], notice);
        			editor.commit();
        		}
        		
        		full = reco * noti * 1000;
        	//�J�E���g���ɐV�����I�u�W�F�N�g�����Ȃ��悤�Ƀt���O�ŊǗ�
        	if(cdflg[Ackey] == 0){	
        		MyCountDownTimer cdt = new MyCountDownTimer(full,1000);	
        		if(v.getId()==R.id.count_start){	
        			cdflg[Ackey] = 1;
        			cdt.start();
        			count_start.setText("  �J�E���g��  ");
        			setview(Ackey);
        		}else if(v.getId()==R.id.count_cancel){
        		}
        	}else if(v.getId()==R.id.count_cancel){
        		vtimer.setText("�c��:00:00:00");
        		count_start.setText("�J�E���g�J�n");
        		MainActivity ma = new MainActivity();
        		ma.timerset("�c��:00:00:00",Ackey);
        		cdflg[Ackey]  = 0;
    			cdkey[Ackey]++;
    		}
        	
        		
        		
        	}
    			
		}
        
        void setview(int flg){
        	switch(flg){
        	case 0: MainActivity.textv_name.setText(totitle);break;
        	case 1: MainActivity.textv_name2.setText(totitle);break;
        	case 2: MainActivity.textv_name3.setText(totitle);break;
        	case 3: MainActivity.textv_name4.setText(totitle);break;
        	case 4: MainActivity.textv_name5.setText(totitle);break;
        	case 5: MainActivity.textv_name6.setText(totitle);break;
        	case 6: MainActivity.textv_name7.setText(totitle);break;
        	case 7: MainActivity.textv_name8.setText(totitle);break;
        	case 8: MainActivity.textv_name9.setText(totitle);break;
        	case 9: MainActivity.textv_name10.setText(totitle);break;
        	default: break;
        	
        	}
        }
        
        
        
        
        public class MyCountDownTimer extends CountDownTimer{
        	//�^�C�}�[�ԍ�
       	 	int flg = Ackey;
        	//�����^�C�}�[�ԍ��ł̃I�u�W�F�N�g�̎��ʔԍ�
       	 	int key = cdkey[Ackey];
       	 	
       	 	
            public MyCountDownTimer(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
          
            }
            
            MainActivity ma = new MainActivity();
            @Override
            public void onFinish() {
            	
                // �J�E���g�_�E��������ɌĂ΂��
            	//�ŐV�̃I�u�W�F�N�g������
            	if(key == cdkey[flg]){
            		//�ŐV�Ȃ�J�E���g�J�n�{�^����������悤�ɂȂ�
            		cdflg[flg] = 0;
            		timer = "�c��:00:00:00";
            		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE); 
            		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "StaminaPop");
            		wl.acquire();
            		if(pflg == 1){
            			Toast.makeText(getApplicationContext(),totitle +  "�̃X�^�~�i���񕜂��܂���", Toast.LENGTH_LONG).show();
            		}
                    sendNotification();               
                    ma.timerset(timer,flg);
                    instantimer(timer,flg);
                    count_start.setText("�J�E���g�J�n");
                    wl.release();
            	}else{
            		//�Â��I�u�W�F�N�g�Ȃ�ŐV�̎��ʔԍ���������
            		cdkey[flg]--;
            		rkey[flg] = 1;
            	}
                
            }
          
            @Override
            public void onTick(long millisUntilFinished) {
                // �C���^�[�o��(countDownInterval)���ɌĂ΂��
            	//�Â��I�u�W�F�N�g�i�L�����Z�����ꂽ�^�C�}�[�j���I������环�ʔԍ����������
            	if(rkey[flg] == 1){
            		if(key == cdkey[flg] + 1){
            			key--;
            			rkey[flg] = 0;
            		}
            	}
            	String h,m,s;
            	h = Long.toString(millisUntilFinished/1000/3600);
            	m = Long.toString(millisUntilFinished/1000%3600/60);
            	s = Long.toString(millisUntilFinished/1000%60);
            	if(millisUntilFinished/1000/3600 < 10){
            		h = "0" + Long.toString(millisUntilFinished/1000/3600);
            	}
            	if(millisUntilFinished/1000%3600/60 < 10){
            		m = "0" + Long.toString(millisUntilFinished/1000%3600/60);
            	}
            	if(millisUntilFinished/1000%60 < 10){
            		s = "0" + Long.toString(millisUntilFinished/1000%60);
            	}
            	if(key == cdkey[flg]){
                timer = "�c��:" + h + ":" + m + ":" + s;
                ma.timerset(timer,flg);
                instantimer(timer,flg);
            	}
                
            }
        }
        
        void instantimer(String time,int flg){
        	if(flg == Ackey){
        		vtimer.setText(time);
        	}
        }
        
        private void sendNotification() {
            // Intent �̍쐬
            Intent intent = new Intent(PreActivity.this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(
                    PreActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
             
            // LargeIcon �� Bitmap �𐶐�
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
             
            // NotificationBuilder���쐬
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    getApplicationContext());
            builder.setContentIntent(contentIntent);
            // �X�e�[�^�X�o�[�ɕ\�������e�L�X�g
            builder.setTicker(totitle + "�̃X�^�~�i���񕜂��܂���");
            // �A�C�R��
            builder.setSmallIcon(R.drawable.ic_launcher);
            // Notification���J�����Ƃ��ɕ\�������^�C�g��
            builder.setContentTitle("StaminaPop");
            // Notification���J�����Ƃ��ɕ\�������T�u�^�C�g��
            builder.setContentText(totitle + "�̃X�^�~�i���񕜂��܂���");
            // Notification���J�����Ƃ��ɕ\�������A�C�R��
            builder.setLargeIcon(largeIcon);
            //���C�g
            builder.setLights(0xFF00FF7F, 500, 500);
            // �ʒm����^�C�~���O
            builder.setWhen(System.currentTimeMillis());
            // �ʒm���̉��E�o�C�u�E���C�g
            System.out.println(sflg);
            if(sflg == 11){
            	builder.setDefaults(Notification.DEFAULT_SOUND
            			|Notification.DEFAULT_VIBRATE);
            }else if(sflg == 10){
            	builder.setDefaults(Notification.DEFAULT_SOUND);
            }else if(sflg == 1){
            	builder.setDefaults(Notification.DEFAULT_VIBRATE);
            }
            // Notification.DEFAULT_LIGHTS
            // �^�b�v����ƃL�����Z��(������)
            builder.setAutoCancel(true); 
            // NotificationManager���擾
            NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
            // Notification���쐬���Ēʒm
            manager.notify(1, builder.build());
        }
}