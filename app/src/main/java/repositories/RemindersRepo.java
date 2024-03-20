package repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.echoesapp.MainActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.DBAccess;
import data.ReminderDAO;
import model.Reminder;

public class RemindersRepo {
    ReminderDAO reminderDAO;
    Executor executor = Executors.newSingleThreadExecutor();

    public RemindersRepo(Application application) {
        reminderDAO = DBAccess.obtainInstance(application).getReminderDAO();
    }

    public LiveData<List<Reminder>> get() {
        return reminderDAO.getReminder(MainActivity.getUsername());
    }

    public void insert(Reminder user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reminderDAO.insert(user);
            }
        });
    }

    public void update(Reminder user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reminderDAO.update(user);
            }
        });
    }

    public void delete(Reminder user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reminderDAO.delete(user);
            }
        });
    }
}

