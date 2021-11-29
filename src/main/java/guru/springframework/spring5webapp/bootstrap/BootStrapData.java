package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("Fl");

        publisherRepository.save(publisher);

        Author aric = new Author("Eric", "Evans");
        Book book = new Book("Spring5", "1234");
        aric.getBooks().add(book);
        book.getAuthors().add(aric);

        book.setPublisher(publisher);
        publisher.getBookSet().add(book);

        authorRepository.save(aric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author ron = new Author("Ron", "Wizli");
        Book book1 = new Book("Spring JPA", "1111");

        ron.getBooks().add(book1);
        book1.getAuthors().add(ron);

        book1.setPublisher(publisher);
        publisher.getBookSet().add(book1);

        authorRepository.save(ron);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        System.out.println("Publisher in Bootstrap");
        System.out.println("Number of publisher: " + publisherRepository.count());
        System.out.println("Publisher has Books: " + publisher.getBookSet().size());
    }
}
