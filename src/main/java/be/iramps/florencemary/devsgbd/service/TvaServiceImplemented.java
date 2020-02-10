package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.TvaDto;
import be.iramps.florencemary.devsgbd.model.Tva;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvaServiceImplemented implements TvaService {
    private TvaRepository repository;

    @Autowired
    public TvaServiceImplemented(TvaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tva> read() {
        return (List<Tva>) repository.findAll();
    }

    @Override
    public Tva readOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void create(TvaDto newItem) {
        Tva newTva = new Tva(newItem.getTauxTva());
        if (equalsAny(newTva) == null) repository.save(newTva);
    }

    @Override
    public Tva update(Long id, TvaDto update) {
        Tva toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setTauxTva(update.getTauxTva());
            if (equalsAny(toUpdate) == null) repository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Tva delete(Long id) {
        if (exists(id)) {
            repository.findById(id).get().setActifTva(false);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Tva> readActive() {
        List<Tva> actifs = new ArrayList<>(read());
        for (Tva tva : actifs) {
            if (tva.isActifTva()) actifs.remove(tva);
        }
        return actifs;
    }

    private boolean exists(Long id) {
        boolean exists = false;
        for (Tva tva : read()) {
            if ((tva.isActifTva() == true) && (tva.getIdTva() == id)) exists = true;
        }
        return exists;
    }

    private Tva equalsAny(Tva tva) {
        for (Tva tvaCompared : read()) {
            if (tva.equals(tvaCompared)) return repository.findById(tvaCompared.getIdTva()).get();
        }
        return null;
    }
}
