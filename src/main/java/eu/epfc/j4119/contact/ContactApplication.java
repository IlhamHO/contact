package eu.epfc.j4119.contact;

import eu.epfc.j4119.contact.entities.Contact;
import eu.epfc.j4119.contact.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;
@Transactional
@AllArgsConstructor
@SpringBootApplication
public class ContactApplication implements CommandLineRunner {
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner in = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("Choisissez parmi les options suivantes:");
			System.out.println("1. lister les contacts");
			System.out.println("2. enregistrer un nouveau contact");
			System.out.println("3. modifier un contact");
			System.out.println("4. supprimer un contact");
			System.out.println("Q. quitter l'application");
			String choice = in.nextLine();
			if (choice.equals("Q")) break;
			switch (choice){
				case "1":
					List<Contact> contacts = contactRepository.findAll();
					for(Contact contact : contacts) System.out.println(contact);
					break;
				case "2":
					Contact contact = new Contact();
					System.out.println("Firstname: ");
					String firstname = in.nextLine();
					System.out.println("Lastname: ");
					String lastname = in.nextLine();
					System.out.println("Email: ");
					String email = in.nextLine();
					System.out.println("Phone: ");
					String phone = in.nextLine();
					contact.setFirstname(firstname);
					contact.setLastname(lastname);
					contact.setEmail(email);
					contact.setPhone(phone);
					contactRepository.save(contact);
					break;
				case "3":
					System.out.print("id to modify? ");
					long id = Long.parseLong(in.nextLine());
					contactRepository.getById(id);
					System.out.println("Firstname: ");
					String firstname1 = in.nextLine();
					System.out.println("Lastname: ");
					String lastname1 = in.nextLine();
					System.out.println("Email: ");
					String email1 = in.nextLine();
					System.out.println("Phone: ");
					String phone1 = in.nextLine();
					contactRepository.save(new Contact(id,firstname1,lastname1,email1,phone1));
					break;
				case "4":
					System.out.println("id to delete: ");
					id = Long.parseLong(in.nextLine());
					contactRepository.deleteById(id);
					break;
				default:
					System.out.println("Your choice"+choice+" is not found");
			}
		}while (true);
	}
}
