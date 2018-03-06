package com.jun.entity;

import com.jun.entity.listener.User3EntityListener;
import com.jun.util.MyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
//@Table
//@EntityListeners({User3EntityListener.class})
@MyEntity(listener ={User3EntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User3 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    public User3(String name){
        this.name=name;
    }
}
