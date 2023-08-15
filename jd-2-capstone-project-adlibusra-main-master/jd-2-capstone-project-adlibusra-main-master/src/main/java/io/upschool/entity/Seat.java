package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Table(name = "seats")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "seat_name",unique = true)
    private String seatName;

    @Column
    private Boolean isReserved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seatType", nullable = false)

    private SeatType seatType;
}
