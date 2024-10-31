package model;


public record ContactData(String id, String first_name, String middle_name, String last_mane,
                          String telephone_home, String telephone_mobile, String e_mail) {

    public ContactData() {

        this("", "", "", "", "", "","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.first_name, this.middle_name, this.last_mane, this.telephone_home, this.telephone_mobile, this.e_mail);
    }

    public ContactData withName(String first_name) {
        return new ContactData(this.id, first_name, this.middle_name, this.last_mane, this.telephone_home, this.telephone_mobile, this.e_mail);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.id, this.first_name, middle_name, this.last_mane, this.telephone_home, this.telephone_mobile, this.e_mail);
    }
    public ContactData withLastName(String last_mane) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_mane, this.telephone_home, telephone_mobile, this.e_mail);
    }

    public ContactData withTelephoneMobile(String telephone_mobile) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_mane, this.telephone_home, telephone_mobile, this.e_mail);
    }
    public ContactData withTelephoneHome(String telephone_home) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_mane, telephone_home, this.telephone_mobile, this.e_mail);

    }
    public ContactData withEmail(String e_mail) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_mane, this.telephone_home, this.telephone_mobile, e_mail);

    }

}