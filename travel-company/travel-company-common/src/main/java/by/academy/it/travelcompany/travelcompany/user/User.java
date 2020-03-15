package by.academy.it.travelcompany.travelcompany.user;

import by.academy.it.travelcompany.travelcompany.user.favourite.Favourite;

import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String userName;
    private String password;
    private String role;
    private String salt;

    private List<Favourite> favouriteList;

    public User() {
    }

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User(Long id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String userName, String password, String salt, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Favourite> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(List<Favourite> favouriteList) {
        this.favouriteList = favouriteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
