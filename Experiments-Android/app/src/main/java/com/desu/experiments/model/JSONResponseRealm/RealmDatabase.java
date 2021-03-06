package com.desu.experiments.model.JSONResponseRealm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class RealmDatabase extends RealmObject {
    @PrimaryKey
    private String name;
    private int age;

    private Email email;
    private RealmList<Email> emails;
    @Ignore
    private int sessionId;

    // Standard getters & setters generated by your IDE…
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }

    public RealmList<Email> getEmails() {
        return emails;
    }
    public void setEmails(RealmList<Email> emails) {
        this.emails = emails;
    }
}
