package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import kotlin.jvm.functions.Function1;
import model.Pictogram;
import repositories.PictogramsRepo;

public class PictogramViewModel extends AndroidViewModel {

    PictogramsRepo pictogramsRepo;
    MutableLiveData<Pictogram> pictogramSelected = new MutableLiveData<Pictogram>();
    MutableLiveData<String> termSearch = new MutableLiveData<>();

    public PictogramViewModel(@NonNull Application application) {
        super(application);
        pictogramsRepo = new PictogramsRepo(application);
    }

    public LiveData<Pictogram> get(String pictogram){ return pictogramsRepo.get(pictogram); }

    public void add(Pictogram pictogram) {
        pictogramsRepo.insert(pictogram);
    }

    void delete(Pictogram pictogram) {
        pictogramsRepo.delete(pictogram);
    }

    void update(Pictogram pictogram) {
        pictogramsRepo.update(pictogram);
    }

    void select(Pictogram pictogram) {
        pictogramSelected.setValue(pictogram);
    }

    MutableLiveData<Pictogram> selected() {
        return pictogramSelected;
    }

    public LiveData<List<Pictogram>> obtain(){
        return pictogramsRepo.get();
    }

    LiveData<List<Pictogram>> resultSearch = Transformations.switchMap(termSearch, new Function1<String, LiveData<List<Pictogram>>>() {
        @Override
        public LiveData<List<Pictogram>> invoke(String input) {
            return pictogramsRepo.search(input);
        }
    });

    public LiveData<List<Pictogram>> search(){
        return resultSearch;
    }

    public LiveData<List<Pictogram>> filter(int filter){ return pictogramsRepo.filterId(filter); }
}
