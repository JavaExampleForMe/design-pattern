package postman.decorator;



public class PackageInfo {
    int cityId;
    String street;
    int number;
    String name;

    public PackageInfo(int cityId, String street, int number, String name) {
        this.cityId = cityId;
        this.street = street;
        this.number = number;
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "cityId=" + cityId +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
