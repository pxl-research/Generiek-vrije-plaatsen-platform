package be.PXLResearch.code4belgium.escaperooms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int minimumAge;
    private int duration;
    private int minPlayers;
    private int maxPlayers;

    @ManyToOne
    @JoinColumn(name = "escaperoom_id", nullable = false)
    private EscapeRoom escapeRoom;
}
