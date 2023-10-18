package jqpl;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
public class Member {
    
    @Id @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    private String username;
    private int age;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
