package fr.esgi.reporitories.users.dao;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

    @Entity
    @Table(name = "t_user_authenticator")
    public class TUserAuthenticator {
        private int id;
        private String login;
        private String password;
        private String email;
        private TUserEntity user;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Basic
        @Column(name = "email")
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Basic
        @Column(name = "password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Basic
        @Column(name = "login")
        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            fr.esgi.reporitories.users.dao.TUserAuthenticator that = (fr.esgi.reporitories.users.dao.TUserAuthenticator) o;
            return id == that.id &&
                    Objects.equals(password, that.password);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, password);
        }

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
        @Cascade(org.hibernate.annotations.CascadeType.DELETE)
        public TUserEntity getUser() {
            return user;
        }

        public void setUser(TUserEntity user) {
            this.user = user;
        }
    }

