package org.ka.trades.model;

public class Trade {
    private long id;
    private String type;
    private String legalEntityId;

    public Trade() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(String legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (id != trade.id) return false;
        if (type != null ? !type.equals(trade.type) : trade.type != null) return false;
        return legalEntityId != null ? legalEntityId.equals(trade.legalEntityId) : trade.legalEntityId == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (legalEntityId != null ? legalEntityId.hashCode() : 0);
        return result;
    }
}
