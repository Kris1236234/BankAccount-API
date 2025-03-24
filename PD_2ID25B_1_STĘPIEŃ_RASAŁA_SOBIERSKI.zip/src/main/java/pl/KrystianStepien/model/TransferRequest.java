package pl.KrystianStepien.model;

import java.math.BigDecimal;

public class TransferRequest {
    private String fromPesel;
    private String toPesel;
    private BigDecimal amount;

    public String getFromPesel() {
        return fromPesel;
    }

    public void setFromPesel(String fromPesel) {
        this.fromPesel = fromPesel;
    }

    public String getToPesel() {
        return toPesel;
    }

    public void setToPesel(String toPesel) {
        this.toPesel = toPesel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
