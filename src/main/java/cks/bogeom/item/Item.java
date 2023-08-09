package cks.bogeom.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="item_tb")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String img;

    private long count;

    @Builder
    public Item(int id, String name, String img, long count) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.count = count;
    }
}