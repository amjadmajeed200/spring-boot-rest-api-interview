package speedhome.interview.boot.Main.Entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String username;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String password;

        @ElementCollection(targetClass = MemberRole.class, fetch = FetchType.EAGER)
        @CollectionTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"))
        @Enumerated(EnumType.STRING)
        private Set<MemberRole> roles;

        public Set<MemberRole> getRoles() {
            return roles;
        }

        public void setRoles(Set<MemberRole> roles) {
            this.roles = roles;
        }

    public Member() {

    }
    public Member(String username, String firstName, String lastName, String email, String password, MemberRole role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.roles = Collections.singleton(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + roles +
                '}';
    }
}

