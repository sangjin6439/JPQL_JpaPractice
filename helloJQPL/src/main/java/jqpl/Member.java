package jqpl;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@NamedQuery(
        name ="Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
public class Member {
    
    @Id @GeneratedValue
    private long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public void changTeam(Team team){
        this.team = team;
        team.getMember().add(this);
    }


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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
