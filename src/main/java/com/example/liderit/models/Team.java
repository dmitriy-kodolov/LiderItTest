package com.example.liderit.models;

import com.example.liderit.models.abstr_model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "teams")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(name = "sport_kind",nullable = false)
    private String sportKind;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @JsonIgnore
    private List<Player> playerList;

    public Team() {

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

    public String getSportKind() {
        return sportKind;
    }

    public void setSportKind(String sportKind) {
        this.sportKind = sportKind;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void addPlayerToList(Player player){
        this.playerList.add(player);
    }

    public Optional<Player> getPlayerFromListById(Integer id){
        for (Player player : this.playerList) {
            if (player.getId() == id) return Optional.of(player);
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportKind='" + sportKind + '\'' +
                ", creationDate=" + creationDate +
                ", playerList=" + playerList +
                '}';
    }
}
