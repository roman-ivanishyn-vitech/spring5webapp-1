package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author aric = new Author("Eric", "Evans");
        Book book = new Book("Spring5", "1234");
        aric.getBooks().add(book);
        book.getAuthors().add(aric);

        authorRepository.save(aric);
        bookRepository.save(book);

        Author ron = new Author("Ron", "Wizli");
        Book book1 = new Book("Spring JPA", "1111");

        ron.getBooks().add(book1);
        book1.getAuthors().add(ron);

        authorRepository.save(ron);
        bookRepository.save(book1);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
