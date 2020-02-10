package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.TvaDto;
import be.iramps.florencemary.devsgbd.model.Tva;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        repository.save(newTva);
    }

    @Override
    public Tva update(Long id, TvaDto update) {
        Tva toUpdate = repository.findById(id).get();
        if ((toUpdate != null) && exists(id)) {
            toUpdate.setTauxTva(update.getTauxTva());
            repository.save(toUpdate);
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

    private boolean exists(Long id) {
        boolean exists = false;
        for (Tva tva : read()) {
            if ((tva.isActifTva() == true) && (tva.getIdTva() == id)) exists = true;
        }
        return exists;
    }
}
