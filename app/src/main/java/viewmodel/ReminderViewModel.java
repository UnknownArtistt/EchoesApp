package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import model.Reminder;
import repositories.RemindersRepo;

public class ReminderViewModel extends AndroidViewModel {

    RemindersRepo remindersRepo;
    MutableLiveData<Reminder> reminderSelected = new MutableLiveData<Reminder>();

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        remindersRepo = new RemindersRepo(application);
    }

    //public LiveData<List<Reminder>> get(String reminder){ return remindersRepo.get(reminder); }

    //public LiveData<List<Reminder>> get(String reminder){ return remindersRepo.get(); }
    public LiveData<List<Reminder>> get() {
        return remindersRepo.get();
    }

    public void add(Reminder reminder) {
        remindersRepo.insert(reminder);
    }

    public void delete(Reminder reminder) {
        remindersRepo.delete(reminder);
    }

    void update(Reminder reminder) {
        remindersRepo.update(reminder);
    }

    void select(Reminder reminder) {
        reminderSelected.setValue(reminder);
    }

    MutableLiveData<Reminder> selected() {
        return reminderSelected;
    }
}
