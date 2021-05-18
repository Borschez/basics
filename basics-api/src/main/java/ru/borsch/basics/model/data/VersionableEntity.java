package ru.borsch.basics.model.data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class VersionableEntity extends BaseEntity {
    @Column(length = 128)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
