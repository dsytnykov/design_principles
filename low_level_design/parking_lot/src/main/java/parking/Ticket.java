package parking;

import payment.PaymentStatusType;
import vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private String floorId;
    private String spotId;
    private PaymentStatusType paymentStatus;

    public Ticket(Builder builder) {
        this.id = builder.id;
        this.vehicle = builder.vehicle;
        this.entryTime = builder.entryTime;
        this.floorId = builder.floorId;
        this.spotId = builder.spotId;
        this.paymentStatus = builder.paymentStatus;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public static class Builder {
        private String id;
        private Vehicle vehicle;
        private LocalDateTime entryTime;
        private String floorId;
        private String spotId;
        private PaymentStatusType paymentStatus = PaymentStatusType.PENDING;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder entryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public Builder floorId(String floorId) {
            this.floorId = floorId;
            return this;
        }

        public Builder spotId(String spotId) {
            this.spotId = spotId;
            return this;
        }

        public Builder paymentStatus(PaymentStatusType paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
