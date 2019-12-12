package by.academy.it.travelcompany.transfer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transfer {

    private Long id;
    private String startPoint;
    private String endPoint;
    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;
    private String companyName;
    private Transfers transfer;

    public Transfer() {
    }

    public Transfer(Long id, String startPoint, String endPoint, LocalDateTime arriveTime, LocalDateTime departureTime, String companyName, Transfers transfer) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.companyName = companyName;
        this.transfer = transfer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Transfers getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfers transfer) {
        this.transfer = transfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer1 = (Transfer) o;
        return Objects.equals(id, transfer1.id) &&
                Objects.equals(startPoint, transfer1.startPoint) &&
                Objects.equals(endPoint, transfer1.endPoint) &&
                Objects.equals(arriveTime, transfer1.arriveTime) &&
                Objects.equals(departureTime, transfer1.departureTime) &&
                Objects.equals(companyName, transfer1.companyName) &&
                transfer == transfer1.transfer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startPoint, endPoint, arriveTime, departureTime, companyName, transfer);
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
