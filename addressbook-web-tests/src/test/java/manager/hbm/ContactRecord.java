package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

        @Id

        @Column(name = "id")
        public int id;

        @Column(name = "firstname")
        public String first_name;

        @Column(name = "middlename")
        public String middle_name;

        @Column(name = "lastname")
        public String last_mane;

        @Column(name = "home")
        public String telephone_home;

        @Column(name = "mobile")
        public String telephone_mobile;

        @Column(name = "email")
        public String e_mail;

        @Column(name = "photo")
        public String photo;


}
