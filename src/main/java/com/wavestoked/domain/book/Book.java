package com.wavestoked.domain.book;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "Book")
//@Table(name = "book")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(length = 15)
    private String isbn;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties = new HashMap<>();

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Book setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Book addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }
}
