package com.enviro.assessment.grad001.ModiseMoerane.FileParser;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity //define the table and columns 
public class AccountProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Id
    private Long id;
    private String accountHolderName;
    private String accountHolderSurname;
    private String httpImageLink;

    public AccountProfile() {
    }

    public AccountProfile(String accountHolderName, String accounntHolderSurname, String httpImageLink) {
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accounntHolderSurname;
        this.httpImageLink = httpImageLink;
    }

    public AccountProfile(Long id, String accountHolderName, String accounntHolderSurname, String httpImageLink) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accounntHolderSurname;
        this.httpImageLink = httpImageLink;
    }

    public AccountProfile(String accountHolderName, String accounntHolderSurname) {
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accounntHolderSurname;
    }
    
    

   
    public Long getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

   
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccounntHolderSurname() {
        return accountHolderSurname;
    }

    public void setAccounntHolderSurname(String accounntHolderSurname) {
        this.accountHolderSurname = accounntHolderSurname;
    }

   
    public String getHttpImageLink() {
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.accountHolderName);
        hash = 23 * hash + Objects.hashCode(this.accountHolderSurname);
        hash = 23 * hash + Objects.hashCode(this.httpImageLink);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountProfile other = (AccountProfile) obj;
        if (!Objects.equals(this.accountHolderName, other.accountHolderName)) {
            return false;
        }
        if (!Objects.equals(this.accountHolderSurname, other.accountHolderSurname)) {
            return false;
        }
        if (!Objects.equals(this.httpImageLink, other.httpImageLink)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "AccountProfile{" + "id=" + id + ", accountHolderName=" + accountHolderName + ", accounntHolderSurname=" + accountHolderSurname + ", httpImageLink=" + httpImageLink + '}';
    }
    
}
