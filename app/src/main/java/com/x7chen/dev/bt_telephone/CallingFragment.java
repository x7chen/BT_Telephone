package com.x7chen.dev.bt_telephone;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CallingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CallingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallingFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String ContactName;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CallingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallingFragment newInstance(String param1, String param2) {
        CallingFragment fragment = new CallingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setContactName(String contectName){
        ContactName = contectName;
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
        View rootView = inflater.inflate(R.layout.fragment_calling, container, false);
        TextView contectNameBox = (TextView)(rootView.findViewById(R.id.contectNameBox));
        contectNameBox.setText(ContactName);
        ImageButton pickup_call = (ImageButton)(rootView.findViewById(R.id.pickup_call));
        pickup_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(0);
            }
        });
        ImageButton calling_hangup_call = (ImageButton)(rootView.findViewById(R.id.calling_hangup_call));
        calling_hangup_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(1);
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
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        callBacks.onKeyDown(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private CallBacks callBacks;
    public void registerCallBacks(CallBacks callBacks){
        this.callBacks = callBacks;
    }
    interface CallBacks{
        void onKeyDown(int key);
    }
}
