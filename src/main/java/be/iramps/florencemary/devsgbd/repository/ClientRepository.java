package be.iramps.florencemary.devsgbd.repository;

import be.iramps.florencemary.devsgbd.model.Client;
import org.springframework.data.repository.CrudRepository;

interface ClientRepository extends CrudRepository<Client, Long> {
}
