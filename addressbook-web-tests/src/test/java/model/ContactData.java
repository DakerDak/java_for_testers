package model;


public record ContactData(String first_name, String middle_name, String last_mane,
                          String telephone_home, String telephone_mobile, String e_mail) {

    public ContactData() {
        this("", "", "", "", "","");
    }

    public ContactData withName(String first_name) {
        return new ContactData(first_name, this.middle_name, this.last_mane, this.telephone_home, this.telephone_mobile, this.e_mail);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.first_name, middle_name, this.last_mane, this.telephone_home, this.telephone_mobile, this.e_mail);
    }

    public ContactData withTelephoneMobile(String telephone_mobile) {
        return new ContactData(this.first_name, this.middle_name, this.last_mane, this.telephone_home, telephone_mobile, this.e_mail);
    }
}