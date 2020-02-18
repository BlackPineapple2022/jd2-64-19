package by.academy.it.travelcompany.entity.homework.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "MEETING")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long meetingId;
    private String meetingName;
    @ManyToMany(mappedBy = "meetingList")
    private List<Employee> employeeList;
}
