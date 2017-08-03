package com.kirak.model;

import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kir on 30.05.2017.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_idx")})
public class User extends NamedEntity {

    @Column(name = "email", unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "phone", unique = true)
    @Email
    @NotBlank
    private String phone;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    //@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<UserRole> roles;

    @OrderBy("dateAdded DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Booking> bookings;

    @OrderBy("dateAdded DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
    private Set<Hotel> hotels;

    public User(){}

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.getBookings(), u.getRoles());
    }

    //For anonymous user
    public User(String name, String email, String phone, UserRole role, UserRole... roles) {
        this(null, name, email, phone, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String phone, UserRole role, UserRole... roles) {
        this(id, name, email, phone, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Set<Booking> bookings, UserRole role, UserRole... roles) {
        this(id, name, email, password, new Date(), bookings, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registered, Set<Booking> bookings, Collection<UserRole> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.bookings = bookings;
        this.registered = registered;
        setRoles(roles);
    }


    public User(Integer id, String name, String email, String phone, Date registered, Collection<UserRole> roles) {
        super(id, name);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registered = registered;
        setRoles(roles);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UserRole> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + "\'" +
                ", name='" + getName() + "\'" +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", roles=" + roles.toString() +
                ", bookings=" + bookings.toString() +
                '}';
    }
}
