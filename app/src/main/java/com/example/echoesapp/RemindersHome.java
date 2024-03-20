package com.example.echoesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.echoesapp.databinding.FragmentReminderViewHolderBinding;
import com.example.echoesapp.databinding.FragmentRemindersHomeBinding;

import java.util.List;

import javax.annotation.Nullable;

import model.Reminder;
import viewmodel.ReminderViewModel;

public class RemindersHome extends Fragment {

    FragmentRemindersHomeBinding binding;
    private NavController navController;
    private ReminderViewModel reminderViewModel;

    public RemindersHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentRemindersHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reminderViewModel = new ViewModelProvider(requireActivity()).get(ReminderViewModel.class);
        navController = Navigation.findNavController(view);

        ReminderAdapter reminderAdapter = new ReminderAdapter();

        binding.reminderRecycler.setAdapter(reminderAdapter);
        binding.reminderRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        reminderViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                reminderAdapter.establishList(reminders);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Reminder reminder = reminderAdapter.getReminder(position);
                reminderViewModel.delete(reminder);
                Toast.makeText(getContext(), R.string.SuccessReminderDelete, Toast.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(binding.reminderRecycler);
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {

        private final FragmentReminderViewHolderBinding binding;

        public ReminderViewHolder(FragmentReminderViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

        List<Reminder> reminderList;

        @NonNull
        @Override
        public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReminderViewHolder(FragmentReminderViewHolderBinding.inflate(getLayoutInflater(), parent, false));
        }

        public Reminder getReminder(int position) {
            return reminderList.get(position);
        }

        @Override
        public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
             Reminder reminder = reminderList.get(position);

             holder.binding.RemDateHolder.setText(reminder.getTime());
             holder.binding.RemCntHolder.setText(reminder.getText());

             holder.binding.shareRemBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     String shareText = holder.binding.RemDateHolder.getText().toString() + "\n" +
                             "--------------------------------------------------------" + "\n" +
                             holder.binding.RemCntHolder.getText().toString();

                     Intent intent = new Intent();
                     intent.setAction(Intent.ACTION_SEND);
                     intent.putExtra(Intent.EXTRA_TEXT, shareText);
                     intent.setType("text/plain");

                     Intent prt = Intent.createChooser(intent, null);
                     startActivity(prt);
                 }
             });
        }

        @Override
        public int getItemCount() {
            return reminderList != null ? reminderList.size() : 0;
        }

        public void establishList(List<Reminder> trophyList) {
            this.reminderList = trophyList;
            notifyDataSetChanged();
        }

    }
}

