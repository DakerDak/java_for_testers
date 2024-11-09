package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

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

        public String work;

        @Column (name = "phone2")
        public String secondary;

        public String nickname = new String();
        public String company = new String();
        public String title = new String();
        public String address = new String();
//        public String work = new String();
        public String fax = new String();
        public String email2 = new String();
        public String email3 = new String();
        public String homepage = new String();

        public ContactRecord() {

        }

        public ContactRecord(int id, String first_name, String middle_name, String last_mane,
                             String telephone_home, String telephone_mobile, String e_mail, String photo, String work, String secondary) {

                this.id = id;
                this.first_name = first_name;
                this.middle_name= middle_name;
                this.last_mane = last_mane;
                this.telephone_home = telephone_home;
                this.telephone_mobile = telephone_mobile;
                this.e_mail = e_mail;
                this.photo = photo;
                this.work = work;
                this.secondary = secondary;

        }
}
