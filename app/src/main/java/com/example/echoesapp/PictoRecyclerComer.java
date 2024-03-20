package com.example.echoesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echoesapp.databinding.FragmentPictoRecyclerComerBinding;
import com.example.echoesapp.databinding.FragmentPictoViewHolderBinding;

import java.util.List;
import java.util.Locale;

import model.Pictogram;
import viewmodel.PictogramViewModel;

public class PictoRecyclerComer extends Fragment implements TextToSpeech.OnInitListener {

    private FragmentPictoRecyclerComerBinding binding;
    private PictogramViewModel pictogramViewModel;
    private NavController navController;
    private TextToSpeech textToSpeech;
    public PictoRecyclerComer() {
        // Required empty public constructor
    }


    public static PictoRecyclerComer newInstance(String param1, String param2) {
        PictoRecyclerComer fragment = new PictoRecyclerComer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textToSpeech = new TextToSpeech(requireContext(), this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale defaultLocale = Locale.getDefault();
            textToSpeech.setLanguage(defaultLocale);
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentPictoRecyclerComerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pictogramViewModel = new ViewModelProvider(requireActivity()).get(PictogramViewModel.class);
        navController = Navigation.findNavController(view);
        PictoRecyclerComer.PictogramAdapterComer pictogramAdapter = new PictoRecyclerComer.PictogramAdapterComer();
        binding.recyclerViewComer.setAdapter(pictogramAdapter);

        getFilter(1).observe(getViewLifecycleOwner(), new Observer<List<Pictogram>>() {
            @Override
            public void onChanged(List<Pictogram> pictograms) {
                pictogramAdapter.establishList(pictograms);
            }
        });
    }

    LiveData<List<Pictogram>> getFilter(int filter){
        return pictogramViewModel.filter(filter);
    }

    class PictogramAdapterComer extends RecyclerView.Adapter<PictoViewHolder> {
        List<Pictogram> pictograms;

        @NonNull
        @Override
        public PictoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PictoViewHolder(FragmentPictoViewHolderBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PictoViewHolder holder, int position) {
            Pictogram pictogram = pictograms.get(position);
            holder.binding.pictoTitle.setText(pictogram.getDescription());

            Bitmap bitmap= BitmapFactory.decodeResource(getActivity().getResources(), pictogram.getImagePath());
            holder.binding.pictoImage.setImageBitmap(bitmap);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAudio(getString(pictogram.getDescription()));
                }
            });
        }

        private void playAudio(String text) {
            if (textToSpeech != null && !text.isEmpty()) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }

        @Override
        public int getItemCount() {
            return pictograms != null ? pictograms.size() : 0;
        }

        public void establishList(List<Pictogram> pictograms) {
            this.pictograms = pictograms;
            notifyDataSetChanged();
        }
    }
}