package be.PXLResearch.code4belgium.escaperooms.domain;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escaperoom_rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private int minimumAge;
    private int duration;
    private int minPlayers;
    private int maxPlayers;
    @ManyToOne
    @JoinColumn(name = "escaperoom_id", nullable = false)
    private EscapeRoom escapeRoom;
}
