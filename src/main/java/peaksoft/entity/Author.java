package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_gen")
    @SequenceGenerator(name = "author_gen",sequenceName = "author_seq",allocationSize = 1)
    private Long id;
    private String fullName;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH},mappedBy = "author")
    private List<Book> books;

    public void addBook(Book book){
        books.add(book);
    }

}
