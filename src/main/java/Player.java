import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player {
    private UUID id;
    private String name;
    private String surname;
    int age;
    private League league;
    private int score;

    public Player(UUID id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

//    public Player(UUID id, String name, String surname, int age, League league) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.score = 0;
//        this.league = league;
//    }

    public void addScore(int val){
        score += val;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}' + "\n";
    }
}