package com.testboot.test2.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

*ссылка с привязкой к времени (добавление нового поля)
https://youtu.be/mGfiV9WDd6Y?list=PLU2ftbIeotGqSTOVNjT4L3Yfy8jatCdhm&t=574


@Entity
@Table(name="Message")
@ToString(of={"id","text"})
@EqualsAndHashCode(of={"id"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
