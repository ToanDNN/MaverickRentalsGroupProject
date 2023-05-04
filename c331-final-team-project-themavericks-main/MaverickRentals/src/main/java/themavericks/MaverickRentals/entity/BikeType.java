package themavericks.MaverickRentals.entity;


import java.math.BigDecimal;

public class BikeType {
    private int bikeTypeId;
    private String typeName;
    private BigDecimal bikePrice;

    public BikeType() {
    }

    public int getBikeTypeId() {
        return bikeTypeId;
    }

    public void setBikeTypeId(int bikeTypeId) {
        this.bikeTypeId = bikeTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(BigDecimal bikePrice) {
        this.bikePrice = bikePrice;
    }
}
