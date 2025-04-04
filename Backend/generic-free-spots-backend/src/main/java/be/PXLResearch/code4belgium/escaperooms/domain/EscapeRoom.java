package be.PXLResearch.code4belgium.escaperooms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escape_rooms")
public class EscapeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private int postalCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String website;

    @OneToMany(mappedBy = "escaperoom")
    private List<Room> rooms;
}
