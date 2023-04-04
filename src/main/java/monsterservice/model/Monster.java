package monsterservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity เรียกใช้ข้างนอก
@Entity
@Table(name = "monsters")
@Getter
@Setter
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer health;
}
