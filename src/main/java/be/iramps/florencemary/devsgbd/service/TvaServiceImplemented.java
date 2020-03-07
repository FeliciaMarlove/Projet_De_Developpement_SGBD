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
        for (Tva tva: repository.findAll()) {
            if (tva.getIdTva().equals(id)) {
                return repository.findById(id).get();
            }
        }
        return null;
    }

    @Override
    public Tva create(TvaDto newItem) {
        if (equalsAny(newItem) == null) {
            Tva newTva = new Tva(newItem.getTauxTva());
            repository.save(newTva);
            return newTva;
        }
        return null;
    }


    @Override
    public Tva update(Long id, TvaDto update) {
        if ((exists(id)) && (equalsAny(update) == null)) {
            Tva toUpdate = repository.findById(id).get();
            toUpdate.setTauxTva(update.getTauxTva());
            repository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    @Override
    public Tva delete(Long id) {
        Tva tva = repository.findById(id).get();
        if (exists(id)) {
            tva.setActifTva(false);
            repository.save(tva);
            return readOne(id);
        }
        return null;
    }

    @Override
    public List<Tva> readActive() {
        List<Tva> actifs = new ArrayList<>();
        for (Tva tva : read()) {
            if (tva.isActifTva()) actifs.add(tva);
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

    private Tva equalsAny(TvaDto tvaDto) {
        for (Tva tvaCompared : read()) {
            if (tvaDto.getTauxTva() == tvaCompared.getTauxTva()) return repository.findById(tvaCompared.getIdTva()).get();
        }
        return null;
    }
}
