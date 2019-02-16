package postman;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

@Setter
@AllArgsConstructor
public class Address implements Comparable<Address> {
    City city;
    String street;
    int house;

    public City getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return house == address.house &&
                city == address.city &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house);
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "city=" + city +
                ", street='" + street + '\'' +
                ", number=" + house +
                '}';
    }

    public String display() {
        return "#" + house + " " + street+ " "+ city;
    }


    @Override
    public int compareTo(Address obj) {
        if (this == obj) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (this.getCity()==obj.getCity()) {
            if  (this.street.equals(obj.street))
                return this.getHouse()-obj.getHouse();
            else
                return this.street.compareTo(obj.street);
        }
        return this.getCity().compareTo(obj.getCity());
    }
}
