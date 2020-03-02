package by.academy.it.travelcompany.orm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEETING")
@EqualsAndHashCode(exclude = {"id","employeeList"})
@ToString(exclude = {"employeeList"})
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long id;
    private String meetingSubj;
    private LocalDateTime meetingDateTime;
    @ManyToMany(mappedBy = "meetings",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();

}
