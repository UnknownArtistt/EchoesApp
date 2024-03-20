package repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.DBAccess;
import data.UserDAO;
import model.User;

public class UsersRepo {

    UserDAO userDAO;
    Executor executor = Executors.newSingleThreadExecutor();

    public UsersRepo(Application application) {
        userDAO = DBAccess.obtainInstance(application).getUserDAO();
    }

    public LiveData<User> get(String user) {
        return userDAO.getUser(user);
    }

    public String getPasswordLogin(String user) {
        return userDAO.getPasswordLogin(user);
    }

    public void insert(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.insert(user);
            }
        });
    }

    public void update(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.update(user);
            }
        });
    }

    public void delete(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.delete(user);
            }
        });
    }
}
