package be.iramps.florencemary.devsgbd.service;

import be.iramps.florencemary.devsgbd.dto.TvaDto;
import be.iramps.florencemary.devsgbd.model.Tva;
import be.iramps.florencemary.devsgbd.repository.TvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service contenant la couche business sur l'entite Tva
 */
@Service
public class TvaServiceImplemented implements TvaService {
    private TvaRepository repository;

    @Autowired
    public TvaServiceImplemented(TvaRepository repository) {
        this.repository = repository;
    }

    /**
     * Retourne les Tva en DB
     * @return List Tva toutes les tva en DB
     */
    @Override
    public List<Tva> read() {
        return (List<Tva>) repository.findAll();
    }

    /**
     * Retourne une Tva
     * @param id (Long) : id de la Tva a retourner
     * @return Tva la Tva trouvee
     */
    @Override
    public Tva readOne(Long id) {
        for (Tva tva: repository.findAll()) {
            if (tva.getIdTva().equals(id)) {
                return repository.findById(id).get();
            }
        }
        return null;
    }

    /**
     * Retourne les Tva actives
     * @return List Tva tva actives
     */
    @Override
    public List<Tva> readActive() {
        List<Tva> actifs = new ArrayList<>();
        for (Tva tva : read()) {
            if (tva.isActifTva()) actifs.add(tva);
        }
        return actifs;
    }

    /**
     * Cree une Tva
     * @param newItem (TvaDto) : la Tva a creer
     * @return Tva la Tva creee || null si la Tva existe deja en DB
     */
    @Override
    public Tva create(TvaDto newItem) {
        if (equalsAny(newItem) == null) {
            Tva newTva = new Tva(newItem.getTauxTva());
            repository.save(newTva);
            return newTva;
        }
        return null;
    }

    /**
     * Met a jour une Tva
     * @param id (Long) : l'id de la Tva a modifier
     * @param update (TvaDto) : la Tva a modifier
     * @return Tva la Tva modifiee
     */
    @Override
    public Tva update(Long id, TvaDto update) {
        if ((exists(id))) {
            Tva toUpdate = repository.findById(id).get();
            toUpdate.setTauxTva(update.getTauxTva());
            repository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    /**
     * Supprime logiquement une Tva
     * @param id (Long) : id de la Tva a supprimer
     * @return Tva la Tva supprimee
     */
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

    //__________________PRIVATE METHODS_________________________________________________________________________________

    private boolean exists(Long id) {
        for (Tva tva : read()) {
            if ((tva.isActifTva()) && (tva.getIdTva().equals(id))) {
                return true;
            }
        }
        return false;
    }

    private Tva equalsAny(Tva tva) {
        for (Tva tvaCompared : read()) {
            if (tva.equals(tvaCompared)) return repository.findById(tvaCompared.getIdTva()).get();
        }
        return null;
    }

    private Tva equalsAny(TvaDto tvaDto) {
        for (Tva tvaCompared : read()) {
            if (tvaDto.getTauxTva().equals(tvaCompared.getTauxTva())) return repository.findById(tvaCompared.getIdTva()).get();
        }
        return null;
    }
}
