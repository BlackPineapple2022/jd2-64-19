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
@EqualsAndHashCode(exclude = {"id","employeeList","meetingDateTime"})
@ToString(exclude = {"employeeList"})
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long id;
    private String meetingSubj;
    private LocalDateTime meetingDateTime;
    @ManyToMany(mappedBy = "meetingList",fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();

}
