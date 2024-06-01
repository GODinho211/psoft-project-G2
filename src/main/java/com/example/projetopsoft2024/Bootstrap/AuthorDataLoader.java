package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthorDataLoader implements CommandLineRunner {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorDataLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        byte[] photo1 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo1.jpg");
        byte[] photo2 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo2.jpg");
        byte[] photo3 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo3.jpg");
        byte[] photo4 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo4.jpg");
        byte[] photo5 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo5.jpg");
        byte[] photo6 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo6.jpg");
        byte[] photo7 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo7.jpg");
        byte[] photo8 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo8.jpg");
        byte[] photo9 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo9.jpg");
        byte[] photo10 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo10.jpg");
        byte[] photo11 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo11.jpg");
        byte[] photo12 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo12.jpg");
        byte[] photo13 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo13.jpg");
        byte[] photo14 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo14.jpg");
        byte[] photo15 = loadPhoto("src/main/java/com/example/projetopsoft2024/Image/photo15.jpg");

        List<Author> authors = Arrays.asList(
                new Author("Harper Lee", "Harper Lee was born in Monroeville, Alabama, on April 28, 1926. Harper Lee lived in New York City for most of her adult life. Despite being an important figure in modern American literature, Lee only wrote and published two novels. Lee's first novel, To Kill a Mockingbird, was published in 1960.", photo1),
                new Author("George Orwell", "Orwell was born Eric Arthur Blair on 25 June 1903 in eastern India, the son of a British colonial civil servant. He was educated in England and, after he left Eton, joined the Indian Imperial Police in Burma, then a British colony. He resigned in 1927 and decided to become a writer.", photo2),
                new Author("Yuval Noah Harari", "Yuval Noah Harari was born in Kiryat Ata, Israel, in 1976 and grew up in a secular Jewish family with Lebanese and Eastern European roots in Haifa, Israel. Harari is gay and in 2002 met his husband Itzik Yahav, whom he calls \"my internet of all things\". Yahav is also Harari's personal manager.", photo3),
                new Author("Tara Westover", "Tara Westover is an American author. Born in Idaho to a father opposed to public education, she never attended school. An older brother taught her to read, and after that her education was erratic and haphazard, with most of her days spent working in her father's junkyard or stewing herbs for her mother.", photo4),
                new Author("Frank Herbert", "Frank Herbert (born October 8, 1920, Tacoma, Washington, U.S.—died February 11, 1986, Madison, Wisconsin) was an American science-fiction writer noted as the author of the best-selling Dune series of futuristic novels, a group of highly complex works that explore such themes as ecology, human evolution, the consequences of genetic manipulation, and mystical and psychic possibilities.", photo5),
                new Author("William Gibson", "William Gibson (born March 17, 1948, Conway, South Carolina, U.S.) is an American Canadian writer of science fiction who was the leader of the genre's cyberpunk movement. Gibson grew up in southwestern Virginia.", photo6),
                new Author("J.R.R. Tolkien", "J.R.R. Tolkien (born January 3, 1892, Bloemfontein, South Africa—died September 2, 1973, Bournemouth, Hampshire, England) was an English writer and scholar who achieved fame with his children's book The Hobbit (1937) and his richly inventive epic fantasy The Lord of the Rings (1954–55).", photo7),
                new Author("J.K. Rowling", "Joanne Rowling was born on July 31, 1965, in Yate, near Bristol, England. She grew up in England and in Chepstow, Gwent, Wales. She loved reading and wrote her first story at the age of six. After graduating from the University of Exeter in 1986, Rowling began working for Amnesty International in London, England.", photo8),
                new Author("Stieg Larsson", "Stieg Larsson (born August 15, 1954, Skelleftehamn, Sweden—died November 9, 2004, Stockholm) was a Swedish writer and activist whose posthumously published Millennium series of crime novels brought him international acclaim.", photo9),
                new Author("Gillian Flynn", "Gillian Flynn (born February 24, 1971, Kansas City, Missouri, U.S.) is an American writer known for her darkly entertaining tales of murder and deceit in the Midwest. Flynn, the younger of two children, was raised in Kansas City, where both of her parents taught.", photo10),
                new Author("Walter Isaacson", "He is a host of the show “Amanpour and Company” on PBS and CNN, a contributor to CNBC, and host of the podcast “Trailblazers, from Dell Technologies.” Isaacson was born on May 20, 1952, in New Orleans. He is a graduate of Harvard College and of Pembroke College of Oxford University, where he was a Rhodes Scholar.", photo11),
                new Author("Michelle Obama", "Raised on the South Side of Chicago, Obama is a graduate of Princeton University and Harvard Law School. In her early legal career, she worked at the law firm Sidley Austin where she met her future husband. She subsequently worked in nonprofits and as the associate dean of Student Services at the University of Chicago.", photo12),
                new Author("Charles Duhigg", "Charles Duhigg is a former columnist and senior editor at The New York Times. Mr. Duhigg is also the author of “The Power of Habit,” which has spent over two years on the New York Times best-seller list, and the recent “Smarter Faster Better,” which was also a New York Times best seller.", photo13),
                new Author("James Clear", "James Clear (born 1986) is an American writer.He is best known for his book Atomic Habits.Raised in Hamilton, Ohio, Clear received his degree in biomechanics from Denison University in 2008, where he also served as captain of the baseball team.", photo14),
                new Author("Markus Zusak", "Markus Zusak was born in 1975 in Sydney, Australia, the youngest of four children of immigrant German and Austrian parents. Neither parent could read or write English when they first arrived in Australia, but they wanted their children to master the language and strongly encouraged them to read and communicate in English from an early age. Zusak began writing fiction at age 16 and pursued a degree in teaching. Before becoming a professional author, Zusak worked briefly as a house painter, a janitor and a high school English teacher.", photo15)



        );


        authorRepository.saveAll(authors);
    }

    private byte[] loadPhoto(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}