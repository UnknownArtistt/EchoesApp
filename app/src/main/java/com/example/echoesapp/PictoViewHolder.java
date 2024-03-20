package com.example.echoesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echoesapp.databinding.FragmentPictoViewHolderBinding;

class PictoViewHolder extends RecyclerView.ViewHolder {
    final FragmentPictoViewHolderBinding binding;

    public PictoViewHolder(FragmentPictoViewHolderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}