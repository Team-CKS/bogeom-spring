package cks.bogeom.market;

import cks.bogeom.item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="market_tb")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Item item;

    @Column(nullable = false)
    private String name;

    private String img;

    private double lat;

    private double lon;

    private String street;

    @Column(nullable = false)
    private double price;

    private Timestamp update_time;

    @Builder
    public Market(int id, Item item, String name, String img, double lat, double lon, String street, double price, Timestamp update_time) {
        this.id = id;
        this.item = item;
        this.name = name;
        this.img = img;
        this.lat = lat;
        this.lon = lon;
        this.street = street;
        this.price = price;
        this.update_time = update_time;
    }
}
