package com.example.echoesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echoesapp.databinding.FragmentReminderViewHolderBinding;
import com.example.echoesapp.databinding.FragmentRemindersHomeBinding;

public class ReminderViewHolder extends Fragment {

    public FragmentReminderViewHolderBinding binding;

    public ReminderViewHolder(@NonNull FragmentRemindersHomeBinding inflate) {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder_view_holder, container, false);
    }
}