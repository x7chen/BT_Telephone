package com.x7chen.dev.bt_telephone;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CallingFragment.OnFragmentInteractionListener,
        OnlineFragment.OnFragmentInteractionListener,
        NumPadFragment.OnFragmentInteractionListener {

    CallingFragment callingFragment;
    OnlineFragment onlineFragment;
    FragmentManager fragmentManager;
    NumPadFragment numPadFragment;
    CallingFragment.CallBacks callingCallBacks;
    OnlineFragment.CallBacks onLineCallBacks;
    NumPadFragment.CallBacks numPadCallBacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FMActivity.class);
                startActivity(intent);

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (numPadFragment == null) {
            fragmentManager = getFragmentManager();
            callingFragment = CallingFragment.newInstance("0", "0");
            onlineFragment = OnlineFragment.newInstance("0", "0");
            numPadFragment = NumPadFragment.newInstance("0", "0");
            CallBacksInstance();
            callingFragment.registerCallBacks(callingCallBacks);
            onlineFragment.registerCallBacks(onLineCallBacks);
            numPadFragment.registerCallBacks(numPadCallBacks);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    void showTelephonyDialog() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        callingFragment.setContactName("张三");
        callingFragment.show(fragmentTransaction, "calling");
    }

    void dismissDialogs() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(callingFragment);
        fragmentTransaction.remove(onlineFragment);
        fragmentTransaction.remove(numPadFragment);
        fragmentTransaction.commit();
    }


    public void CallBacksInstance() {
        callingCallBacks = new CallingFragment.CallBacks() {
            @Override
            public void onKeyDown(int key) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (key) {
                    case 0:
                        fragmentTransaction.remove(callingFragment);
                        fragmentTransaction.add(onlineFragment, "online");
                        onlineFragment.setContactName(callingFragment.getContactName());
                        fragmentTransaction.commit();

//                    onlineFragment.show(fragmentTransaction,"online");
                        break;
                    case 1:
                        fragmentTransaction.remove(callingFragment);
                        fragmentTransaction.commit();
                        break;
                    default:
                        break;
                }
            }
        };

        onLineCallBacks = new OnlineFragment.CallBacks() {
            @Override
            public void onKeyDown(int key) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (key) {
                    case 0:
                        Toast.makeText(MainActivity.this, "volume Reduce Button is Pressed.", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        fragmentTransaction.remove(onlineFragment);
                        fragmentTransaction.add(numPadFragment, "numpad");
                        fragmentTransaction.commit();
//                    numPadFragment.show(fragmentTransaction,"numpad");
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "volume Rise Button is Pressed.", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Mic Switch Button is Pressed.", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "Play Device Button is Pressed.", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        fragmentTransaction.remove(onlineFragment);
                        fragmentTransaction.commit();
                    default:
                        break;
                }
            }
        };

        numPadCallBacks = new NumPadFragment.CallBacks() {
            @Override
            public void onKeyDown(int key) {
                switch (key) {
                    case 99:
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.remove(numPadFragment);
                        fragmentTransaction.add(onlineFragment, "online");
                        fragmentTransaction.commit();
//                    onlineFragment.show(fragmentTransaction,"online");
                        break;
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 20:
                    case 21:
                        Toast.makeText(MainActivity.this, "Input Number:" + key, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
