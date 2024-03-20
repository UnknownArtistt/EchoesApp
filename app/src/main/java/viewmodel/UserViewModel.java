package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import model.User;
import repositories.UsersRepo;

public class UserViewModel extends AndroidViewModel {

    UsersRepo usersRepo;
    MutableLiveData<User> userSelected = new MutableLiveData<User>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        usersRepo = new UsersRepo(application);
    }

    public LiveData<User> get(String user){ return usersRepo.get(user); }

    public String getPasswordLogin(String user){ return usersRepo.getPasswordLogin(user); }

    public void add(User user) {
        usersRepo.insert(user);
    }

    void delete(User user) {
        usersRepo.delete(user);
    }

    void update(User user) {
        usersRepo.update(user);
    }

    void select(User user) {
        userSelected.setValue(user);
    }

    MutableLiveData<User> selected() {
        return userSelected;
    }
}
