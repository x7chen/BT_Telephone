package com.x7chen.dev.bt_telephone;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumPadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumPadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumPadFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NumPadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumPadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumPadFragment newInstance(String param1, String param2) {
        NumPadFragment fragment = new NumPadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View rootView = inflater.inflate(R.layout.fragment_num_pad, container, false);
        final TextView input_box = (TextView)(rootView.findViewById(R.id.input_box));
        Button bt_back = (Button)(rootView.findViewById(R.id.bt_back));
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(99);
            }
        });
        Button bt_num_0 = (Button)(rootView.findViewById(R.id.bt_num_0));
        bt_num_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(0);
                input_box.append("0");
            }
        });
        Button bt_num_1 = (Button)(rootView.findViewById(R.id.bt_num_1));
        bt_num_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(1);
                input_box.append("1");
            }
        });
        Button bt_num_2 = (Button)(rootView.findViewById(R.id.bt_num_2));
        bt_num_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(2);
                input_box.append("2");
            }
        });
        Button bt_num_3 = (Button)(rootView.findViewById(R.id.bt_num_3));
        bt_num_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(3);
                input_box.append("3");
            }
        });
        Button bt_num_4 = (Button)(rootView.findViewById(R.id.bt_num_4));
        bt_num_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(4);
                input_box.append("4");
            }
        });
        Button bt_num_5 = (Button)(rootView.findViewById(R.id.bt_num_5));
        bt_num_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(5);
                input_box.append("5");
            }
        });
        Button bt_num_6 = (Button)(rootView.findViewById(R.id.bt_num_6));
        bt_num_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(6);
                input_box.append("6");
            }
        });
        Button bt_num_7 = (Button)(rootView.findViewById(R.id.bt_num_7));
        bt_num_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(7);
                input_box.append("7");
            }
        });
        Button bt_num_8 = (Button)(rootView.findViewById(R.id.bt_num_8));
        bt_num_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(8);
                input_box.append("8");
            }
        });
        Button bt_num_9 = (Button)(rootView.findViewById(R.id.bt_num_9));
        bt_num_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(9);
                input_box.append("9");
            }
        });
        Button bt_num_x = (Button)(rootView.findViewById(R.id.bt_num_x));
        bt_num_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(20);
                input_box.append("*");
            }
        });
        Button bt_num_h = (Button)(rootView.findViewById(R.id.bt_num_h));
        bt_num_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBacks.onKeyDown(21);
                input_box.append("#");
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
