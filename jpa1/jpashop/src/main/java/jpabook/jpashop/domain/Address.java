package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipCode;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipCode(), address.getZipCode());
    } // getter를 호출해야 프록시 객체에서도 사용을 할 수 있음

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipCode());
    }
}
