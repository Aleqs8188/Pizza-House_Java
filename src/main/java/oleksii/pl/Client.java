package oleksii.pl;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {
    private int id;

    Client() {
    }

    public Client(int id, String name, String surname, String email, int numbercard, int cardpin, BigDecimal money, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.numbercard = numbercard;
        this.cardpin = cardpin;
        this.money = money;
        this.username = username;
        this.password = password;
    }
    public Client(String name, String surname, String email, int numbercard, int cardpin, BigDecimal money, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.numbercard = numbercard;
        this.cardpin = cardpin;
        this.money = money;
        this.username = username;
        this.password = password;
    }

    private String name;
    private String surname;
    private String email;
    private int numbercard;
    private int cardpin;
    private BigDecimal money;
    private String username;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", numbercard=" + numbercard +
                ", cardpin=" + cardpin +
                ", money=" + money +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && numbercard == client.numbercard && cardpin == client.cardpin && client.money.equals(money) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(email, client.email) && Objects.equals(username, client.username) && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, numbercard, cardpin, money, username, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(int numbercard) {
        this.numbercard = numbercard;
    }

    public int getCardPin() {
        return cardpin;
    }

    public void setCardPin(int cardpin) {
        this.cardpin = cardpin;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
