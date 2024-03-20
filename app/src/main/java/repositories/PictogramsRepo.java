package repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.echoesapp.R;

import java.util.List;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.DBAccess;
import data.PictogramDAO;
import model.Pictogram;

public class PictogramsRepo {

    PictogramDAO pictogramDAO;
    Executor executor = Executors.newSingleThreadExecutor();


    List<Pictogram> pictogram = new ArrayList<>();

    private void CreatePictograms() {
        pictogram.add(new Pictogram(R.drawable.agua, R.string.Agua, 1));
        pictogram.add(new Pictogram(R.drawable.carne, R.string.Carne, 1));
        pictogram.add(new Pictogram(R.drawable.cafe, R.string.Cafe, 1));
        pictogram.add(new Pictogram(R.drawable.cerveza, R.string.Cerveza, 1));
        pictogram.add(new Pictogram(R.drawable.ensalada, R.string.Ensalada, 1));
        pictogram.add(new Pictogram(R.drawable.frutossecos, R.string.FrutosSecos, 1));
        pictogram.add(new Pictogram(R.drawable.galletas, R.string.Galletas, 1));
        pictogram.add(new Pictogram(R.drawable.golosinas, R.string.Golosinas, 1));
        pictogram.add(new Pictogram(R.drawable.hortalizas, R.string.Hortalizas, 1));
        pictogram.add(new Pictogram(R.drawable.huevo, R.string.Huevos, 1));
        pictogram.add(new Pictogram(R.drawable.leche, R.string.Leche, 1));
        pictogram.add(new Pictogram(R.drawable.legumbres, R.string.Legumbres, 1));
        pictogram.add(new Pictogram(R.drawable.pan, R.string.Pan, 1));
        pictogram.add(new Pictogram(R.drawable.pasta, R.string.Pasta, 1));
        pictogram.add(new Pictogram(R.drawable.pescadoalaplancha, R.string.Pescado, 1));
        pictogram.add(new Pictogram(R.drawable.tarta, R.string.Tarta, 1));
        pictogram.add(new Pictogram(R.drawable.te, R.string.Té, 1));
        pictogram.add(new Pictogram(R.drawable.verduras, R.string.Verduras, 1));
        pictogram.add(new Pictogram(R.drawable.vino, R.string.Vino, 1));
        pictogram.add(new Pictogram(R.drawable.yogur, R.string.Yogur, 1));
        pictogram.add(new Pictogram(R.drawable.zumo, R.string.Zumo, 1));
        pictogram.add(new Pictogram(R.drawable.aseo, R.string.Baño, 2));
        pictogram.add(new Pictogram(R.drawable.cortarunas, R.string.CortarUñas, 2));
        pictogram.add(new Pictogram(R.drawable.dientes, R.string.Dientes, 2));
        pictogram.add(new Pictogram(R.drawable.ducha, R.string.Ducha, 2));
        pictogram.add(new Pictogram(R.drawable.enjabonarcara, R.string.LavarCara, 2));
        pictogram.add(new Pictogram(R.drawable.enjabonarmanos, R.string.Manos, 2));
        pictogram.add(new Pictogram(R.drawable.maquillaje, R.string.Maquillaje, 2));
        pictogram.add(new Pictogram(R.drawable.peinar, R.string.Peinar, 2));
        pictogram.add(new Pictogram(R.drawable.peluquero, R.string.Pelo, 2));
        pictogram.add(new Pictogram(R.drawable.pintaumas, R.string.PintarUñas, 2));
        pictogram.add(new Pictogram(R.drawable.vestir, R.string.Vestir, 2));
        pictogram.add(new Pictogram(R.drawable.dolorbrazo, R.string.DolorBrazo, 3));
        pictogram.add(new Pictogram(R.drawable.dolorcabeza, R.string.DolorCabeza, 3));
        pictogram.add(new Pictogram(R.drawable.dolorculo, R.string.DolorCulo, 3));
        pictogram.add(new Pictogram(R.drawable.dolorespalda, R.string.DolorEspalda, 3));
        pictogram.add(new Pictogram(R.drawable.dolorestomago, R.string.DolorEstomago, 3));
        pictogram.add(new Pictogram(R.drawable.dolorgarganta, R.string.DolorGarganta, 3));
        pictogram.add(new Pictogram(R.drawable.dolormano, R.string.DolorMano, 3));
        pictogram.add(new Pictogram(R.drawable.dolormuela, R.string.DolorDientes, 3));
        pictogram.add(new Pictogram(R.drawable.dolorpecho, R.string.DolorPecho, 3));
        pictogram.add(new Pictogram(R.drawable.dolorpie, R.string.DolorPie, 3));
        pictogram.add(new Pictogram(R.drawable.dolorpierna, R.string.DolorPierna, 3));
        pictogram.add(new Pictogram(R.drawable.genitales, R.string.DolorGenitales, 3));
        pictogram.add(new Pictogram(R.drawable.oreja, R.string.DolorOreja, 3));
        pictogram.add(new Pictogram(R.drawable.ojo, R.string.DolorOjos, 3));
        pictogram.add(new Pictogram(R.drawable.lengua, R.string.DolorLengua, 3));
        pictogram.add(new Pictogram(R.drawable.nariz, R.string.DolorNariz, 3));
        pictogram.add(new Pictogram(R.drawable.bailar, R.string.Bailar, 4));
        pictogram.add(new Pictogram(R.drawable.cartas, R.string.Cartas, 4));
        pictogram.add(new Pictogram(R.drawable.gimnasio, R.string.Gimnasio, 4));
        pictogram.add(new Pictogram(R.drawable.juegodemesa, R.string.JuegoMesa, 4));
        pictogram.add(new Pictogram(R.drawable.leer, R.string.Leer, 4));
        pictogram.add(new Pictogram(R.drawable.manualidades, R.string.Manualidades, 4));
        pictogram.add(new Pictogram(R.drawable.musica, R.string.Musica, 4));
        pictogram.add(new Pictogram(R.drawable.paseo, R.string.Paseo, 4));
        pictogram.add(new Pictogram(R.drawable.pelicula, R.string.Pelicula, 4));
        pictogram.add(new Pictogram(R.drawable.pintar, R.string.Pintar, 4));
    }

    public PictogramsRepo(Application application) {
        pictogramDAO = DBAccess.obtainInstance(application).getPictogramDAO();

        executor.execute(() -> {
            if (pictogramDAO.getPictogramsSync().isEmpty()) {
                CreatePictograms();
                for (Pictogram momentPictogram : pictogram) {
                    this.insert(momentPictogram);
                }
            }
        });
    }

    public LiveData<Pictogram> get(String pictogram) {
        return pictogramDAO.getPictogram(pictogram);
    }

    public void insert(Pictogram pictogram) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pictogramDAO.insert(pictogram);
            }
        });
    }

    public void update(Pictogram pictogram) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pictogramDAO.update(pictogram);
            }
        });
    }

    public void delete(Pictogram pictogram) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pictogramDAO.delete(pictogram);
            }
        });
    }

    public LiveData<List<Pictogram>> get(){
        return pictogramDAO.getPictograms();
    }

    public LiveData<List<Pictogram>> search(String t) {
        return pictogramDAO.search(t);
    }

    public LiveData<List<Pictogram>> filterId(int typeId) {
        return pictogramDAO.getFilter(typeId);
    }
}
