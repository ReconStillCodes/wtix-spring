package com.wtix.wtix.model;

import jakarta.persistence.*;


@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false, referencedColumnName = "id")
    private Screening screening;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "row_number", nullable = false)
    private Integer rowNumber;

    @Column(name = "col_number", nullable = false)
    private Integer colNumber;

    public Seat(Screening screening, String number, String status, Integer rowNumber, Integer colNumber) {
        this.screening = screening;
        this.number = number;
        this.status = status;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    public Seat() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColNumber() {
        return colNumber;
    }

    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", screening=" + screening +
                ", number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", rowNumber=" + rowNumber +
                ", colNumber=" + colNumber +
                '}';
    }
}
