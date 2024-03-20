package com.example.echoesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.Inflater;

import com.example.echoesapp.databinding.FragmentHomeBinding;
import com.example.echoesapp.databinding.FragmentPictoMainBinding;

public class PictoMain extends Fragment {

    FragmentPictoMainBinding binding;
    NavController navController;
    Executor executor;

    public PictoMain() {
        // Required empty public constructor
    }

    public static PictoMain newInstance(String param1, String param2) {
        PictoMain fragment = new PictoMain();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        Inflater SignUpFragmentBinding;
        return(binding = FragmentPictoMainBinding.inflate(inflater, container, false)).getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance) {

        super.onViewCreated(view, savedInstance);
        navController = Navigation.findNavController(view);
        executor = Executors.newSingleThreadExecutor();

        binding.imageButtonOcio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_pictoMain_to_picto_recycler_ocio);
            }
        });
        binding.imageButtonComer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_pictoMain_to_picto_recycler_comer);
            }
        });
        binding.imageButtonAseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_pictoMain_to_pictoRecycler);
            }
        });
        binding.imageButtonDolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_pictoMain_to_picto_recycler_dolor);
            }
        });
    }
}