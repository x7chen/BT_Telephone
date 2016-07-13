package com.x7chen.dev.bt_telephone;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnlineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnlineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnlineFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String ContactName;

    private OnFragmentInteractionListener mListener;

    public OnlineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnlineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnlineFragment newInstance(String param1, String param2) {
        OnlineFragment fragment = new OnlineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setContactName(String contactName){
        ContactName = contactName;
    }
    public String getContactName(){
        return ContactName;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setStyle(0,R.style.add_dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_online, container, false);

        TextView contectNameBox = (TextView)(rootView.findViewById(R.id.contectNameBox2));
        contectNameBox.setText(ContactName);

        ImageButton volume_reduce = (ImageButton)(rootView.findViewById(R.id.volume_reduce));
        volume_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(0);
            }
        });
        ImageButton tel_num_pad = (ImageButton)(rootView.findViewById(R.id.tel_num_pad));
        tel_num_pad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(1);
            }
        });
        ImageButton volume_rise = (ImageButton)(rootView.findViewById(R.id.volume_rise));
        volume_rise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(2);
            }
        });
        ImageButton mic_switch = (ImageButton)(rootView.findViewById(R.id.mic_switch));
        mic_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(3);
            }
        });
        ImageButton play_device = (ImageButton)(rootView.findViewById(R.id.play_device));
        play_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(4);
            }
        });
        ImageButton online_hangup_call = (ImageButton)(rootView.findViewById(R.id.online_hangup_call));
        online_hangup_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(5);
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if(callBacks == null){
            throw new RuntimeException(getClass().getName()+":CallBack interface is not implement");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    CallBacks callBacks;
    public void registerCallBacks(CallBacks callBacks){
        this.callBacks = callBacks;
    }
    interface CallBacks{
        void onKeyDown(int key);
    }
}
