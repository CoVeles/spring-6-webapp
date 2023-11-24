package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author laura = new Author();
        laura.setFirstName("Laura");
        laura.setLastName("Bertz");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123324");

        Author savedLaura = authorRepository.save(laura);
        Book savedDDD = bookRepository.save(ddd);

        Author bob = new Author();
        bob.setFirstName("Bob");
        bob.setLastName("Hertz");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE wot EJB");
        noEJB.setIsbn("123324");

        Author savedBob = authorRepository.save(bob);
        Book savedNoEJB = bookRepository.save(noEJB);

        savedLaura.getBooks().add(savedDDD);
        savedBob.getBooks().add(savedNoEJB);

        authorRepository.save(savedLaura);
        authorRepository.save(savedBob);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

    }
}
