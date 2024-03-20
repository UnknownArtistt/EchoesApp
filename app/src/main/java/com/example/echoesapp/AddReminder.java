package com.example.echoesapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.echoesapp.databinding.FragmentAddReminderBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import model.Reminder;
import viewmodel.ReminderViewModel;

public class AddReminder extends Fragment {

    FragmentAddReminderBinding binding;
    NavController navController;
    ReminderViewModel reminderViewModel;
    Executor executor;

    String reminderContent;
    String reminderDate;

    public AddReminder() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentAddReminderBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        reminderViewModel = new ViewModelProvider(requireActivity()).get(ReminderViewModel.class);
        executor = Executors.newSingleThreadExecutor();

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    reminderContent = binding.remText.getText().toString();

                    if (reminderContent.equals("") || reminderDate.equals("")){
                        Toast t = Toast.makeText(getContext(), R.string.FillFields, Toast.LENGTH_LONG);
                        t.show();
                    } else{
                        String username = MainActivity.getUsername();
                        Reminder reminder = new Reminder(reminderContent, reminderDate, username);
                        reminderViewModel.add(reminder);

                        navController.navigate(R.id.action_add_reminder_to_home2);
                        Toast t = Toast.makeText(getContext(), R.string.SuccessReminderCreation, Toast.LENGTH_LONG);
                        t.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast t = Toast.makeText(getContext(), R.string.FillFields, Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_add_reminder_to_home2);
            }
        });

        binding.remDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(Calendar.YEAR, year);
                        selectedCalendar.set(Calendar.MONTH, month);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        reminderDate = dateFormat.format(selectedCalendar.getTime());

                        binding.remDate.setText(reminderDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}