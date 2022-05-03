package eu.epfc.j4119.contact.repositories;

import eu.epfc.j4119.contact.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
