package ru.borsch.basics.model.document;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.data.annotation.ReadOnlyProperty;
import ru.borsch.basics.model.data.VersionableEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DocumentEntity extends VersionableEntity {

    @ReadOnlyProperty
    @Column(length = 256)
    private String state;

    @Column(length = 256)
    private String registrationNumber;

    @Column(length = 512)
    private String name;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getDocumentTypeCode();
}
