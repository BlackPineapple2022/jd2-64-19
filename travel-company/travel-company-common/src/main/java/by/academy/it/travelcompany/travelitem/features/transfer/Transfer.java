package by.academy.it.travelcompany.travelitem.features.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    private Long id;
    private String startPoint;
    private String endPoint;
    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;
    private String companyName;
    private Transfers transfer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer1 = (Transfer) o;
        return  Objects.equals(startPoint, transfer1.startPoint) &&
                Objects.equals(endPoint, transfer1.endPoint) &&
                Objects.equals(arriveTime, transfer1.arriveTime) &&
                Objects.equals(departureTime, transfer1.departureTime) &&
                Objects.equals(companyName, transfer1.companyName) &&
                transfer == transfer1.transfer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, arriveTime, departureTime, companyName, transfer);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", companyName='" + companyName + '\'' +
                ", transfer=" + transfer +
                '}';
    }

}
